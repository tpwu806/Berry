package com.iot.sensor.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iot.common.utilities.Pager;
import com.iot.device.dto.DeviceDO;
import com.iot.device.service.DeviceService;
import com.iot.sensor.domain.Sensor;
import com.iot.sensor.dto.SensorDO;
import com.iot.sensor.service.SensorService;



@Controller
public class SensorControler {
	static final Logger log = LoggerFactory.getLogger(SensorControler.class);
	
	@Resource
	private Environment env;
	
	@Autowired
	private SensorService sensorService;
	
	@Autowired
	private DeviceService deviceService;
	
	/**
	 * 传感器列表
	 *
	 */
	@RequestMapping(value = { "/sensor/viewsensor" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView viewAllSensor(@ModelAttribute("sensorList") Object sensorList,Model model, Pageable pgble,
			HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("sensor/list-of-sensor");
		Integer sensorNum;
		try {
			Integer deviceId=Integer.valueOf(request.getParameter("deviceId"));	
			DeviceDO device=this.deviceService.getDeviceDetailById(deviceId);
			modelAndView.addObject("device", device);
			if (null != sensorList && sensorList instanceof Pager) {

				if (((Pager) sensorList).hasContent()) {

					Pager<SensorDO> rList = (Pager) sensorList;
					modelAndView.addObject("sensorList", rList);

				} else {

					modelAndView.addObject("MESSAGE_KEY", "没有搜索结果");
					sensorNum=0;
					modelAndView.addObject("sensorNum", sensorNum);
				}

			} else {
								
				Page<SensorDO> sList = this.sensorService.retrieveAllSensor(deviceId,pgble);				
				String url = request.getContextPath() + "/sensor/viewsensor?";
				Pager<SensorDO> page = new Pager(sList, url);
				modelAndView.addObject("sensorList", page);
				sensorNum=(int) page.getTotalElements();
				modelAndView.addObject("sensorNum", sensorNum);
			}			
		} catch (Exception ex) {
			log.debug("Error retrieving list sensor search results or retrieving all sensor", ex);
			System.out.println(ex);
			model.addAttribute("MESSAGE_KEY", "系统发生故障，请跟Berry联系");
		}
		return modelAndView;
	}
	

	/**
	 * 获得添加传感器表单
	 *
	 */
	@RequestMapping(value = { "/sensor/newsensorform/{deviceId}" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView viewCreateSensorForm(@PathVariable Integer deviceId) {
		ModelAndView modelAndView = new ModelAndView("sensor/add-sensor-form");
		
		SensorDO sensorObject = new SensorDO();
		sensorObject.setDeviceid(deviceId);
		modelAndView.addObject("sensorForm", sensorObject);
		
		return modelAndView;
	}
	
	/**
	 * 保存添加传感器表单
	 *
	 */
	@RequestMapping(value = { "/sensor/newsensorform/newsensor" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public String addNewSensor(@Valid @ModelAttribute("sensorForm") SensorDO sensorForm, BindingResult bindingResult,
			Model model, RedirectAttributes redirectAttributes) {
		try {
			if (bindingResult.hasErrors()) {
				return "sensor/add-sensor-form";
			}
			Sensor np = this.sensorService.createSensor(sensorForm);
		} catch (Exception ex) {
			log.debug("Error when creating new sensor article", ex);
			redirectAttributes.addFlashAttribute("MESSAGE_KEY", "系统发生故障，请跟Berry联系");
		}
		return "redirect:/sensor/viewsensor?page=0&size=" + this.env.getRequiredProperty("paging.numitems")+"&deviceId="+sensorForm.getDeviceid()+"&";
	}
	
	/**
	 * 获得修改传感器表单
	 *
	 */
	@RequestMapping(value = { "/sensor/editsensorform/{sensorId}" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView editSensor(@PathVariable Integer sensorId) {
		ModelAndView modelAndView = new ModelAndView("sensor/edit-sensor-form");
		try {

			SensorDO sensorObject = this.sensorService.getSensorDetailById(sensorId,"");
			modelAndView.addObject("sensorForm", sensorObject);			
		} catch (Exception ex) {
			log.debug("Error in finding news article to display edit sensor form", ex);
			modelAndView.addObject("MESSAGE_KEY", "系统发生故障，请跟Berry联系");
		}
		return modelAndView;
	}
	
	/**
	 * 保存修改传感器表单
	 *
	 */
	@RequestMapping(value = { "/sensor/editsensorform/editsensor/{sensorId}" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public ModelAndView viewEditSensorForm(@PathVariable Integer sensorId, @ModelAttribute("sensorForm") SensorDO sensorForm) {
		ModelAndView modelAndView = null;
		try {
			Sensor nn = this.sensorService.updateSensor(sensorForm);

			modelAndView = new ModelAndView("redirect:/sensor/viewsensor/viewsensorarticle/" + sensorId);
		} catch (Exception ex) {
			log.debug("Error in finding news article to use in editing device article", ex);
			modelAndView.addObject("MESSAGE_KEY", "系统发生故障，请跟Berry联系");
		}
		return modelAndView;
	}
	
	/**
	 * 删除传感器
	 *
	 */
	@RequestMapping(value = { "/sensor/deletesensor/{id}" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView deleteSensor(@PathVariable Integer id) {
		ModelAndView modelAndView = null;
		try {
			modelAndView = new ModelAndView(
					"redirect:/sensor/viewsensor?page=0&size=" + this.env.getRequiredProperty("paging.numitems"));
			this.sensorService.deleteSensor(id);			
		} catch (Exception ex) {
			log.debug("Error in deleting sensor", ex);
			modelAndView.addObject("MESSAGE_KEY", "系统发生故障，请跟Berry联系");
		}
		return modelAndView;
	}
	

}

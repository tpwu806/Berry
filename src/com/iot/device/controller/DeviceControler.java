package com.iot.device.controller;

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
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iot.exceptions.DaoFinderException;
import com.iot.common.utilities.Pager;
import com.iot.device.domain.Device;
import com.iot.device.dto.DeviceDO;
import com.iot.device.service.DeviceService;
import com.iot.sensor.service.SensorService;

/**
 * @author wutongpeng
 * 设备
 */


@Controller
public class DeviceControler {
	static final Logger log = LoggerFactory.getLogger(DeviceControler.class);
	
	@Resource
	private Environment env;
	
	@Autowired
	private SensorService sensorService;
	
	@Autowired
	private DeviceService deviceService;
		
	
	/**
	 * 设备列表
	 *
	 */
	@RequestMapping(value = { "/device/viewdevice" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView viewAllDevice(@ModelAttribute("deviceList") Object deviceList, Pageable pgble,
			HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("device/list-of-device");
		Integer deviceNum;
		try {
			if ((deviceList != null) && ((deviceList instanceof Pager))) {
				if (((Pager) deviceList).hasContent()) {
					Pager<DeviceDO> page = (Pager) deviceList;
					modelAndView.addObject("deviceList", page);
				} else {
					modelAndView.addObject("MESSAGE_KEY", "没有搜索结果");
					deviceNum=0;
					modelAndView.addObject("deviceNum", deviceNum);
				}
			} else {
				Page<DeviceDO> searchResults = this.deviceService.retrieveAllDevice(pgble);
				if ((searchResults != null) && (searchResults.hasContent())) {	
					String url = request.getContextPath() + "/device/viewdevice?";
					Pager<DeviceDO> page = new Pager<DeviceDO>(searchResults, url);
					modelAndView.addObject("deviceList", page);
					deviceNum=page.getTotalElements();
					modelAndView.addObject("deviceNum", deviceNum);
					
					
				}
			}
		} catch (Exception ex) {
			log.debug("Error retrieving list device search results or retrieving all device", ex);
			modelAndView.addObject("MESSAGE_KEY", "系统发生故障，请跟Berry联系");
		}
		return modelAndView;
	}
	

	/**
	 * 获得添加设备表单
	 *
	 */
	@RequestMapping(value = { "/device/newdeviceform" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView viewCreateDeviceForm() {
		ModelAndView modelAndView = new ModelAndView("device/add_device_form");
		
		DeviceDO noticeObject = new DeviceDO();
		modelAndView.addObject("deviceForm", noticeObject);
		
		return modelAndView;
	}
	
	/**
	 * 保存添加设备表单
	 *
	 */
	@RequestMapping(value = { "/device/newdeviceform/newdevice" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public String addNewDevice(@Valid @ModelAttribute("deviceForm") DeviceDO deviceForm, BindingResult bindingResult,
			Model model, RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = null;
		try {
			if (bindingResult.hasErrors()) {
				return "device/add_device_form";
			}
			Device np = this.deviceService.createDevice(deviceForm);
		} catch (Exception ex) {
			log.debug("Error when creating new device article", ex);
			redirectAttributes.addFlashAttribute("MESSAGE_KEY", "系统发生故障，请跟Berry联系");
		}
		return "redirect:/device/viewdevice?page=0&size=" + this.env.getRequiredProperty("paging.numitems");
	}
	
	/**
	 * 获得修改设备表单
	 *
	 */
	@RequestMapping(value = { "/device/editdeviceform/{deviceId}" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView editDevice(@PathVariable Integer deviceId) {
		ModelAndView modelAndView = new ModelAndView("device/edit-device-form");
		try {

			DeviceDO deviceObject = this.deviceService.getDeviceDetailById(deviceId);
			modelAndView.addObject("deviceForm", deviceObject);			
		} catch (Exception ex) {
			log.debug("Error in finding news article to display edit device form", ex);
			modelAndView.addObject("MESSAGE_KEY", "系统发生故障，请跟Berry联系");
		}
		return modelAndView;
	}
	
	/**
	 * 保存修改设备表单
	 *
	 */
	@RequestMapping(value = { "/device/editdeviceform/editdevice/{deviceId}" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public ModelAndView viewEditDeviceForm(@PathVariable Integer deviceId, @ModelAttribute("deviceForm") DeviceDO deviceForm) {
		ModelAndView modelAndView = null;
		try {
			Device nn = this.deviceService.updateDevice(deviceForm);
			//Integer noticeId = nn.getId();

			modelAndView = new ModelAndView("redirect:/device/viewdevice/viewdevicearticle/" + deviceId);
		} catch (Exception ex) {
			log.debug("Error in finding news article to use in editing device article", ex);
			modelAndView.addObject("MESSAGE_KEY", "系统发生故障，请跟Berry联系");
		}
		return modelAndView;
	}
	
	/**
	 * 删除设备
	 *
	 */
	@RequestMapping(value = { "/device/deletedevice/{id}" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView deleteDevice(@PathVariable Integer id) {
		ModelAndView modelAndView = null;
		try {
			modelAndView = new ModelAndView(
					"redirect:/device/viewdevice?page=0&size=" + this.env.getRequiredProperty("paging.numitems"));
			this.deviceService.deleteDevice(id);			
		} catch (Exception ex) {
			log.debug("Error in deleting device", ex);
			modelAndView.addObject("MESSAGE_KEY", "系统发生故障，请跟Berry联系");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = { "/device/searchdevice" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	public String searchDevice(@RequestParam("searchKey") String searchKey, @RequestParam("devicetype") String devicetype,
			Pageable pgble, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		try {
			String pSearchKey = StringUtils.isEmpty(searchKey) ? "" : searchKey;
			String pDevicetype = StringUtils.isEmpty(devicetype) ? "" : devicetype;

			Page<DeviceDO> searchResults = this.deviceService.searchDeviceContent(pSearchKey, pDevicetype, pgble);
			Integer deviceNum;
			
			if ((searchResults != null) && (searchResults.hasContent())) {
				String url = request.getContextPath() + "/device/searchdevice?searchKey=" + pSearchKey + "&devicetype="
						+ pDevicetype + "&";

				Pager<DeviceDO> page = new Pager(searchResults, url);

				redirectAttributes.addFlashAttribute("deviceList", page);
				deviceNum=page.getTotalElements();
			} else {
				Pager<DeviceDO> page = new Pager(null, null);

				redirectAttributes.addFlashAttribute("deviceList", page);
				deviceNum=0;
			}
			redirectAttributes.addFlashAttribute("deviceNum", deviceNum);
			redirectAttributes.addFlashAttribute("searchKey", pSearchKey);
			redirectAttributes.addFlashAttribute("devicetype", pDevicetype);
		} catch (Exception ex) {
			log.debug("Error in searching for device article", ex);
			System.out.print(ex);
			redirectAttributes.addFlashAttribute("MESSAGE_KEY", "系统发生故障，请跟Berry联系");
		}
		return "redirect:/device/viewdevice?page=" + pgble.getPageNumber() + "&size=" + pgble.getPageSize();
	}
	
	@RequestMapping(value = { "/device/viewdevice/viewdevicearticle/{deviceId}" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView viewDeviceArticle(@PathVariable Integer deviceId) {
		ModelAndView modelAndView = new ModelAndView("device/view-device-article");
		try {
			DeviceDO noticeObject = this.deviceService.getDeviceDetailById(deviceId);
			modelAndView.addObject("deviceForm", noticeObject);
		} catch (Exception ex) {
			log.debug("Error finding news article to display", ex);
			modelAndView.addObject("MESSAGE_KEY", "系统发生故障，请跟Berry联系");
		}
		return modelAndView;
	}
	

}

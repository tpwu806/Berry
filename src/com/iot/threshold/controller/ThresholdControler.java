package com.iot.threshold.controller;

import javax.annotation.Resource;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iot.threshold.domain.Threshold;
import com.iot.threshold.dto.ThresholdDO;
import com.iot.threshold.service.ThresholdService;

/**
 * @author wutongpeng 201605062115
 * 控制
 */

@Controller
public class ThresholdControler {
	static final Logger log = LoggerFactory.getLogger(ThresholdControler.class);
	
	@Resource
	private Environment env;	
	@Autowired
	private ThresholdService thresholdService;
	
	/**
	 * 获得修改阈值表单
	 *
	 */
	@RequestMapping(value = { "/threshold/vieweditthreshold" }, method = { 
			org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView viewEditThreshold() {
		ModelAndView modelAndView = null;
		try {	
			ThresholdDO thresholdObject = this.thresholdService.getThresholdDetail();
			if(thresholdObject!=null){
				modelAndView = new ModelAndView("threshold/edit-threshold-form");
				modelAndView.addObject("thresholdForm", thresholdObject);		
			}else{
				modelAndView = new ModelAndView("threshold/add-threshold-form");				
				ThresholdDO thresholdObjects = new ThresholdDO();
				modelAndView.addObject("thresholdForm", thresholdObjects);
			}
		
		} catch (Exception ex) {
			log.debug("Error in finding news article to display edit device form", ex);
			modelAndView.addObject("MESSAGE_KEY", "系统发生故障，请跟Berry联系");
		}
		return modelAndView;
	}

	/**
	 * 保存修改阈值表单
	 *
	 */
	@RequestMapping(value = { "/threshold/editthreshold" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public ModelAndView editThresholdForm(@ModelAttribute("thresholdForm") ThresholdDO thresholdForm) {
		ModelAndView modelAndView = null;
		try {
			Threshold nn = this.thresholdService.updateDevice(thresholdForm);
			//Integer noticeId = nn.getId();
	
			modelAndView = new ModelAndView("redirect:threshold/vieweditthreshold");
		} catch (Exception ex) {
			log.debug("Error in finding news article to use in editing device article", ex);
			modelAndView.addObject("MESSAGE_KEY", "系统发生故障，请跟Berry联系");
		}
		return modelAndView;
	}

	
	/**
	 * 保存添加阈值表单
	 *
	 */
	@RequestMapping(value = { "/threshold/newthresholdform/newthreshold" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public String addNewThreshold(@Valid @ModelAttribute("thresholdForm") ThresholdDO thresholdForm, BindingResult bindingResult,
			Model model, RedirectAttributes redirectAttributes) {
		try {
			if (bindingResult.hasErrors()) {
				return "threshold/edit-threshold-form";
			}
			Threshold np = this.thresholdService.createThreshold(thresholdForm);
		} catch (Exception ex) {
			log.debug("Error when creating new device article", ex);
			redirectAttributes.addFlashAttribute("MESSAGE_KEY", "系统发生故障，请跟Berry联系");
		}
		return "redirect:/threshold/vieweditthreshold";
	}
}

package com.warehouse.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.warehouse.excel.WhUserTypeExcelView;
import com.warehouse.model.WhUserType;
import com.warehouse.pdf.WhUserTypePdfView;
import com.warehouse.service.IWhUserTypeService;
import com.warehouse.util.EmailUtil;
import com.warehouse.util.WhUserTypeChartUtil;

import jakarta.mail.MessagingException;
import jakarta.servlet.ServletContext;

@Controller
@RequestMapping("/wh")
public class WhUserTypeController {
	
	@Autowired
	private EmailUtil emailUtil;
	
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private WhUserTypeChartUtil chartUtil;
	
	@Autowired
	private IWhUserTypeService whUserTypeService;
	
	@GetMapping("/register")
	public String registerWhUser(@ModelAttribute WhUserType whUserType, Model model) {
		model.addAttribute("whUser", whUserType);
		return "wh/register";
	}
	
	@PostMapping("/save")
	public String saveWhUser(@ModelAttribute WhUserType whUserType, RedirectAttributes redirectAttributes) {
		String id = whUserTypeService.insertWhUserType(whUserType);
		//Sending email
		new Thread(()->{
			try {
				emailUtil.sendEmai(whUserType.getUserEmail(),
								  "Success - Registration",
								  "Welcome user " + whUserType.getUserCode() + ".\n\nYour user id : " + id);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}).start();
		redirectAttributes.addFlashAttribute("message", "Warehouse user is saved with id '" + id + "' successfully...!");
		return "redirect:data";
	}
	
	@GetMapping("/data")
	public String whUserData(Model model) {
		model.addAttribute("userData", whUserTypeService.getWhUserData());
		return "wh/data";
	}
	
	@GetMapping("/edit")
	public String editWhUserType(@RequestParam String id, Model model) {
		model.addAttribute("whUser", whUserTypeService.getOneWhUserType(id));
		return "wh/edit";
	}
	
	@PostMapping("/update")
	public String updateWhUser(@ModelAttribute WhUserType whUserType, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("message", whUserTypeService.updateWhUserType(whUserType));
		return "redirect:data";
	}
	
	@GetMapping("/delete")
	public String deleteWhUserType(@RequestParam String id, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("message", whUserTypeService.deleteWhUserType(id));
		return "redirect:data";
	}
	
	@GetMapping("/excel")
	public ModelAndView exportToExcel(ModelAndView modelAndView) {
		modelAndView.setView(new WhUserTypeExcelView());
		modelAndView.addObject("data", whUserTypeService.getWhUserData());
		return modelAndView;
	}
	
	@GetMapping("/pdf")
	public ModelAndView exportToPdf(ModelAndView modelAndView) {
		modelAndView.setView(new WhUserTypePdfView());
		modelAndView.addObject("data", whUserTypeService.getWhUserData());
		return modelAndView;
	}
	
	@GetMapping("/validateUserCode")
	public @ResponseBody String validateUserCode(@RequestParam String userCode) {
		return whUserTypeService.isUserCodeExist(userCode)?"<b>User code already exist</b>":"";
	}
	
	@GetMapping("/validateUserEmail")
	public @ResponseBody String validateUserEmail(@RequestParam String userEmail) {
		return whUserTypeService.isUserEmailExist(userEmail)?"<b>User email already exist</b>":"";
	}
	
	@GetMapping("/validateUserIdNumber")
	public @ResponseBody String validateUserIdNumber(@RequestParam String userIdNumber) {
		return whUserTypeService.isUserIdNumberExist(userIdNumber)?"<b>Id number already exist</b>":"";
	}
	
	@GetMapping("/charts")
	public String whUserTypeCharts() throws IOException {
		String path = servletContext.getRealPath("/");
		List<Object[]> data = whUserTypeService.getUserTypeAndCount();
		chartUtil.generatePieChart(path, data);
		chartUtil.generateBarChart(path, data);
		return "wh/charts";
	}

}

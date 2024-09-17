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

import com.warehouse.excel.OrderMethodExcelView;
import com.warehouse.model.OrderMethod;
import com.warehouse.pdf.OrderMethodPdfView;
import com.warehouse.service.IOrderMethodService;
import com.warehouse.util.OrderMethodChartUtils;

import jakarta.servlet.ServletContext;

@Controller
@RequestMapping("/order")
public class OrderMethodController {
	
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private OrderMethodChartUtils orderMethodChartUtils;
	
	@Autowired
	private IOrderMethodService orderMethodService;
	
	@GetMapping("/register")
	public String registerOrderMethod(@ModelAttribute OrderMethod orderMethod, Model model) {
		model.addAttribute("order", orderMethod);
		return "order/register";
	}
	
	@PostMapping("/save")
	public String saveOrderMethod(@ModelAttribute OrderMethod orderMethod, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("message", orderMethodService.insertOrderMethod(orderMethod));
		return "redirect:data";
	}
	
	@GetMapping("/data")
	public String orderMethodData(Model model) {
		model.addAttribute("orderData", orderMethodService.getOrderMethodData());
		return "order/data";
	}
	
	@GetMapping("/edit")
	public String editOrderMethod(@RequestParam String id, Model model) {
		model.addAttribute("order", orderMethodService.getOneOrderMethod(id));
		return "order/edit";
	}
	
	@PostMapping("/update")
	public String updateOrderMethod(@ModelAttribute OrderMethod orderMethod, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("message", orderMethodService.updateOrderMethod(orderMethod));
		return "redirect:data";
	}
	
	@GetMapping("/delete")
	public String deleteOrderMethod(@RequestParam String id, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("message", orderMethodService.deleteOrderMethod(id));
		return "redirect:data";
	}
	
	@GetMapping("/excel")
	public ModelAndView exportToExcel(ModelAndView mav) {
		mav.setView(new OrderMethodExcelView());
		mav.addObject("data", orderMethodService.getOrderMethodData());
		return mav;
	}
	
	@GetMapping("/pdf")
	public ModelAndView exportToPdf(ModelAndView mav) {
		mav.setView(new OrderMethodPdfView());
		mav.addObject("data", orderMethodService.getOrderMethodData());
		return mav;
	}
	
	@GetMapping("/validateOrderCode")
	public @ResponseBody String validateOrderCode(@RequestParam String orderCode) {
		return orderMethodService.isOrderCodeExist(orderCode)?"<b>Order code already exist</b>":"";
	}
	
	@GetMapping("/charts")
	public String orderMethodCharts() throws IOException {
		String path = servletContext.getRealPath("/");
		List<Object[]> data = orderMethodService.getOrderModeAndCount();
		orderMethodChartUtils.generatePieChart(path, data);
		orderMethodChartUtils.generateBarChart(path, data);
		return "order/charts";
	}

}

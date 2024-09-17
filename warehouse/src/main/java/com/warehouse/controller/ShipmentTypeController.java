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

import com.warehouse.excel.ShipmentTypeExcelView;
import com.warehouse.model.ShipmentType;
import com.warehouse.pdf.ShipmentTypePdfView;
import com.warehouse.service.IShipmentTypeService;
import com.warehouse.util.ShipmentTypeChartUtil;

import jakarta.servlet.ServletContext;

@Controller
@RequestMapping("/st")
public class ShipmentTypeController {
	
	@Autowired
	private IShipmentTypeService shipmentTypeService;
	
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private ShipmentTypeChartUtil shipmentTypeChartUtil;
	
	@GetMapping("/register")
	public String registerShipment(@ModelAttribute ShipmentType st, Model model) {
		model.addAttribute("st", st);
		return "st/register";
	}
	
	@PostMapping("/save")
	public String saveShipment(@ModelAttribute ShipmentType st, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("message", shipmentTypeService.insertShipmentType(st));
		return "redirect:data";
	}
	
	@GetMapping("/data")
	public String shipmentData(Model model) {
		model.addAttribute("stData", shipmentTypeService.getShipmentData());
		return "st/data";
	}
	
	@GetMapping("/edit")
	public String editShipmentType(@RequestParam String id, Model model) {
		model.addAttribute("st", shipmentTypeService.getShipmentById(id));
		return "st/edit";
	}
	
	@PostMapping("/update")
	public String updateShipmentType(@ModelAttribute ShipmentType st, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("message", shipmentTypeService.updateShipmentType(st));
		return "redirect:data";
	}
	
	@GetMapping("/delete")
	public String deleteShipmentType(@RequestParam String id, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("message", shipmentTypeService.deleteShipmentType(id));
		return "redirect:data";
	}
	
	@GetMapping("/excel")
	public ModelAndView exportToExcel(ModelAndView modelAndView) {
		modelAndView.setView(new ShipmentTypeExcelView());
		modelAndView.addObject("data", shipmentTypeService.getShipmentData());
		return modelAndView;
	}
	
	@GetMapping("/pdf")
	public ModelAndView exportToPdf(ModelAndView modelAndView) {
		modelAndView.setView(new ShipmentTypePdfView());
		modelAndView.addObject("data", shipmentTypeService.getShipmentData());
		return modelAndView;
	}
	
	@GetMapping("/validateShipmentCode")
	public @ResponseBody String validateShipmentCode(@RequestParam String shipmentCode) {
		return shipmentTypeService.isShipmentCodeExist(shipmentCode)?"<b>Shipment code already exist</b>":"";
	}
	
	@GetMapping("/charts")
	public String shipmentTypeChartView() throws IOException {
		List<Object[]> data = shipmentTypeService.getShipmentTypeAndCount();
		String path = servletContext.getRealPath("/");
		shipmentTypeChartUtil.generateBarChart(path, data);
		shipmentTypeChartUtil.generatePieChart(path, data);
		return "st/charts";
	}

}

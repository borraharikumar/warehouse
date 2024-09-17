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

import com.warehouse.excel.UomExcelView;
import com.warehouse.model.Uom;
import com.warehouse.pdf.UomPdfView;
import com.warehouse.service.IUomService;
import com.warehouse.util.UomChartUtil;

import jakarta.servlet.ServletContext;

@Controller
@RequestMapping("/uom")
public class UomController {
	
	@Autowired
	private UomChartUtil uomChartUtil;
	
	@Autowired
	public ServletContext servletContext;
	
	@Autowired
	private IUomService uomService;
	
	@GetMapping("/register")
	public String registerUom(Model model) {
		model.addAttribute("uom", new Uom());
		return "uom/register";
	}
	
	@PostMapping("/save")
	public String saveUom(@ModelAttribute Uom uom, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("message", uomService.insertUom(uom));
		return "redirect:data";
	}
	
	@GetMapping("/data")
	public String uomData(Model model) {
		model.addAttribute("uomData", uomService.getUomData());
		return "uom/data";
	}
	
	@GetMapping("/delete")
	public String deleteUom(@RequestParam String id, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("message", uomService.deleteUom(id));
		return "redirect:data";
	}
	
	@GetMapping("/edit")
	public String editUom(@RequestParam String id, Model model) {
		model.addAttribute("uom", uomService.getUom(id));
		return "uom/edit";
	}
	
	@PostMapping("/update")
	public String updateUom(@ModelAttribute Uom uom, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("message", uomService.updateUom(uom));
		return "redirect:data";
	}
	
	@GetMapping("/excel")
	public ModelAndView exportToExcel(ModelAndView modelAndView) {
		modelAndView.setView(new UomExcelView());
		modelAndView.addObject("data", uomService.getUomData());
		return modelAndView;
	}
	
	@GetMapping("/pdf")
	public ModelAndView exportToPdf(ModelAndView modelAndView) {
		modelAndView.setView(new UomPdfView());
		modelAndView.addObject("data", uomService.getUomData());
		return modelAndView;
	}
	
	@GetMapping("/validateUomModel")
	public @ResponseBody String validateUomModel(@RequestParam String uomModel) {
		return uomService.isUomModelExist(uomModel)?"<b>UOM model already exist</b>":"";
	}
	
	
	  @GetMapping("/charts")
	  public String getMethodName() throws IOException {
		  List<Object[]> data = uomService.getUomTypeCount();
		  String path = servletContext.getRealPath("/");
		  uomChartUtil.createPieChart(path, data);
		  uomChartUtil.createBarChart(path, data);
		  return "uom/charts";
	  }
	 
	

}

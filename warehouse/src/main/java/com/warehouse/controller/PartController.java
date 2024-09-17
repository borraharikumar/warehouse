package com.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.warehouse.excel.PartExcelView;
import com.warehouse.model.Part;
import com.warehouse.pdf.PartPdfView;
import com.warehouse.service.IOrderMethodService;
import com.warehouse.service.IPartService;
import com.warehouse.service.IUomService;

@Controller
@RequestMapping("/part")
public class PartController {

	@Autowired
	private IPartService partService;
	
	@Autowired
	private IUomService uomService;
	
	@Autowired
	private IOrderMethodService orderMethodService;
	
	private void addDynamicUiComponents(Model model) {
		model.addAttribute("uoms", uomService.getUomIdAndModel());
		model.addAttribute("omSales", orderMethodService.getOrderIdAndCodeByMode("SALE"));
		model.addAttribute("omPurchases", orderMethodService.getOrderIdAndCodeByMode("PURCHASE"));
	}
	
	@GetMapping("/register")
	public String partRegister(@ModelAttribute Part part, Model model) {
		model.addAttribute("part", part);
		addDynamicUiComponents(model);
		return "part/register";
	}
	
	@PostMapping("/save")
	public String partSave(@ModelAttribute Part part, RedirectAttributes redirectAttributes) {
		String id = partService.insertPart(part);
		redirectAttributes.addFlashAttribute("message", "Part saved with id '" + id + "' successfully...!");
		return "redirect:data";
	}
	
	@GetMapping("/data")
	public String partData(Model model) {
		model.addAttribute("partData", partService.getPartData());
		return "part/data";
	}
	
	@GetMapping("/edit")
	public String editPart(@RequestParam String id, Model model) {
		model.addAttribute("part", partService.getOnePart(id));
		addDynamicUiComponents(model);
		return "part/edit";
	}
	
	@PostMapping("/update")
	public String updatePart(@ModelAttribute Part part, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("message", partService.updatePart(part));
		return "redirect:data";
	}
	
	@GetMapping("/delete")
	public String deletePart(@RequestParam String id, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("message", partService.deletePart(id));
		return "redirect:data";
	}
	
	@GetMapping("/excel")
	public ModelAndView exportToExcel(ModelAndView modelAndView) {
		modelAndView.setView(new PartExcelView());
		modelAndView.addObject("data", partService.getPartData());
		return modelAndView;
	}
	
	@GetMapping("/pdf")
	public ModelAndView exportToPdf(ModelAndView modelAndView) {
		modelAndView.setView(new PartPdfView());
		modelAndView.addObject("data", partService.getPartData());
		return modelAndView;
	}
	
}

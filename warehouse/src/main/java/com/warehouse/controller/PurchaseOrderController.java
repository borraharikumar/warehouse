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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.warehouse.excel.PurchaseOrderExcelView;
import com.warehouse.model.PurchaseOrder;
import com.warehouse.pdf.PurchaseOrderPdfView;
import com.warehouse.service.IPurchaseOrderService;
import com.warehouse.service.IShipmentTypeService;
import com.warehouse.service.IWhUserTypeService;
import com.warehouse.util.PurchaseOrderChartUtil;

import jakarta.servlet.ServletContext;

@Controller
@RequestMapping("/po")
public class PurchaseOrderController {
	
	@Autowired
	private IPurchaseOrderService purchaseOrderService;
	
	@Autowired
	private IShipmentTypeService shipmentTypeService;
	
	@Autowired
	private IWhUserTypeService whUserTypeService;
	
	@Autowired
	private PurchaseOrderChartUtil purchaseOrderChartUtil;
	
	@Autowired
	private ServletContext servletContext;
	
	private void addDynamicUiComponents(Model model) {
		model.addAttribute("st", shipmentTypeService.getShipmentIdAndCode());
		model.addAttribute("wut", whUserTypeService.getUserIdAndCodeByType("VENDOR"));
	}
	
	@GetMapping("/register")
	public String registerPurchaseOrder(@ModelAttribute PurchaseOrder purchaseOrder, Model model) {
		model.addAttribute("po", purchaseOrder);
		addDynamicUiComponents(model);
		return "po/register";
	}
	
	@PostMapping("/save")
	public String savePurchaseOrder(@ModelAttribute PurchaseOrder purchaseOrder, RedirectAttributes redirectAttributes) {
		String id = purchaseOrderService.insertPurchaseOrder(purchaseOrder);
		redirectAttributes.addFlashAttribute("message", "Purchase order saved with id " + id + "successfully...!");
		return "redirect:data";
	}
	
	@GetMapping("/data")
	public String purchaseOrderData(Model model) {
		model.addAttribute("poData", purchaseOrderService.getPurchaseOrderData());
		return "po/data";
	}
	
	@GetMapping("/edit")
	public String editPurchaseOrder(@RequestParam String id, Model model) {
		model.addAttribute("po", purchaseOrderService.getOnePurchaseOrder(id));
		addDynamicUiComponents(model);
		return "po/edit";
	}
	
	@GetMapping("/delete")
	public String deletePurchaseOrder(@RequestParam String id, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("message", purchaseOrderService.deletePurchaseOrder(id));
		return "redirect:data";
	}
	
	@GetMapping("/excel")
	public ModelAndView exportToExcel(ModelAndView modelAndView) {
		modelAndView.setView(new PurchaseOrderExcelView());
		modelAndView.addObject("data", purchaseOrderService.getPurchaseOrderData());
		return modelAndView;
	}
	
	@GetMapping("/pdf")
	public ModelAndView exportToPdf(ModelAndView modelAndView) {
		modelAndView.setView(new PurchaseOrderPdfView());
		modelAndView.addObject("data", purchaseOrderService.getPurchaseOrderData());
		return modelAndView;
	}
	
	@GetMapping("/charts")
	public String purchaseOrderChartView() throws IOException {
		String path = servletContext.getRealPath("/");
		List<Object[]> data = purchaseOrderService.getPurchaseOrderCountByQltyCheck();
		purchaseOrderChartUtil.generatePieChart(path, data);
		purchaseOrderChartUtil.generateBarChart(path, data);
		return "po/charts";
	}

}

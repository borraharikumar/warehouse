package com.warehouse.controller;

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

import com.warehouse.model.SaleOrder;
import com.warehouse.model.SaleOrderDtl;
import com.warehouse.pdf.SaleOrderInvoicePdfView;
import com.warehouse.service.IPartService;
import com.warehouse.service.ISaleOrderService;
import com.warehouse.service.IShipmentTypeService;
import com.warehouse.service.IWhUserTypeService;

@Controller
@RequestMapping("/so")
public class SaleOrderController {
	
	@Autowired
	private ISaleOrderService saleOrderService;
	
	@Autowired
	private IShipmentTypeService shipmentTypeService;
	
	@Autowired
	private IWhUserTypeService whUserTypeService;
	
	@Autowired
	private IPartService partService;
	
	private void addDynamicUiComponentsForRegister(Model model) {
		model.addAttribute("sts", shipmentTypeService.getShipmentIdAndCode());
		model.addAttribute("wuts", whUserTypeService.getUserIdAndCodeByType("CUSTOMER"));
	}
	
	//************************************ Screen#1 Operations ************************************//
	
	@GetMapping("/register")
	public String registerSaleOrder(@ModelAttribute SaleOrder saleOrder, Model model) {
		saleOrder.setStatus("SALE-OPEN");
		model.addAttribute("so", saleOrder);
		addDynamicUiComponentsForRegister(model);
		return "so/register";
	}
	
	@PostMapping("/save")
	public String saveSaleOrder(@ModelAttribute SaleOrder saleOrder, RedirectAttributes redirectAttributes) {
		SaleOrder so = saleOrderService.saveSaleOrder(saleOrder);
		redirectAttributes.addFlashAttribute("message", "SaleOrder saved with id '" + so.getId() + "' successfully...!");
		return "redirect:data";
	}
	
	@GetMapping("/edit")
	public String editSaleOrder(@RequestParam String id, Model model) {
		model.addAttribute("so", saleOrderService.getOneSaleOrder(id));
		addDynamicUiComponentsForRegister(model);
		return "so/edit";
	}
	
	@PostMapping("/update")
	public String updateSaleOrder(@ModelAttribute SaleOrder saleOrder, RedirectAttributes redirectAttributes) {
		SaleOrder so = saleOrderService.updateSaleOrder(saleOrder);
		redirectAttributes.addFlashAttribute("message", "SaleOrder with id '" + so.getId() + "' updated successfully...!");
		return "redirect:data";
	}
	
	@GetMapping("/delete")
	public String deleteSaleOrder(@RequestParam String id, RedirectAttributes redirectAttributes) {
		saleOrderService.deleteSaleOrder(id);
		redirectAttributes.addFlashAttribute("message", "SaleOrder with id '" + id + "' deleted successfully...!");
		return "redirect:data";
	}
	
	@GetMapping("/data")
	public String saleOrderData(Model model) {
		model.addAttribute("soData", saleOrderService.getSaleOrderData());
		return "so/data";
	}
	
	//************************************ Validation Operations ************************************//
	
	@GetMapping("/validate")
	public @ResponseBody String validateValues(@RequestParam String type, @RequestParam String value) {
		return saleOrderService.isValueAvailable(type, value)?"<b>"+type+" '"+value+"' already exist</b>":"";
	}
	
	//************************************ Screen#2 Operations ************************************//
	
	@GetMapping("/info")
	public String saleOrderInfo(@RequestParam String id, Model model) {
		SaleOrderDtl soDtl = new SaleOrderDtl();
		soDtl.setSo(saleOrderService.getOneSaleOrder(id));
		model.addAttribute("soDtl", soDtl);
		model.addAttribute("parts", partService.getPartIdAndCode());
		model.addAttribute("soDtlData", saleOrderService.getSaleOrderDtlData(id));
		return "so/info";
	}
	
	@GetMapping("/addPart")
	public String addPart(@ModelAttribute SaleOrderDtl dtl) {
		saleOrderService.addSaleOrderDtl(dtl);
		saleOrderService.updateStatus(dtl.getSo().getId(), "SALE-READY");
		return "redirect:info?id="+dtl.getSo().getId();
	}
	
	@GetMapping("/removePart")
	public String removePart(@RequestParam String soId, @RequestParam Integer sodId) {
		saleOrderService.removeSaleOrderDtl(sodId);
		if(saleOrderService.getSaleOrderDtlData(soId)==null)
			saleOrderService.updateStatus(soId, "SALE-OPEN");
		return "redirect:info?id="+soId;
	}
	
	@GetMapping("/placeOrder")
	public String placeOrder(@RequestParam String soId) {
		saleOrderService.updateStatus(soId, "SALE-CONFIRM");
		return "redirect:info?id="+soId;
	}
	
	@GetMapping("/cancelOrder")
	public String cancelOrder(@RequestParam String soId) {
		saleOrderService.updateStatus(soId, "SALE-CANCEL");
		return "redirect:info?id="+soId;
	}
	
	@GetMapping("/generateInvoice")
	public String generateInvoice(@RequestParam String soId) {
		saleOrderService.updateStatus(soId, "SALE-INVOICED");
		return "redirect:info?id="+soId;
	}
	
	@GetMapping("/printInvoice")
	public ModelAndView printInvoice(@RequestParam String soId, ModelAndView modelAndView) {
		modelAndView.setView(new SaleOrderInvoicePdfView());
		modelAndView.addObject("so", saleOrderService.getOneSaleOrder(soId));
		modelAndView.addObject("soDtl", saleOrderService.getSaleOrderDtlData(soId));
		return modelAndView;
	}

}

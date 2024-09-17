package com.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.warehouse.model.PurchaseOrder;
import com.warehouse.service.IPurchaseOrderService;
import com.warehouse.service.IShipmentTypeService;
import com.warehouse.service.IWhUserTypeService;

@Controller
@RequestMapping("/po")
public class PurchaseOrderController {
	
	@Autowired
	private IPurchaseOrderService purchaseOrderService;
	
	@Autowired
	private IShipmentTypeService shipmentTypeService;
	
	@Autowired
	private IWhUserTypeService whUserTypeService;
	
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

}

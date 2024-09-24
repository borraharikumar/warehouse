package com.warehouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.warehouse.model.Grn;
import com.warehouse.model.PurchaseOrderDtl;
import com.warehouse.service.IGrnService;
import com.warehouse.service.IPurchaseOrderService;

@Controller
@RequestMapping("/grn")
public class GrnController {

	@Autowired
	private IGrnService grnService;
	
	@Autowired
	private IPurchaseOrderService purchaseOrderService;
	
	private void addDynamicUiComponents(Model model) {
		model.addAttribute("pos", purchaseOrderService.getOrderIdAndCodeByOrderStatus("INVOICED"));
	}
	
	@GetMapping("/register")
	public String registerGrn(@ModelAttribute Grn grn, Model model) {
		model.addAttribute("grn", grn);
		addDynamicUiComponents(model);
		return "grn/register";
	}
	
	@PostMapping("/save")
	public String saveGrn(@ModelAttribute Grn grn, RedirectAttributes redirectAttributes) {
		grn = grnService.insertGrn(grn);
		purchaseOrderService.changePurchaseOrderStatus(grn.getPo().getId(), "RECEIVED");
		List<PurchaseOrderDtl> orderDtls = purchaseOrderService.getPurchaseOrderDtlDataByPoId(grn.getPo().getId());
		grnService.insertGrnDtls(orderDtls, grn);
		redirectAttributes.addFlashAttribute("message", "GRN saved with id '" + grn.getId() + "' successfully...!");
		return "redirect:data";
	}
	
	@GetMapping("/data")
	public String grnData(Model model) {
		model.addAttribute("grnData", grnService.getGrnData());
		return "grn/data";
	}
	
	//********************** SCREEN#2 ***********************//
	
	@GetMapping("/info")
	public String grnInfo(@RequestParam String grnId, Model model) {
		model.addAttribute("grn", grnService.getOneGrn(grnId));
		model.addAttribute("grnDtls", grnService.getGrnDtlsByGrnId(grnId));
		return "grn/info";
	}
	
	@GetMapping("/changeStatus")
	public String changeStatus(@RequestParam String status, @RequestParam String grnId, @RequestParam Integer grnDtlId) {
		grnService.updateGrnDtlStatusById(status, grnDtlId);
		return "redirect:info?grnId="+grnId;
	}
	
}

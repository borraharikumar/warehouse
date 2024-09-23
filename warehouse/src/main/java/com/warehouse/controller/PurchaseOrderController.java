package com.warehouse.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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

import com.warehouse.excel.PurchaseOrderExcelView;
import com.warehouse.model.PurchaseOrder;
import com.warehouse.model.PurchaseOrderDtl;
import com.warehouse.pdf.PurchaseOrderInvoicePdfView;
import com.warehouse.pdf.PurchaseOrderPdfView;
import com.warehouse.service.IPartService;
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
	private IPartService partService;
	
	@Autowired
	private ServletContext servletContext;
	
	private void addDynamicUiComponents(Model model) {
		model.addAttribute("st", shipmentTypeService.getShipmentIdAndCode());
		model.addAttribute("wut", whUserTypeService.getUserIdAndCodeByType("VENDOR"));
	}
	
	@GetMapping("/register")
	public String registerPurchaseOrder(@ModelAttribute PurchaseOrder purchaseOrder, Model model) {
		purchaseOrder.setStatus("OPEN");
		model.addAttribute("po", purchaseOrder);
		addDynamicUiComponents(model);
		return "po/register";
	}
	
	@PostMapping("/save")
	public String savePurchaseOrder(@ModelAttribute PurchaseOrder purchaseOrder, RedirectAttributes redirectAttributes) {
		String id = purchaseOrderService.insertPurchaseOrder(purchaseOrder);
		redirectAttributes.addFlashAttribute("message", "Purchase order saved with id '" + id + "' successfully...!");
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
	
	@GetMapping("/validateOrderCode")
	public @ResponseBody String validateOrderCode(String orderCode) {
		return purchaseOrderService.isOrderCodeExist(orderCode)?"<b>Order code already exist</b>":"";
	}
	
	@GetMapping("/validateRefNumber")
	public @ResponseBody String validateRefNumber(String refNumber) {
		System.err.println("Validation");
		return purchaseOrderService.isRefNumberExist(refNumber)?"<b>Reference number already exist</b>":"";
	}

	//************************************* End points for PurchaseOrderDtl *************************************//
	@GetMapping("/parts")
	public String addPartsPage(@RequestParam String id, Model model) {
		//Get PurchaseOrder object using id
		PurchaseOrder po = purchaseOrderService.getOnePurchaseOrder(id);
		//Create PurchaseOrderDtl object & set PurchaseOrder to it
		PurchaseOrderDtl purchaseOrderDtl = new PurchaseOrderDtl();
		purchaseOrderDtl.setPurchaseOrder(po);
		//Get list of PurchaseOrderDtl objects from database
		List<PurchaseOrderDtl> purchaseOrderDtlData = purchaseOrderService.getPurchaseOrderDtlDataByPoId(id);
		//If purchaseOrderDtlData is not null, set PurchaseOrder status to PICKING
		//Remove selected parts from dynamic drop-down
		Map<String, String> parts = partService.getPartIdAndCode();
		if(purchaseOrderDtlData!=null) {
			purchaseOrderDtlData.forEach(x->{
				String partId = x.getPart().getId();
				if(parts.containsKey(partId.trim())) {
					parts.remove(partId);
				}
			});
		} else {
			purchaseOrderService.changePurchaseOrderStatus(id, "OPEN");
		}
		//Add Attributes to the Model object
		model.addAttribute("purchaseOrderDtl", purchaseOrderDtl);
		model.addAttribute("parts", parts);
		model.addAttribute("purchaseOrderDtlData", purchaseOrderDtlData);
		return "po/parts";
	}

	@PostMapping("/addPart")
	public String addPart(@ModelAttribute PurchaseOrderDtl purchaseOrderDtl) {
		String id = purchaseOrderDtl.getPurchaseOrder().getId();
		purchaseOrderService.inserPurchaseOrderDtl(purchaseOrderDtl);
		purchaseOrderService.changePurchaseOrderStatus(id, "PICKING");
		return "redirect:parts?id=" + id;
	}
	
	@GetMapping("/removePart")
	public String removePart(@RequestParam Long podId, @RequestParam String poId) {
		purchaseOrderService.deletePurchaseOrderDtl(podId);
		return "redirect:parts?id=" + poId;
	}
	
	@GetMapping("/placeOrder")
	public String placeOrder(@RequestParam String id) {
		purchaseOrderService.changePurchaseOrderStatus(id, "ORDERED");
		return "redirect:parts?id=" + id;
	}
	
	@GetMapping("/generateInvoice")
	public String generateInvoice(@RequestParam String id) {
		purchaseOrderService.changePurchaseOrderStatus(id, "INVOICED");
		return "redirect:parts?id=" + id;
	}
	
	@GetMapping("/printInvoice")
	public ModelAndView printInvoice(@RequestParam String id, ModelAndView modelAndView) {
		modelAndView.addObject("po", purchaseOrderService.getOnePurchaseOrder(id));
		modelAndView.addObject("poDtl", purchaseOrderService.getPurchaseOrderDtlDataByPoId(id));
		modelAndView.setView(new PurchaseOrderInvoicePdfView());
		return modelAndView;
	}
	
	@GetMapping("/cancelOrder")
	public String cancelOrder(@RequestParam String id) {
		purchaseOrderService.changePurchaseOrderStatus(id, "CANCELED");
		return "redirect:data";
	}

}

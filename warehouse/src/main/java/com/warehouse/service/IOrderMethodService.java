package com.warehouse.service;

import java.util.List;
import java.util.Map;

import com.warehouse.model.OrderMethod;

public interface IOrderMethodService {
	
	public String insertOrderMethod(OrderMethod orderMethod);
	public OrderMethod getOneOrderMethod(String id);
	public String updateOrderMethod(OrderMethod orderMethod);
	public String deleteOrderMethod(String id);
	public List<OrderMethod> getOrderMethodData();
	
	public Boolean isOrderCodeExist(String orderCode);
	public List<Object[]> getOrderModeAndCount();
	public Map<String, String> getOrderIdAndCodeByMode(String orderMode);

}

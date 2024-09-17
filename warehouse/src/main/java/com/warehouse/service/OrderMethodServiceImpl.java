package com.warehouse.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warehouse.exception.OrderMethodNotFoundException;
import com.warehouse.model.OrderMethod;
import com.warehouse.repository.IOrderMethodRepository;
import com.warehouse.util.CollectionUtil;

@Service
public class OrderMethodServiceImpl implements IOrderMethodService {
	
	@Autowired
	private IOrderMethodRepository orderMethodRepository;

	@Override
	public String insertOrderMethod(OrderMethod orderMethod) {
		return "Order saved with id '" + orderMethodRepository.save(orderMethod).getId() + "' successfully...!";
	}

	@Override
	public OrderMethod getOneOrderMethod(String id) {
		return orderMethodRepository.findById(id)
				.orElseThrow(()->new OrderMethodNotFoundException("Order not found with id '" + id + "'"));
	}

	@Override
	public String updateOrderMethod(OrderMethod orderMethod) {
		if(getOneOrderMethod(orderMethod.getId())!=null) orderMethodRepository.save(orderMethod);
		return "Order with id '" + orderMethod.getId() + "' updated successfully...!";
	}

	@Override
	public String deleteOrderMethod(String id) {
		if(getOneOrderMethod(id)!=null) orderMethodRepository.deleteById(id);
		return "Order with id '" + id + "' deleted successfully...!";
	}

	@Override
	public List<OrderMethod> getOrderMethodData() {
		List<OrderMethod> list = orderMethodRepository.findAll();
		return list.isEmpty()?null:list;
	}

	@Override
	public Boolean isOrderCodeExist(String orderCode) {
		return orderMethodRepository.getOrderCodeCount(orderCode)==0?false:true;
	}

	@Override
	public List<Object[]> getOrderModeAndCount() {
		return orderMethodRepository.getOrderModeAndCount();
	}

	@Override
	public Map<String, String> getOrderIdAndCodeByMode(String orderMode) {
		return CollectionUtil.converListToMap(orderMethodRepository.getOrderIdAndCodeByMode(orderMode));
	}

}

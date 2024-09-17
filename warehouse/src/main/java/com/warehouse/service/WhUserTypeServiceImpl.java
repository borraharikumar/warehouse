package com.warehouse.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warehouse.exception.WhUserTypeNotFoundException;
import com.warehouse.model.WhUserType;
import com.warehouse.repository.IWhUserTypeRepository;
import com.warehouse.util.CollectionUtil;

@Service
public class WhUserTypeServiceImpl implements IWhUserTypeService {
	
	@Autowired
	private IWhUserTypeRepository whUserTypeRepository;

	@Override
	public String insertWhUserType(WhUserType whUserType) {
		return whUserTypeRepository.save(whUserType).getId();
	}

	@Override
	public WhUserType getOneWhUserType(String id) {
		return whUserTypeRepository.findById(id)
				.orElseThrow(()->new WhUserTypeNotFoundException("Warehouse user not found with id '" + id + "'"));
	}

	@Override
	public String updateWhUserType(WhUserType whUserType) {
		if(getOneWhUserType(whUserType.getId())!=null) whUserTypeRepository.save(whUserType);
		return "Warehouse user details with id '" + whUserType.getId() + "' updated successfully...!";
	}

	@Override
	public String deleteWhUserType(String id) {
		if(getOneWhUserType(id)!=null) whUserTypeRepository.deleteById(id);
		return "Warehouse user details with id '" + id + "' deleted successfully...!";
	}

	@Override
	public List<WhUserType> getWhUserData() {
		List<WhUserType> list = whUserTypeRepository.findAll();
		return list.isEmpty()?null:list;
	}

	@Override
	public Boolean isUserCodeExist(String userCode) {
		return whUserTypeRepository.getUserCodeCount(userCode)==0?false:true;
	}

	@Override
	public Boolean isUserEmailExist(String userEmail) {
		return whUserTypeRepository.getUserEmailCount(userEmail)>0;
	}

	@Override
	public Boolean isUserIdNumberExist(String userIdNumber) {
		return whUserTypeRepository.getUserIdNumberCount(userIdNumber)>0;
	}

	@Override
	public List<Object[]> getUserTypeAndCount() {
		return whUserTypeRepository.getUserTypeAndCount();
	}

	@Override
	public Map<String, String> getUserIdAndCodeByType(String type) {
		return CollectionUtil.converListToMap(whUserTypeRepository.getUserIdAndCodeByType(type));
	}

}

package com.warehouse.service;

import java.util.List;
import java.util.Map;

import com.warehouse.model.WhUserType;

public interface IWhUserTypeService {
	
	public String insertWhUserType(WhUserType whUserType);
	public WhUserType getOneWhUserType(String id);
	public String updateWhUserType(WhUserType whUserType);
	public String deleteWhUserType(String id);
	public List<WhUserType> getWhUserData();
	
	public Boolean isUserCodeExist(String userCode);
	public Boolean isUserEmailExist(String userEmail);
	public Boolean isUserIdNumberExist(String userIdNumber);
	public List<Object[]> getUserTypeAndCount();
	public Map<String, String> getUserIdAndCodeByType(String type);

}

package com.warehouse.service;

import java.util.List;
import java.util.Map;

import com.warehouse.model.Part;

public interface IPartService {
	
	public String insertPart(Part part);
	public Part getOnePart(String id);
	public String updatePart(Part part);
	public String deletePart(String id);
	public List<Part> getPartData();
	
	public Boolean isOrderCodeExist(String partCode);
	public Map<String, String> getPartIdAndCode();

}

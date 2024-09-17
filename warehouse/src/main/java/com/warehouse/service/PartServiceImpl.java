package com.warehouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warehouse.exception.PartNotFoundException;
import com.warehouse.model.Part;
import com.warehouse.repository.IPartRepository;

@Service
public class PartServiceImpl implements IPartService {
	
	@Autowired
	private IPartRepository partRepository;

	@Override
	public String insertPart(Part part) {
		return partRepository.save(part).getId();
	}

	@Override
	public Part getOnePart(String id) {
		return partRepository.findById(id)
				.orElseThrow(()->new PartNotFoundException("Part not found with id '" + id + "'"));
	}

	@Override
	public String updatePart(Part part) {
		if(getOnePart(part.getId())!=null) partRepository.save(part);
		return "Part with id '" + part.getId() + "' details updated successfully...!";
	}

	@Override
	public String deletePart(String id) {
		if(getOnePart(id)!=null) partRepository.deleteById(id);
		return "Part with id '" + id + "' details updated successfully...!";
	}

	@Override
	public List<Part> getPartData() {
		List<Part> list = partRepository.findAll();
		return list!=null?list:null;
	}

}

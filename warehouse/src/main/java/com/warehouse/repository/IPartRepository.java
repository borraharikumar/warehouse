package com.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.warehouse.model.Part;

public interface IPartRepository extends JpaRepository<Part, String> {

}

package com.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.warehouse.model.Grn;

public interface IGrnRepository extends JpaRepository<Grn, String> {

}

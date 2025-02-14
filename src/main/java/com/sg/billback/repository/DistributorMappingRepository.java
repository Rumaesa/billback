package com.sg.billback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sg.billback.model.DistributorFieldMapping;

public interface DistributorMappingRepository extends JpaRepository<DistributorFieldMapping, Integer> {

}

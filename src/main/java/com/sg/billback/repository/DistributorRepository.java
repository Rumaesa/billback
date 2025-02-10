package com.sg.billback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sg.billback.model.Distributor;

public interface DistributorRepository extends JpaRepository<Distributor, Integer> {

	public List<Distributor> findByStatus(String activeStatus);

}

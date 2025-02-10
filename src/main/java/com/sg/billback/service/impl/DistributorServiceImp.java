package com.sg.billback.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sg.billback.model.Distributor;
import com.sg.billback.repository.DistributorRepository;
import com.sg.billback.service.DistributorService;

@Service
public class DistributorServiceImp implements DistributorService{
	
	public static final String ACTIVE_STATUS = "active";
	
	@Autowired
	private DistributorRepository distributorRepository;
	
	@Override
	public List<Distributor> getDistributors() {
		List<Distributor> distributors = this.distributorRepository.findByStatus(ACTIVE_STATUS);
		return distributors;
	}
	

}

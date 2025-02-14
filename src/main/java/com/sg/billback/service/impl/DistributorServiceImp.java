package com.sg.billback.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sg.billback.model.Distributor;
import com.sg.billback.model.DistributorFieldMapping;
import com.sg.billback.repository.DistributorMappingRepository;
import com.sg.billback.repository.DistributorRepository;
import com.sg.billback.service.DistributorService;

@Service
public class DistributorServiceImp implements DistributorService{
	
	public static final String ACTIVE_STATUS = "active";
	
	@Autowired
	private DistributorRepository distributorRepository;
	@Autowired
	private DistributorMappingRepository distributorMappingRepository;
	
	@Override
	public List<Distributor> getDistributors() {
		List<Distributor> distributors = this.distributorRepository.findByStatus(ACTIVE_STATUS);
		return distributors;
	}

	@Override
	public DistributorFieldMapping createDistributorMapping(DistributorFieldMapping distributorFieldMapping, Integer distributorId) {
		System.out.println("create distributor mapping..."+ distributorFieldMapping);
		Optional<Distributor> distributor = this.distributorRepository.findById(distributorId);
		System.out.println("--------------------------- "+distributor.toString());
		if(distributor.isPresent()) {
			if(distributor.get().getIsMappingPresent().TRUE) {
				distributorFieldMapping.setDateModified(new Date());
			}
			else {
				distributorFieldMapping.setDateCreated(new Date());
			}
			distributorFieldMapping.setDistributor(distributor.get());
			DistributorFieldMapping savedDMR =  this.distributorMappingRepository.save(distributorFieldMapping);
			return savedDMR;
		}	else {
			throw new RuntimeException("Distibutor not found");
		}
		}
	

}

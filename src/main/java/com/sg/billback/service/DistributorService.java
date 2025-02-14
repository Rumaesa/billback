package com.sg.billback.service;

import java.util.List;

import com.sg.billback.model.Distributor;
import com.sg.billback.model.DistributorFieldMapping;

public interface DistributorService {

	public List<Distributor> getDistributors();
	public DistributorFieldMapping createDistributorMapping(DistributorFieldMapping distributorFieldMapping, Integer distributorId);
}

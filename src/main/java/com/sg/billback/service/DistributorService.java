package com.sg.billback.service;

import java.util.List;
import java.util.Optional;

import com.sg.billback.dto.DistributorDTO;
import com.sg.billback.dto.DistributorFieldMappingDTO;
import com.sg.billback.model.DistributorFieldMapping;

public interface DistributorService {

	public List<DistributorDTO> getDistributors();
	public Optional<DistributorFieldMappingDTO> getDistributorMapping(Integer mappingId);
	public DistributorFieldMappingDTO createDistributorMapping(DistributorFieldMapping distributorFieldMapping, Integer distributorId, Boolean create);
}

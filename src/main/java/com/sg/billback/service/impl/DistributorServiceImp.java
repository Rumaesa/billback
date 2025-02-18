package com.sg.billback.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sg.billback.dto.DistributorDTO;
import com.sg.billback.dto.DistributorFieldMappingDTO;
import com.sg.billback.model.Distributor;
import com.sg.billback.model.DistributorFieldMapping;
import com.sg.billback.repository.DistributorMappingRepository;
import com.sg.billback.repository.DistributorRepository;
import com.sg.billback.service.DistributorService;
import com.sg.billback.utility.DistributorMapper;

@Service
public class DistributorServiceImp implements DistributorService{
	
	public static final String ACTIVE_STATUS = "active";
	
	@Autowired
	private DistributorRepository distributorRepository;
	@Autowired
	private DistributorMappingRepository distributorMappingRepository;
	
	@Override
	public List<DistributorDTO> getDistributors() {
		List<Distributor> distributors = this.distributorRepository.findByStatus(ACTIVE_STATUS);
		return distributors.stream().map(DistributorMapper::mapToDistributorDTO).collect(Collectors.toList());
	}
	
	@Override
	public Optional<DistributorFieldMappingDTO> getDistributorMapping(Integer mappingId) {
		Optional<DistributorFieldMapping> distributorMapping = this.distributorMappingRepository.findById(mappingId);
		if (distributorMapping.isPresent()) {
	        DistributorFieldMappingDTO dto = new DistributorFieldMappingDTO();

	        // Manually map fields from entity to DTO
	        DistributorFieldMapping mapping = distributorMapping.get();
	        dto.setMappingId(mapping.getMappingId());
	        dto.setCustomerProductShippedToAddress(mapping.getCustomerProductShippedToAddress());
	        dto.setAmountCreditedPerItem(mapping.getAmountCreditedPerItem());
	        dto.setBillbackPartnerCustomerNumber(mapping.getBillbackPartnerCustomerNumber());
	        dto.setCustomerProductShippedToCity(mapping.getCustomerProductShippedToCity());
	        dto.setInvoiceShippedDate(mapping.getInvoiceShippedDate());
	        dto.setDistributorBilledShippedInvoiceNumber(mapping.getDistributorBilledShippedInvoiceNumber());
	        dto.setCustomerDistributorName(mapping.getCustomerDistributorName());
	        dto.setNoMapping(mapping.getNoMapping());
	        dto.setSgaPXCustomerUnitPrice(mapping.getSgaPXCustomerUnitPrice());
	        dto.setQuantityShipped(mapping.getQuantityShipped());
	        dto.setRebateClaim(mapping.getRebateClaim());
	        dto.setMaterialNumber(mapping.getMaterialNumber());
	        dto.setBillbackSgaPXDistributorCustomerCombination(mapping.getBillbackSgaPXDistributorCustomerCombination());
	        dto.setCustomerProductShippedToState(mapping.getCustomerProductShippedToState());
	        dto.setDistributorSgaInvoiceUOM(mapping.getDistributorSgaInvoiceUOM());
	        dto.setDistributorInvoiceSgaProductUnitPrice(mapping.getDistributorInvoiceSgaProductUnitPrice());
	        dto.setCustomerProductShippedToZipCode(mapping.getCustomerProductShippedToZipCode());

	        return Optional.of(dto);
	    } else {
	        return Optional.empty(); // If not found
	    }
	}

	@Override
	public DistributorFieldMappingDTO createDistributorMapping(DistributorFieldMapping dto,
			Integer distributorId, Boolean create) {

		System.out.println("Creating distributor mapping..." + dto);
	    
	    Optional<Distributor> distributor = distributorRepository.findById(distributorId);
	    
	    DistributorFieldMapping entity = new DistributorFieldMapping();
	    
	    if(distributor.isPresent()) {
	    	if(!create) {
	    		 if (distributor.get().getDistributorFieldMapping() == null) {
	    	            throw new RuntimeException("No existing mapping found for distributorId: " + distributorId);
	    	        }
	    		 
	    		 // Fetch existing mapping
	    	        entity = distributorMappingRepository.findById(distributor.get().getDistributorFieldMapping().getMappingId())
	    	                .orElseThrow(() -> new RuntimeException("DistributorFieldMapping not found"));

		        System.out.println("Updating existing distributor mapping for distributorId: " + distributorId);
		        entity.setDateModified(new Date());
	    	} else {
	    		entity = new DistributorFieldMapping();
		        System.out.println("Creating new distributor mapping for distributorId: " + distributorId);
		        entity.setDateCreated(new Date());
	    	}
	    	
	    }
	  	    
	 // Map DTO fields to Entity
	    entity.setClaimPeriod(dto.getClaimPeriod());
	    entity.setDistributorName(dto.getDistributorName());
	    entity.setCustomerProductShippedToAddress(dto.getCustomerProductShippedToAddress());
	    entity.setAmountCreditedPerItem(dto.getAmountCreditedPerItem());
	    entity.setBillbackPartnerCustomerNumber(dto.getBillbackPartnerCustomerNumber());
	    entity.setCustomerProductShippedToCity(dto.getCustomerProductShippedToCity());
	    entity.setInvoiceShippedDate(dto.getInvoiceShippedDate());
	    entity.setDistributorBilledShippedInvoiceNumber(dto.getDistributorBilledShippedInvoiceNumber());
	    entity.setCustomerDistributorName(dto.getCustomerDistributorName());
	    entity.setNoMapping(dto.getNoMapping());
	    entity.setSgaPXCustomerUnitPrice(dto.getSgaPXCustomerUnitPrice());
	    entity.setQuantityShipped(dto.getQuantityShipped());
	    entity.setRebateClaim(dto.getRebateClaim());
	    entity.setMaterialNumber(dto.getMaterialNumber());
	    entity.setBillbackSgaPXDistributorCustomerCombination(dto.getBillbackSgaPXDistributorCustomerCombination());
	    entity.setCustomerProductShippedToState(dto.getCustomerProductShippedToState());
	    entity.setDistributorSgaInvoiceUOM(dto.getDistributorSgaInvoiceUOM());
	    entity.setDistributorInvoiceSgaProductUnitPrice(dto.getDistributorInvoiceSgaProductUnitPrice());
	    entity.setCustomerProductShippedToZipCode(dto.getCustomerProductShippedToZipCode());
	    entity.setDistributor(distributor.get());
		
	    DistributorFieldMapping savedEntity = distributorMappingRepository.save(entity);
	    
	    if (create) {
	        distributor.get().setDistributorFieldMapping(savedEntity);
	        distributor.get().setIsMappingPresent(true);
	        distributorRepository.save(distributor.get());
	    }
//	    distributorRepository.save(distributor.get());

//	    return savedEntity;
	    return mapToDTO(savedEntity); 
	}

//	@Override
//	public DistributorFieldMappingDTO createDistributorMapping(DistributorFieldMappingDTO distributorFieldMapping, Integer distributorId, Boolean create) {
//		System.out.println("create distributor mapping..."+ distributorFieldMapping);
//		Optional<Distributor> distributor = this.distributorRepository.findById(distributorId);
//		System.out.println("--------------------------- "+distributor.toString());
//		if(distributor.isPresent()) {
//			if(create) {
//				distributorFieldMapping.setDateCreated(new Date());
//				distributorFieldMapping.setDistributor(distributor.get());
//				DistributorFieldMapping savedDMR =  this.distributorMappingRepository.save(distributorFieldMapping);
//				distributor.get().setDistributorFieldMapping(savedDMR);
//				distributor.get().setIsMappingPresent(true);
//				this.distributorRepository.save(distributor.get());
//				return savedDMR;
//			} else {
//				distributorFieldMapping.setDateModified(new Date());
//				DistributorFieldMapping savedDMR =  this.distributorMappingRepository.save(distributorFieldMapping);
//				return savedDMR;
//			}
//			if(Boolean.TRUE.equals(distributor.get().getIsMappingPresent())) {
//				
//			}
//			else {
//				distributorFieldMapping.setDateCreated(new Date());
//				distributorFieldMapping.setDistributor(distributor.get());
//				DistributorFieldMapping savedDMR =  this.distributorMappingRepository.save(distributorFieldMapping);
//				distributor.get().setDistributorFieldMapping(savedDMR);
//				distributor.get().setIsMappingPresent(true);
//				this.distributorRepository.save(distributor.get());
//				return savedDMR;
//			}	
			
//		}	else {
//			throw new RuntimeException("Distibutor not found");
//		}
//		}

	
	private DistributorFieldMappingDTO mapToDTO(DistributorFieldMapping entity) {
	    DistributorFieldMappingDTO dto = new DistributorFieldMappingDTO();
	    dto.setMappingId(entity.getMappingId());
	    dto.setCustomerProductShippedToAddress(entity.getCustomerProductShippedToAddress());
	    dto.setAmountCreditedPerItem(entity.getAmountCreditedPerItem());
	    dto.setBillbackPartnerCustomerNumber(entity.getBillbackPartnerCustomerNumber());
	    dto.setCustomerProductShippedToCity(entity.getCustomerProductShippedToCity());
	    dto.setInvoiceShippedDate(entity.getInvoiceShippedDate());
	    dto.setDistributorBilledShippedInvoiceNumber(entity.getDistributorBilledShippedInvoiceNumber());
	    dto.setCustomerDistributorName(entity.getCustomerDistributorName());
	    dto.setNoMapping(entity.getNoMapping());
	    dto.setSgaPXCustomerUnitPrice(entity.getSgaPXCustomerUnitPrice());
	    dto.setQuantityShipped(entity.getQuantityShipped());
	    dto.setRebateClaim(entity.getRebateClaim());
	    dto.setMaterialNumber(entity.getMaterialNumber());
	    dto.setBillbackSgaPXDistributorCustomerCombination(entity.getBillbackSgaPXDistributorCustomerCombination());
	    dto.setCustomerProductShippedToState(entity.getCustomerProductShippedToState());
	    dto.setDistributorSgaInvoiceUOM(entity.getDistributorSgaInvoiceUOM());
	    dto.setDistributorInvoiceSgaProductUnitPrice(entity.getDistributorInvoiceSgaProductUnitPrice());
	    dto.setCustomerProductShippedToZipCode(entity.getCustomerProductShippedToZipCode());
	    return dto;
	}

	

}

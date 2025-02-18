package com.sg.billback.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sg.billback.dto.DistributorDTO;
import com.sg.billback.dto.DistributorFieldMappingDTO;
import com.sg.billback.model.DistributorFieldMapping;
import com.sg.billback.service.DistributorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api") // This ensures all endpoints start with /api
@CrossOrigin(origins = "http://localhost:4200")
public class DistributorController {
	
	@Autowired
	private DistributorService distributorService;
	
	@GetMapping("/getActiveDistributors")
	public ResponseEntity<List<DistributorDTO>> getActiveDistributors(){
		List<DistributorDTO> distributors = this.distributorService.getDistributors();
		return new ResponseEntity<>(distributors, HttpStatus.OK);
	}
	
	@GetMapping("/getDistributorMapping/{mappingId}")
	public ResponseEntity<Optional<DistributorFieldMappingDTO>> getDistributorMapping(@PathVariable Integer mappingId){
		Optional<DistributorFieldMappingDTO> distributorMapping = this.distributorService.getDistributorMapping(mappingId);
		if(distributorMapping.isPresent()) {
			return ResponseEntity.ok(distributorMapping);
		} else {
			throw new RuntimeException("Distrbutor Mapping Not Found");
		}
	}
	
	@PostMapping("/createDistributorMapping/{distributorId}/{create}")
	public ResponseEntity<DistributorFieldMappingDTO> createDistributorMapping(@Valid @RequestBody DistributorFieldMapping distributorFieldMapping, BindingResult result, @PathVariable Integer distributorId, @PathVariable Boolean create){
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().build();
		}
		DistributorFieldMappingDTO savedDistributorFieldMapping =  this.distributorService.createDistributorMapping(distributorFieldMapping, distributorId, create);
		return new ResponseEntity<>(savedDistributorFieldMapping, HttpStatus.CREATED);
	}

	
	
}

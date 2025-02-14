package com.sg.billback.controller;

import java.util.List;

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

import com.sg.billback.model.Distributor;
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
	public ResponseEntity<List<Distributor>> getActiveDistributors(){
		List<Distributor> distributors = this.distributorService.getDistributors();
		return new ResponseEntity<>(distributors, HttpStatus.OK);
	}
	
	@PostMapping("/createDistributorMapping/{distributorId}")
	public ResponseEntity<DistributorFieldMapping> createDistributorMapping(@Valid @RequestBody DistributorFieldMapping distributorFieldMapping, BindingResult result, @PathVariable Integer distributorId){
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().build();
		}
		DistributorFieldMapping savedDistributorFieldMapping =  this.distributorService.createDistributorMapping(distributorFieldMapping, distributorId);
		return new ResponseEntity<>(savedDistributorFieldMapping, HttpStatus.CREATED);
	}

	
}

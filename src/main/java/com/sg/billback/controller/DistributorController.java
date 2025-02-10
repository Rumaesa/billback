package com.sg.billback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sg.billback.model.Distributor;
import com.sg.billback.service.DistributorService;

@RestController
public class DistributorController {
	
	@Autowired
	private DistributorService distributorService;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/getActiveDistributors")
	public ResponseEntity<List<Distributor>> getActiveDistributors(){
		List<Distributor> distributors = this.distributorService.getDistributors();
		return new ResponseEntity<>(distributors, HttpStatus.OK);
	}

	
}

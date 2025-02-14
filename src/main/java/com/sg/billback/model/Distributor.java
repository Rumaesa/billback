package com.sg.billback.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "distributor")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Distributor {
	
	@Id
	@Column(name = "distributor_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer distributorId;
	
	@Column(name = "distributor_name")
	private String distributorName;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "header")
	private String header;
	
	@Column(name = "is_mapping_present")
	private Boolean isMappingPresent;

}

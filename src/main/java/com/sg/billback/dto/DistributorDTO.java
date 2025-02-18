package com.sg.billback.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistributorDTO {

	private Integer distributorId;
	private String distributorName;
	private String status;
	private String header;
	private Boolean isMappingPresent;
	private Integer mappingId;
}

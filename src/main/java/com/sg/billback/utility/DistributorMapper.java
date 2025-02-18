package com.sg.billback.utility;

import com.sg.billback.dto.DistributorDTO;
import com.sg.billback.model.Distributor;

public class DistributorMapper {

	public static DistributorDTO mapToDistributorDTO(Distributor distributor) {
        return new DistributorDTO(
                distributor.getDistributorId(),
                distributor.getDistributorName(),
                distributor.getStatus(),
                distributor.getHeader(),
                distributor.getIsMappingPresent(),
                distributor.getDistributorFieldMapping() != null ? distributor.getDistributorFieldMapping().getMappingId() : null
        );
    }
}

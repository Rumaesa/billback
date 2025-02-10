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
@Table(name = "field")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Field {
	
	@Id
	@Column(name = "field_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer fieldId;
	
	@Column(name = "field_name")
	private String fieldName;

}

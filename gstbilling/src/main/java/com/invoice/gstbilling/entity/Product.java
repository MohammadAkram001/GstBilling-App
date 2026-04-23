package com.invoice.gstbilling.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;

@Entity
@Data

public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Getter
	private Long id;
	
	private String name;
	private double price;
	private double gstPercentage; //example: 18%
}

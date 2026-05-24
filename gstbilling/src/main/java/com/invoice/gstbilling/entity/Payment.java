package com.invoice.gstbilling.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private double amount;
	private String status;
	
	private String paymentMethod;
	private LocalDate paymentDate;
	
	@OneToOne
	@JoinColumn(name = "invoice_id")
	private Invoice invoice;
}

package com.invoice.gstbilling.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invoice.gstbilling.entity.Payment;
import com.invoice.gstbilling.repository.PaymentRepository;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;
	
	public void markAsPaid(Long invoiceId, String method) {
		Payment payment=paymentRepository.findByInvoiceId(invoiceId);
		payment.setStatus("PAID");
		payment.setPaymentMethod(method);
		payment.setPaymentDate(LocalDate.now());
		paymentRepository.save(payment);
	}
}

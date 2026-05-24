package com.invoice.gstbilling.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.invoice.gstbilling.service.PaymentService;

@Controller
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	
	
	public String markAsPaid(
			@PathVariable Long invoiceId,
			@RequestParam String method) {
		paymentService.markAsPaid(invoiceId, method);
		return"redirect:/invoice/list";
	}
}

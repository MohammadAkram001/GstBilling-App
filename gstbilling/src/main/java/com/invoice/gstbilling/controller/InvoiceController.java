package com.invoice.gstbilling.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.invoice.gstbilling.repository.CustomerRepository;
import com.invoice.gstbilling.repository.InvoiceRepository;
import com.invoice.gstbilling.repository.ProductRepository;
import com.invoice.gstbilling.service.InvoiceService;

@Controller
@RequestMapping("/invoice")
public class InvoiceController {
	
	@Autowired
	private InvoiceService invoiceService;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private InvoiceRepository invoiceRepository;

	//show invoice form
	@GetMapping("/create")
	public String showInvoiceForm(Model model) {
		
		model.addAttribute("customers", customerRepository.findAll());
		model.addAttribute("products", productRepository.findAll());
		return "invoice";
	}
	
	
	// save invoice
	@PostMapping("/create")
	public String CreateInvoice(
			@RequestParam Long customerId,
			@RequestParam List<Long> productIds,
			@RequestParam List<Integer> quantities) {
		invoiceService.createInvoice(customerId, productIds, quantities);
		return "redirect:/invoice/list";
	}
	
	//show all invoices
	@GetMapping("/list")
	public String listInvoices(Model model) {
		model.addAttribute("invoices", invoiceRepository.findAll());
		return "invoice-list";
	}
}

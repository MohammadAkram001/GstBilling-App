package com.invoice.gstbilling.controller;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.invoice.gstbilling.entity.Invoice;
import com.invoice.gstbilling.repository.CustomerRepository;
import com.invoice.gstbilling.repository.InvoiceRepository;
import com.invoice.gstbilling.repository.ProductRepository;
import com.invoice.gstbilling.service.InvoiceService;
import com.invoice.gstbilling.service.PdfService;

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
	
	@Autowired
	private PdfService pdfService;

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
	
	@GetMapping("/pdf/{id}")
	public ResponseEntity<InputStreamResource> downloadPdf (@PathVariable Long id){
		
		Invoice invoice=invoiceRepository.findById(id).orElseThrow();
		ByteArrayInputStream bis=pdfService.generateInvoicepdf(invoice);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=invoice_" + id + ".pdf");
		
		return ResponseEntity
			.ok()
			.contentType(MediaType.APPLICATION_PDF)
			.body(new InputStreamResource(bis));
		
	}
}

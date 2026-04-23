package com.invoice.gstbilling.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invoice.gstbilling.entity.Customer;
import com.invoice.gstbilling.entity.Invoice;
import com.invoice.gstbilling.entity.InvoiceItem;
import com.invoice.gstbilling.entity.Payment;
import com.invoice.gstbilling.entity.Product;
import com.invoice.gstbilling.repository.CustomerRepository;
import com.invoice.gstbilling.repository.InvoiceItemRepository;
import com.invoice.gstbilling.repository.InvoiceRepository;
import com.invoice.gstbilling.repository.PaymentRepository;
import com.invoice.gstbilling.repository.ProductRepository;

@Service
public class InvoiceService {

	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private InvoiceItemRepository invoiceItemRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	
	public Invoice createInvoice(Long customerId, List<Long>productIds, List<Integer>quantities) {
		
		Customer customer=customerRepository.findById(customerId).orElseThrow();
		
		Invoice invoice = new Invoice();
		invoice.setCustomer(customer);
		invoice.setDate(LocalDate.now());
		invoice.setStatus("UNPAID");
		
		double totalAmount = 0;
		
		// save invoice first
		invoice=invoiceRepository.save(invoice);
		
		for(int i = 0; i<productIds.size();i++) {
			
			Product product=productRepository.findById(productIds.get(i)).orElseThrow();
			int qty = quantities.get(i);
			
			double price = product.getPrice()*qty;
			
			// GST Calculation
			
			double gst=price*product.getGstPercentage()/100;
			
			InvoiceItem item = new InvoiceItem();
			item.setInvoice(invoice);
			item.setProduct(product);
			item.setQuantity(qty);
			item.setPrice(price);
			item.setGstAmount(gst);
			
			invoiceItemRepository.save(item);
			
			totalAmount += price+gst;
		}
		
		invoice.setTotalAmount(totalAmount);
		invoiceRepository.save(invoice);
		
		// create payment entry
		Payment payment = new Payment();
		payment.setInvoice(invoice);
		payment.setAmount(totalAmount);
		payment.setStatus("PENDING");
		
		paymentRepository.save(payment);
		
		return invoice;
		
	}
}

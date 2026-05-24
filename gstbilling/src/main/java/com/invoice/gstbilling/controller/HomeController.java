package com.invoice.gstbilling.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.invoice.gstbilling.repository.CustomerRepository;
import com.invoice.gstbilling.repository.InvoiceRepository;
import com.invoice.gstbilling.repository.ProductRepository;

@Controller
public class HomeController {

	@Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @GetMapping("/")
    public String home(Model model) {

        long totalCustomers = customerRepository.count();
        long totalProducts = productRepository.count();
        long totalInvoices = invoiceRepository.count();

        model.addAttribute("customers", totalCustomers);
        model.addAttribute("products", totalProducts);
        model.addAttribute("invoices", totalInvoices);

        return "home";
    }
}

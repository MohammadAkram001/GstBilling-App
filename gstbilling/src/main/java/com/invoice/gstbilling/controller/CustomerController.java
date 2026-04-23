package com.invoice.gstbilling.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.invoice.gstbilling.entity.Customer;
import com.invoice.gstbilling.repository.CustomerRepository;


@Controller
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;
	
	@GetMapping("/customers")
	public String showForm(Model model) {
		model.addAttribute("customer", new Customer());
		return "customer";
	}
	
	@PostMapping("/customers/save")
	public String saveCustomer(@ModelAttribute Customer customer) {
		customerRepository.save(customer);
		return "redirect:/";
	}
}

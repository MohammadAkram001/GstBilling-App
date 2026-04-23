package com.invoice.gstbilling.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.invoice.gstbilling.entity.Product;
import com.invoice.gstbilling.repository.ProductRepository;

@Controller
public class ProductController {

	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping("/products")
	public String showForm(Model model) {
		model.addAttribute("product", new Product());
		return "product";
	}
	
	@PostMapping("/product/save")
	public String saveProduct(@ModelAttribute Product product) {
		productRepository.save(product);
		return "redirect:/";
	}
}

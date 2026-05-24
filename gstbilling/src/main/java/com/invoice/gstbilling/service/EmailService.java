package com.invoice.gstbilling.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.invoice.gstbilling.entity.Invoice;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;
	
	public void sendInvoiceemail(String toEmail, Invoice invoice) {
		
		SimpleMailMessage mail=new SimpleMailMessage();
		
		mail.setTo(toEmail);
		mail.setSubject("GST Invoice Genertaed");
		
		mail.setText(
				"Hello Customer,\n\n" + 
				"Your invoice has been generated.\n" + 
				"Invoice ID: "+ invoice.getId() + "\n" +
				"Total Amount: rs"+ invoice.getTotalAmount() + "\n" +
				"Status: " + invoice.getStatus()+ "\n\n" + 
				"Thank you"
				);
		mailSender.send(mail);
	}
	
}

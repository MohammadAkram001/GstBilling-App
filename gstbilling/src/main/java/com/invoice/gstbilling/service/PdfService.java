package com.invoice.gstbilling.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.springframework.stereotype.Service;

import com.invoice.gstbilling.entity.Invoice;
import com.invoice.gstbilling.entity.InvoiceItem;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class PdfService {

	public ByteArrayInputStream generateInvoicepdf(Invoice invoice) {
		
		Document document =new Document();
		ByteArrayOutputStream out=new ByteArrayOutputStream();
		
		try {
			PdfWriter.getInstance(document, out);
			document.open();
			
			Font font=FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
			
			Paragraph title=new Paragraph("GST Invoice", font);
			
			title.setAlignment(Element.ALIGN_CENTER);
			
			document.add(title);
			document.add(new Paragraph(" "));
			
			document.add(new Paragraph("Invoice ID: " + invoice.getId()));
			document.add(new Paragraph("Date: "+invoice.getDate()));
			document.add(new Paragraph("Customer: "+invoice.getCustomer().getName()));
			document.add(new Paragraph("Email: "+invoice.getCustomer().getEmail()));
			document.add(new Paragraph("phone: "+invoice.getCustomer().getPhone()));
			
			document.add(new Paragraph(" "));
			
			PdfPTable table = new PdfPTable(5);
			table.setWidthPercentage(100);
			
			table.addCell("Product");
			table.addCell("Qty");
			table.addCell("Price");
			table.addCell("GST");
			table.addCell("Total");
			
			for(InvoiceItem item:invoice.getItems()) {
				
				double total =item.getPrice() + item.getGstAmount();
				
				table.addCell(item.getProduct().getName());
				table.addCell(String.valueOf(item.getQuantity()));
				table.addCell(String.valueOf(item.getPrice()));
				table.addCell(String.valueOf(item.getGstAmount()));
				table.addCell(String.valueOf(total));
			}
			
			document.add(table);
			
			document.add(new Paragraph(" "));
			document.add(new Paragraph("Grand Total : rs "+ invoice.getTotalAmount()));
			
			document.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ByteArrayInputStream(out.toByteArray());
	}
}

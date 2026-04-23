package com.invoice.gstbilling.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invoice.gstbilling.entity.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long>{

}

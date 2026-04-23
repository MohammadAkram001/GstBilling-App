package com.invoice.gstbilling.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invoice.gstbilling.entity.InvoiceItem;

public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, Long>{

}

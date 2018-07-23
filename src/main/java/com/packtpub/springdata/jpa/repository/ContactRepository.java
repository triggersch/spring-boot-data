package com.packtpub.springdata.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.packtpub.springdata.jpa.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
	List<Contact> findByFirstNameStartingWithOrLastNameStartingWith(String firstName, String LastName, Pageable page);
}

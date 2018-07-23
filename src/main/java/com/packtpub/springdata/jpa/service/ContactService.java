package com.packtpub.springdata.jpa.service;

import java.util.List;

import com.packtpub.springdata.jpa.dto.ContactDTO;
import com.packtpub.springdata.jpa.dto.SearchDTO;
import com.packtpub.springdata.jpa.model.Contact;

public interface ContactService {

	Contact Add(ContactDTO added);
	
	Contact deleteById(Long id) throws NotFoundException ;
	
	List<Contact> findAllForPage(int pageIndex, int pageSize);
	
	Contact findById(Long id) throws NotFoundException ;
	
	Contact update(ContactDTO updated) throws NotFoundException;
	
	List<Contact> search(SearchDTO dto);
	
	List<Contact> findAll();

}

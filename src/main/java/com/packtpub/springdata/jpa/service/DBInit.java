package com.packtpub.springdata.jpa.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packtpub.springdata.jpa.model.Contact;
import com.packtpub.springdata.jpa.repository.ContactRepository;

@Service
public class DBInit {

	@Autowired
	private ContactRepository repository;
	
	@PostConstruct
	public void initDB(){
	
		Contact contact = Contact.getBuilder("toto", "titi").address("99 rue tata", "99999", "totopost", "BRETAGNE", "NAVARE")
				                 .emailAddress("toto@toto.com").build();
		repository.save(contact);
	}
}

package com.packtpub.springdata.jpa.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.packtpub.springdata.jpa.model.Contact;
import com.packtpub.springdata.jpa.service.ContactService;
import com.packtpub.springdata.jpa.web.DataJPAExampleInitializer;

@RestController
public class ContactController {
	
	@Resource
    private ContactService service;
	
	@RequestMapping("/")
	@ResponseBody
	String home(){
		return "hello";
	}

	@RequestMapping(value="/contacts",method=RequestMethod.GET)
	public ResponseEntity<Iterable<Contact>> contacts(){
		List<Contact> c = service.findAll();
		return new ResponseEntity<>(c,HttpStatus.OK);
	}
}

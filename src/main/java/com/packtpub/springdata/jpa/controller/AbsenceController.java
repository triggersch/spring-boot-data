package com.packtpub.springdata.jpa.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.packtpub.springdata.jpa.bar.model.Absence;
import com.packtpub.springdata.jpa.bar.model.MyView;
import com.packtpub.springdata.jpa.bar.repository.AbsenceRepository;
import com.packtpub.springdata.jpa.bar.repository.MyViewRepository;

@RestController
public class AbsenceController {
	
	@Resource
	private AbsenceRepository repository;
	
	@Resource
	private MyViewRepository vRepository;
	
	@RequestMapping(value="/absences/{abId}", method=RequestMethod.GET)
	public ResponseEntity<?> absence(@PathVariable Long abId){
		Absence ab = repository.findOne(abId);
		return new ResponseEntity<>(ab, HttpStatus.OK);
	}
	
	@RequestMapping(value="/views/{viewId}",  method=RequestMethod.GET)
	public ResponseEntity<?> views(@PathVariable Long viewId ){
		MyView v = vRepository.findOne(viewId);
		return new ResponseEntity<>(v,HttpStatus.OK);
	}
	
	@RequestMapping("/test/{viewId}")
	@ResponseBody
	public String test(@PathVariable Long viewId){
		List<MyView> vs = vRepository.findAll();
		MyView v = vRepository.findOne(viewId);
		return v.ConvertDateDebut().toString() ;
	}
}

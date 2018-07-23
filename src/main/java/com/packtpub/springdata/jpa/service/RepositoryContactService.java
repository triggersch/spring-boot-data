package com.packtpub.springdata.jpa.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.packtpub.springdata.jpa.dto.ContactDTO;
import com.packtpub.springdata.jpa.dto.SearchDTO;
import com.packtpub.springdata.jpa.model.Contact;
import com.packtpub.springdata.jpa.repository.ContactRepository;


@Service
public class RepositoryContactService implements ContactService {
	
	@Resource
	private ContactRepository repository;

	@Transactional
	@Override
	public Contact Add(ContactDTO added) {
		Contact contact = Contact.getBuilder(added.getFistName(), added.getLastName())
				.address(added.getStreetAddress(), added.getPostCode(),
						 added.getPostOffice(), added.getState(), added.getCountry())
				.emailAddress(added.getEmailAddress()).phoneNumber(added.getPhoneNumber())
				.build();
				
		return repository.save(contact);
	}

	@Transactional(rollbackFor = NotFoundException.class)
	@Override
	public Contact deleteById(Long id) throws NotFoundException {
		Contact deleted = findById(id);
		repository.delete(deleted);
		return deleted;
	}

	@Transactional(readOnly = true)
	@Override
	public Contact findById(Long id) throws NotFoundException {
		
		Contact found = repository.findOne(id);
		
		if (found == null )
			throw new NotFoundException("No contact found with id: " + id);
		return found;
	}

	@Transactional(rollbackFor = NotFoundException.class)
	@Override
	public Contact update(ContactDTO updated) throws NotFoundException {
		Contact found = findById( updated.getId() );
		
		found.update(updated.getFistName(), updated.getLastName(),
				     updated.getEmailAddress(), updated.getPhoneNumber());
		found.updateAddress(updated.getStreetAddress(), updated.getPostCode(),
				updated.getPostOffice(), updated.getState(), updated.getCountry());
		return found;
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<Contact> search(SearchDTO dto) {
		
		Pageable pageSpecification = buildPageSpecification(dto.getPageIndex(), dto.getPageSize());
		
		return repository.findByFirstNameStartingWithOrLastNameStartingWith(dto.getSearchTerm(),
				                                         dto.getSearchTerm(), pageSpecification);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Contact> findAllForPage(int pageIndex, int pageSize) {
		Pageable pageSpecification = buildPageSpecification(pageIndex, pageSize);
		Page<Contact> page = repository.findAll(pageSpecification);
		return page.getContent();
	}
	
	private Pageable buildPageSpecification(int pageIndex, int pageSize){
		Sort sortSpec = sortByLastNameAndFirstNameAsc();
		return new PageRequest(pageIndex, pageSize, sortSpec);
	}

	private Sort sortByLastNameAndFirstNameAsc() {
		
		return new Sort(new Sort.Order(Sort.Direction.ASC, "lastName")
				        , new Sort.Order(Sort.Direction.ASC, "firstName"));
	}

	@Transactional(readOnly = true)
	@Override
	public List<Contact> findAll() {
		List<Contact> contacts = repository.findAll();
		return contacts;
	}

	
}

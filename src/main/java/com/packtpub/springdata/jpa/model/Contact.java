package com.packtpub.springdata.jpa.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="contacts")
public class Contact {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Embedded
	private Address address;
	
	@Column(name = "email_address", length = 100)
	private String emailAddress;
	
	@Column(name="first_name", nullable=false, length = 50)
	private String firstName;
	
	@Column(name="last_name", nullable=false, length = 100)
	private String lastName;
	
	@Column(name="phone_number", length = 30)
	private String phoneNumber;
	
	@Version
	private long version;
	
	public static class Builder{
		
		private Contact built;

		public Builder(String firstname,String lastname) {
			built =  new Contact();
			built.setFirstName(firstname);
			built.setLastName(lastname);
		}
		
		
		public Builder address(String streetAddress, String postCode,
				               String postOffice, String state, String country){
			Address address = new Address();
			
		    address = Address.getBuilder(streetAddress, postCode, postOffice)
		    .state(state).country(country).build();
			built.address = address;
			
			return this;
		}
		
		public Builder emailAddress(String emailAddress){
			built.emailAddress = emailAddress;
			return this;
		}
		
		public Builder phoneNumber(String phoneNumber){
			built.phoneNumber = phoneNumber;
			return this;
		}
		
		public Contact build(){
			return built;
		}
		
	}
	
	public static Builder getBuilder(String firstname, String lastname){
		return new Builder(firstname, lastname);
	}
	
	public void update(final String firstname, final String lastname, 
			           final String emailAddress, final String phoneNumber ){
		this.firstName = firstname;
		this.lastName = lastname;
		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber;
	}
	
	
	public void updateAddress(final String streetAddress, final String postCode,
			                  final String postOffice, final String state, final String country){
		if(address == null){
			address = new Address();
		}
		
		address.update(streetAddress, postCode, postOffice, country, state);
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public Long getId() {
		return id;
	}
	
	
}

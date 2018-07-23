package com.packtpub.springdata.jpa.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {
	
	@Column(name = "country", length = 20)
	private String country;
	
	@Column(name = "street_address", length  = 150)
	private String streetAddress;
	
	@Column(name = "post_code", length = 10)
	private String postCode;
	
	@Column(name = "post_office", length = 40)
	private String postOffice ;
	
	@Column(name = "state" , length = 20)
	private String state;
	
	
	public static class Builder{
		
		private Address built;
		
		public Builder(String streetAddress, String postCode, String postOffice){
			built = new Address();
			built.streetAddress = streetAddress;
			built.postCode = postCode;
			built.postOffice = postOffice;
		}
		
		public Builder country(String country){			
			built.country = country;
			return this;
		}
		
		public Builder state(String state){
			built.state = state;
			return this;
		}
		
		public Address build(){
			return built;
		}
	}
	
	public static Builder getBuilder(String streetAddress, String postCode, String postOffice){
		return new Builder(streetAddress, postCode, postOffice);
	}
	
	public void update(final String streetAddress, final String postCode,final String postOffice,
			           final String country, final String state){
		
		this.streetAddress = streetAddress;
		this.postCode = postCode;
		this.postOffice = postOffice;
		this.state = state;
		this.country = country;
		
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getPostOffice() {
		return postOffice;
	}

	public void setPostOffice(String postOffice) {
		this.postOffice = postOffice;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}

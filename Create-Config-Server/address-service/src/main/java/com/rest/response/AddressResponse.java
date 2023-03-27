package com.rest.response;

import com.rest.entity.Address;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressResponse {

	private long addressId;
	private String street;
	private String city;

	public AddressResponse(Address address) {
		this.addressId = address.getId();
		this.street = address.getStreet();
		this.city = address.getCity();
	}
}
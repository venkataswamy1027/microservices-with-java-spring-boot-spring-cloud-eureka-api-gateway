package com.rest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.feignclients.AddressFeignClient;
import com.rest.response.AddressResponse;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class CommonService {
	Logger logger = LoggerFactory.getLogger(CommonService.class);
	int count = 1;
	@Autowired
	AddressFeignClient addressFeignClient;

	@CircuitBreaker(name = "addressService", fallbackMethod = "fallbackGetAddressById")
	public AddressResponse getAddressById(long addressId) {
		logger.info("count = " + count);
		count++;
		AddressResponse addressResponse = addressFeignClient.getById(addressId);

		return addressResponse;
	}

	public AddressResponse fallbackGetAddressById(long addressId, Throwable th) {
		logger.info("error due to " + th);
		return new AddressResponse();
	}
}

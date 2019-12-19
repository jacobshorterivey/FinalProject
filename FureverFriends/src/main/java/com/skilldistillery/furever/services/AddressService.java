package com.skilldistillery.furever.services;

import java.util.List;

import com.skilldistillery.furever.entities.Address;

public interface AddressService {
	public List<Address> displayAllAddresses();
	public Address showAddress(int aid);
	public Address createNewAddress(Address newAddress);
	public Address updateAddress(Address origAddress, Integer aid);
	public boolean destroyAddress(Integer aid);
}

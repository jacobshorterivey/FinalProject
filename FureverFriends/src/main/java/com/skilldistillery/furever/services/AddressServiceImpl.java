package com.skilldistillery.furever.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.furever.entities.Address;
import com.skilldistillery.furever.repositories.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService {

	// FIELDS
	@Autowired
	private AddressRepository addressRepo;

	// METHODS
	@Override
	public List<Address> displayAllAddresses() {
		return addressRepo.findAll();
	}

	@Override
	public Address showAddress(int aid) {
		Optional<Address> opt = addressRepo.findById(aid);
		if (opt.isPresent()) {
			return opt.get();
		}
		return null;
	}

	@Override
	public Address createNewAddress(Address newAddress) {
		try {
			newAddress.setId(0);
			return addressRepo.saveAndFlush(newAddress);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Address updateAddress(Address updateAddress, Integer aid) {
		Address origAddress = showAddress(aid);
		if (origAddress != null) {
			if (updateAddress.getStreet() != null) {
				origAddress.setStreet(updateAddress.getStreet());
			}
			if (updateAddress.getStreet2() != null) {
				origAddress.setStreet2(updateAddress.getStreet2());
			}
			if (updateAddress.getCity() != null) {
				origAddress.setCity(updateAddress.getCity());
			}
			if (updateAddress.getZip() != 0) {
				origAddress.setZip(updateAddress.getZip());
			}
			if (updateAddress.getStateAbbr() != null) {
				origAddress.setStateAbbr(updateAddress.getStateAbbr());
			}
			addressRepo.saveAndFlush(origAddress);
		}
		return origAddress;
	}

	@Override
	public boolean destroyAddress(Integer aid) {
		boolean deleted = false;
		try {
			addressRepo.deleteById(aid);
			deleted = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deleted;
	}

}

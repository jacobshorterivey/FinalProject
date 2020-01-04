package com.skilldistillery.furever.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.furever.entities.Foster;
import com.skilldistillery.furever.repositories.FosterRepository;

@Service
public class FosterServiceImpl implements FosterService {

	// FIELDS
	
	@Autowired
	private FosterRepository fRepo;

	@Override
	public List<Foster> displayAllFosters() {
		// TODO Auto-generated method stub
		return fRepo.findAll();
	}

	@Override
	public List<Foster> findFostersBySpeciesPref(Integer sid) {
		return fRepo.findBySpeciesListId(sid);
	}

	@Override
	public List<Foster> findFosterByBreedPref(Integer bid) {
		return fRepo.findByBreedListId(bid);
	}

	@Override
	public List<Foster> findFosterByTraitPref(Integer tid) {
		return fRepo.findByTraitListId(tid);
	}
	
	@Override
	public Foster update(int id, Foster foster) {
		Foster fosterToUpdate = null;
		Optional<Foster>opt = fRepo.findById(id);
		if (opt.isPresent()) {
			fosterToUpdate = opt.get();
			fosterToUpdate.setUser(foster.getUser());
			fosterToUpdate.setMaxFoster(foster.getMaxFoster());
			fRepo.saveAndFlush(fosterToUpdate);
		}
		return fosterToUpdate;
	}
}

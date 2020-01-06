package com.skilldistillery.furever.services;

import java.util.List;

import com.skilldistillery.furever.entities.Address;
import com.skilldistillery.furever.entities.Foster;

public interface FosterService {
	
	List<Foster> displayAllFosters();
	List<Foster> findFostersBySpeciesPref(Integer sid);
	List<Foster> findFosterByBreedPref(Integer bid);
	List<Foster> findFosterByTraitPref(Integer tid);
	Foster update(int id, Foster foster);
	Foster create(Foster newFoster);
}

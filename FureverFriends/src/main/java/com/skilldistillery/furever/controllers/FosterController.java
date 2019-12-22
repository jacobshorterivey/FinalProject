package com.skilldistillery.furever.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.furever.entities.Foster;
import com.skilldistillery.furever.services.FosterService;

@RestController
@RequestMapping("api/foster")
@CrossOrigin({"*", "http://localhost:4400"})
public class FosterController {
	@Autowired
	private FosterService fSvc;
	
	
	@GetMapping
	private List<Foster> displayAllFosters(){
		return fSvc.displayAllFosters();
	}
	
	@GetMapping("species/{sid}")
	private List<Foster> findFostersBySpeciesPref(@PathVariable Integer sid, HttpServletResponse response){
		List<Foster> fosters = fSvc.findFostersBySpeciesPref(sid);
		if(fosters == null) {
			response.setStatus(404);
		}
		return fosters;
	}
	@GetMapping("breed/{bid}")
	private List<Foster> findFostersByBreedPref(@PathVariable Integer bid, HttpServletResponse response){
		List<Foster> fosters = fSvc.findFosterByBreedPref(bid);
		if(fosters == null) {
			response.setStatus(404);
		}
		return fosters;
	}
	@GetMapping("trait/{tid}")
	private List<Foster> findFostersByTraitPref(@PathVariable Integer tid, HttpServletResponse response){
		List<Foster> fosters = fSvc.findFosterByTraitPref(tid);
		if(fosters == null) {
			response.setStatus(404);
		}
		return fosters;
	}
}

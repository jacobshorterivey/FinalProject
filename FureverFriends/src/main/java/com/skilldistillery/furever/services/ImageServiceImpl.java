package com.skilldistillery.furever.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.furever.repositories.ImageRepository;

@Service
public class ImageServiceImpl implements ImageService {

	// FIELDS
	
	@Autowired
	private ImageRepository imageRepo;
}

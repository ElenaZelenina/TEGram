package com.techelevator.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.PhotoDAO;
import com.techelevator.model.PhotoRequest;

@RestController
@CrossOrigin
public class PhotoController {

	private PhotoDAO photoDAO;
	
	public PhotoController(PhotoDAO photoDAO) {
		this.photoDAO = photoDAO;
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(path = "/photos", method = RequestMethod.POST)
	public boolean addPhoto(@Valid @RequestBody PhotoRequest request, Principal principal) {
		boolean photoAdded = false;
		photoAdded = photoDAO.create(request.getFileName(), request.getLink(), request.getCaption());		
		return photoAdded;
	}
}

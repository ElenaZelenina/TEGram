package com.techelevator.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.PhotoDAO;
import com.techelevator.dao.UserDAO;
import com.techelevator.model.Photo;
import com.techelevator.model.PhotoRequest;

@RestController
@CrossOrigin()
public class TestController {

	private PhotoDAO photoDAO;
	private UserDAO userDAO;
	
	public TestController(PhotoDAO photoDAO, UserDAO userDAO) {
		this.photoDAO = photoDAO;
		this.userDAO = userDAO;
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(path = "/testphotos", method = RequestMethod.POST)
	public boolean addPhoto(@Valid @RequestBody PhotoRequest request, Principal principal) {
		int userId = userDAO.findIdByUsername(principal.getName());
		boolean photoAdded = false;
		photoAdded = (boolean) photoDAO.create(userId, request.getFileName(), request.getLink(), request.getCaption());
		return photoAdded;
	}
	    
    @RequestMapping(path = "/allphotos", method = RequestMethod.GET)
    public List<Photo> getAll() {
    	List<Photo> photos = new ArrayList<>();
    	photos = photoDAO.findAll();
    	return photos;
    }
}

package com.techelevator.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.PhotoDAO;
import com.techelevator.dao.UserDAO;
import com.techelevator.dao.CommentDAO;
import com.techelevator.dao.PhotoCommentDAO;
import com.techelevator.model.Comment;
import com.techelevator.model.Photo;
import com.techelevator.model.PhotoComment;
import com.techelevator.model.PhotoRequest;

@RestController
@CrossOrigin()
public class TestController {

	private PhotoDAO photoDAO;
	private UserDAO userDAO;
	private CommentDAO commentDAO;
	private PhotoCommentDAO photoCommentDAO;
	
	public TestController(PhotoDAO photoDAO, UserDAO userDAO, 
			CommentDAO commentDAO, PhotoCommentDAO photoCommentDAO) {
		this.photoDAO = photoDAO;
		this.userDAO = userDAO;
		this.commentDAO = commentDAO;
		this.photoCommentDAO = photoCommentDAO;
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
    public List<Photo> getTen() {
    	List<Photo> photos = new ArrayList<>();
    	photos = photoDAO.findTen();
    	return photos;
    }
    
    @RequestMapping(path = "/photofeed", method = RequestMethod.GET)
    public List<PhotoComment> getAllPhotosOneComment() {
    	List<PhotoComment> photos = new ArrayList<>();
    	photos = photoCommentDAO.allPhotosOneComment();
    	return photos;
    }
    
    @RequestMapping(path = "/photo/{id}/comments", method = RequestMethod.GET)
    public List<Comment> getCommentsForPhoto(@PathVariable int id) {
    	List<Comment> comments = new ArrayList<>();
    	comments = commentDAO.findCommentsForPhoto(id);
    	return comments;
    }
}

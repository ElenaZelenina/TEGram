package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Photo;

public interface PhotoDAO {
	
	List<Photo> findAll();
	
	Photo getPhotoById(int photoId);
	
	boolean create(Photo newPhoto);
	
}

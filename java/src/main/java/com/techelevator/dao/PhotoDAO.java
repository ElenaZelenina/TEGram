package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Photo;

public interface PhotoDAO {
	
	List<Photo> findTen();
	
	Photo getPhotoById(int photoId);
	
	boolean create(int user_id, String fileName, String link, String caption);
	
}

package com.techelevator.dao;

public interface FavoriteDAO {

	boolean create (int user_id, int photo_id);
	
	boolean delete (int user_id, int photo_id);

}

package com.techelevator.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.model.Photo;

public class PhotoSqlDAO implements PhotoDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public PhotoSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Photo> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Photo getPhotoById(int photoId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean create(Photo newPhoto) {
		// TODO Auto-generated method stub
		return false;
	}

}

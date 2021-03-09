package com.techelevator.dao;

import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.techelevator.model.Photo;

@Service
public class PhotoSqlDAO implements PhotoDAO {
	
	private static final int INITIAL_LIKES_COUNT = 0;
	
	private JdbcTemplate jdbcTemplate;
	
	public PhotoSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Photo> findAll() {
		List<Photo> photos = new ArrayList<>();
		String sql = "SELECT * FROM photo";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while(results.next()) {
			Photo photo = mapRowToPhoto(results);
			photos.add(photo);
		}
		
		return photos;
	}

	@Override
	public Photo getPhotoById(int photoId) {
		String sql = "SELECT * FROM photo WHERE photo_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, photoId);
		if(results.next()) {
			return mapRowToPhoto(results);
		} else {
			throw new RuntimeException("Photo ID " + photoId + "was not found.");
		}
	}

	@Override
	public boolean create(String fileName, String link, String caption) {
		boolean photoCreated = false;
		
		LocalDateTime dateTime = LocalDateTime.now();
		String insertPhoto = "INSERT INTO photo (file_name, link, caption, likes_count, date_time) "
				+ "VALUES (?, ?, ?, ?, ?)";
		photoCreated = jdbcTemplate.update(con -> {
			PreparedStatement ps = con.prepareStatement(insertPhoto);
			ps.setString(1, fileName);
			ps.setString(2, link);
			ps.setString(3, caption);
			ps.setInt(4, INITIAL_LIKES_COUNT);
			ps.setObject(5, dateTime);			
			return ps;
		}) == 1;		
		
		return photoCreated;
	}

	private Photo mapRowToPhoto(SqlRowSet rs) {
		Photo photo = new Photo();
		photo.setId(rs.getInt("photo_id"));
		photo.setFileName(rs.getString("file_name"));
		photo.setLink(rs.getString("link"));
		photo.setCaption(rs.getString("caption"));
		photo.setLikesCount(rs.getInt("likes_count"));
		photo.setDateTime(rs.getDate("date_time"));		
		return photo;
	}
	
}

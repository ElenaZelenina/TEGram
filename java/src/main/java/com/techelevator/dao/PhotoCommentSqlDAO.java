package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.techelevator.model.PhotoComment;

@Service
public class PhotoCommentSqlDAO implements PhotoCommentDAO {
	
	private JdbcTemplate jdbcTemplate;
	

	public PhotoCommentSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	@Override
	public List<PhotoComment> allPhotosOneComment() {
		List<PhotoComment> photos = new ArrayList<>();
		String sql = "SELECT photo.photo_id AS photo_id, link, caption, likes_count, "
				+ "comment.date_time AS comment_date_time, cu.username AS comment_username, message, pu.username AS photo_username "
				+ "FROM photo JOIN comment ON photo.photo_id = comment.photo_id "
				+ "JOIN users AS cu ON comment.user_id = cu.user_id "
				+ "JOIN users AS pu ON photo.user_id = pu.user_id "
				+ "ORDER BY photo.photo_id DESC,  comment.date_time DESC";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while(results.next()) {
			PhotoComment photo = mapRowToPhotoComment(results);
			photos.add(photo);
		}
		
		return photos;
	}


	private PhotoComment mapRowToPhotoComment(SqlRowSet rs) {
		PhotoComment pc = new PhotoComment();
		pc.setPhotoId(rs.getInt("photo_id"));
		pc.setLink(rs.getString("link"));
		pc.setCaption(rs.getString("caption"));
		pc.setLikesCount(rs.getInt("likes_count"));
		pc.setCommentDateTime(rs.getDate("comment_date_time"));
		pc.setMessage(rs.getString("message"));
		pc.setPhotoUsername(rs.getString("photo_username"));
		pc.setCommentUsername(rs.getString("comment_username"));
		return pc;
	}

}

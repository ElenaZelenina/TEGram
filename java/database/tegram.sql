BEGIN TRANSACTION;

DROP TABLE IF EXISTS photo;
DROP SEQUENCE IF EXISTS photo_id;

CREATE SEQUENCE seq_photo_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;


CREATE TABLE photo (
	photo_id int DEFAULT nextval('seq_photo_id'::regclass) NOT NULL,
	file_name varchar(250) NOT NULL,
	link varchar(250) NOT NULL,
	caption varchar(250) NOT NULL,
	likes_count int NOT NULL,
	date_time timestamp NOT NULL, 
	CONSTRAINT PK_photo PRIMARY KEY (photo_id)
);


COMMIT TRANSACTION;


BEGIN TRANSACTION;

DROP TABLE IF EXISTS comment;
DROP SEQUENCE IF EXISTS comment_id;

CREATE SEQUENCE seq_comment_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;


CREATE TABLE comment (
	comment_id int DEFAULT nextval('seq_comment_id'::regclass) NOT NULL,
	photo_id int NOT NULL,
	user_id int NOT NULL,
	message varchar(1024) NOT NULL,
	date_time timestamp NOT NULL, 
	CONSTRAINT PK_comment PRIMARY KEY (comment_id)
);


COMMIT TRANSACTION;

SELECT * FROM comment;


BEGIN TRANSACTION;

DROP TABLE IF EXISTS like;
DROP SEQUENCE IF EXISTS like_id;

CREATE SEQUENCE seq_like_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;


CREATE TABLE like (
	--comment_id int DEFAULT nextval('seq_comment_id'::regclass) NOT NULL,
	photo_id int NOT NULL,
	user_id int NOT NULL,
	date_time timestamp
	CONSTRAINT PK_user_photo PRIMARY KEY(user_id, photo_id)
);

SELECT * FROM like;

ROLLBACK;

SELECT * FROM like;





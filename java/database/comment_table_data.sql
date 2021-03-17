INSERT INTO comment (photo_id, user_id, message, date_time)
VALUES (98, 6, 'So cool! Is it tough?', '2021-03-25 4:41:33');
INSERT INTO comment (photo_id, user_id, message, date_time)
VALUES (3, 2, 'Suspendisse suscipit semper ante a posuere. Integer sapien lacus, ullamcorper eu diam nec, egestas malesuada est.', '2020-01-13 17:09:42');
INSERT INTO comment (photo_id, user_id, message, date_time)
VALUES (4, 3, 'Vestibulum ultricies, metus at porttitor aliquam, sapien augue pellentesque purus, eu tempor odio metus eu lectus. Duis sed metus neque.', '2021-01-13 17:09:42');
INSERT INTO comment (photo_id, user_id, message, date_time)
VALUES (4, 5, 'Nulla nisi ex, congue at mauris non, pretium mollis magna.', '2019-01-13 17:09:42');
INSERT INTO comment (photo_id, user_id, message, date_time)
VALUES (5, 1, 'Pellentesque ut elit vitae arcu ullamcorper porta.', '2019-05-13 17:09:42');
INSERT INTO comment (photo_id, user_id, message, date_time)
VALUES (6, 1, 'Etiam varius dui tellus, ut faucibus orci accumsan eget.', '2019-04-13 17:09:42');
INSERT INTO comment (photo_id, user_id, message, date_time)
VALUES (7, 1, 'Morbi enim ipsum, commodo non condimentum at, maximus id turpis. Phasellus rutrum malesuada eros. Maecenas ac tellus ac quam pulvinar blandit. Proin pharetra, turpis vel semper convallis, lectus ante ornare est, nec semper velit lectus id nisl. Integer vel rhoncus magna. Aliquam rutrum tortor nec quam mattis, eu efficitur odio venenatis. Pellentesque auctor suscipit nulla id auctor. Donec non dui non urna sagittis porttitor. Donec sem mauris, convallis ultricies nisl in, lacinia elementum orci. Mauris sed odio at velit ultricies ullamcorper. Fusce lobortis nulla sit amet tellus malesuada, eu fringilla lectus aliquet. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Praesent dictum odio et mattis mattis. Maecenas rutrum ligula turpis, eget dictum urna viverra ac. Integer imperdiet leo ac ante pharetra dapibus ac eget ex. Phasellus eu tincidunt eros, nec ultricies elit.', '2019-06-13 17:09:42');

select * from comment;
select * from photo;
UPDATE comment SET message = "So cool! Is it tough?" WHERE photo_id = 96;
delete from photo where photo_id = 98 AND user_id = 6;
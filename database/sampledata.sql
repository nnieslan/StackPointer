-- Locations
insert into locations (lat, lon, zip) values(42.736979, -84.483865, 0);
insert into locations (lat, lon, zip) values(34.052234, -118.243685, 0);
insert into locations (lat, lon, zip) values(42.358431, -71.059773, 0);
insert into locations (lat, lon, zip) values(41.358431, -71.059773, 0);
insert into locations (lat, lon, zip) values(34.052234, -119.243685, 0);

-- SXUsers
insert into sxusers (sxid, display_name, location_lid) values ('sxid1', 'John', 1);
insert into sxusers (sxid, display_name, location_lid) values ('sxid2', 'Pete', 2);
insert into sxusers (sxid, display_name, location_lid) values ('sxid3', 'Andy', 4);
insert into sxusers (sxid, display_name, location_lid) values ('sxid4', 'Suzy', 5);

-- Questions
insert into questions (question_text, postedby_uid)
	values ('What is the Red Hot Chili Pepper''s latest album?', 1);
insert into questions (question_text, postedby_uid)
	values ('How do I retrieve all the controls from a JSP page using Java?', 2);
insert into questions (question_text, postedby_uid)
	values ('Does malloc itself provide some kind of synchronization?', 3);
insert into questions (question_text, postedby_uid)
	values ('Tell me about IllegalArgumentException by Spring controller', 4);
insert into questions (question_text, postedby_uid)
	values ('How to set Azure Max Spending Limit or Cost CAP $ amount?', 5);
insert into questions (question_text, postedby_uid)
	values ('How come GZIP (mod_deflate) in PHP is only working for SSL (https) pages?', 1);
insert into questions (question_text, postedby_uid)
	values ('How do I start IIS Express?', 2);
insert into questions (question_text, postedby_uid)
	values ('Cloudbees Lift ClickStart pom.xml jetty dependency?', 3);
insert into questions (question_text, postedby_uid)
	values ('Deadlock in my MPI_Send() and MPI_Recv() when run on multiple nodes', 4);
insert into questions (question_text, postedby_uid)
	values ('How to extract frames from a GIF file preserving frame dimensions', 5);

-- Answers
insert into answers (answer_text, qid, postedby_uid) values ('I''m With You', 1, 2);

-- JobPostings
insert into jobpostings (date_posted, headline, description, company, location_lid)
	values(makedate(2011, 32), '.NET Web Developer', 'We have a cool job posting.', 'Supersoft LLC', 3);

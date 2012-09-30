-- Locations
insert into locations (lat, lon, zip) values(42.736979, -84.483865, 0);
insert into locations (lat, lon, zip) values(34.052234, -118.243685, 0);
insert into locations (lat, lon, zip) values(42.358431, -71.059773, 0);

-- SXUsers
insert into sxusers (sxid, display_name, location_lid) values ('sxid1', 'John', 1);
insert into sxusers (sxid, display_name, location_lid) values ('sxid2', 'Pete', 2);

-- Questions
insert into questions (question_text, postedby_uid)
	values ('What is the Red Hot Chili Pepper''s latest album?', 1);

-- Answers
insert into answers (answer_text, qid, postedby_uid) values ('I''m With You', 1, 2);

-- JobPostings
insert into jobpostings (date_posted, headline, description, company, location_lid)
	values(makedate(2011, 32), '.NET Web Developer', 'We have a cool job posting.', 'Supersoft LLC', 3);

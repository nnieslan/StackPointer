-- SXUsers
insert into sxusers (sxid, display_name, location_text) values (1, 'John', 'New York, NY');
insert into sxusers (sxid, display_name, location_text) values (2, 'Pete', 'Denver, CO');
insert into sxusers (sxid, display_name, location_text) values (3, 'Andy', 'Detroit, MI');
insert into sxusers (sxid, display_name, location_text) values (4, 'Suzy', 'Philadelphia, PA');
insert into sxusers (sxid, display_name, location_text) values (5, 'Nicole', 'Grand Rapids, MI');

-- Questions
insert into questions (postedTimestamp, title, question_text, postedby_uid)
	values ('2012-01-27 00:00:00', 'What is the Red Hot Chili Pepper''s latest album?', 'some text about chili peppers', 1);
insert into questions (postedTimestamp, title, question_text, postedby_uid)
	values ('2012-02-06 00:00:00', 'How do I retrieve all the controls from a JSP page using Java?', 'some texta bout JSP', 2);
insert into questions (postedTimestamp, title, question_text, postedby_uid)
	values ('2012-01-22 00:00:00', 'Does malloc itself provide some kind of synchronization?', 'some text about malloc', 3);
insert into questions (postedTimestamp, title, question_text, postedby_uid)
	values ('2012-12-13 00:00:00', 'Tell me about IllegalArgumentException by Spring controller', 'some text about spring', 4);
insert into questions (postedTimestamp, title, question_text, postedby_uid)
	values ('2012-08-18 00:00:00', 'How to set Azure Max Spending Limit or Cost CAP $ amount?', 'some text about azure', 5);
insert into questions (postedTimestamp, title, question_text, postedby_uid)
	values ('2012-01-27 00:00:00', 'How come GZIP (mod_deflate) in PHP is only working for SSL (https) pages?', 'some text about gzip', 1);
insert into questions (postedTimestamp, title, question_text, postedby_uid)
	values ('2012-12-31 00:00:00', 'How do I start IIS Express?', 'some text about iis', 2);
insert into questions (postedTimestamp, title, question_text, postedby_uid)
	values ('2012-07-07 00:00:00', 'Cloudbees Lift ClickStart pom.xml jetty dependency?', 'some text about pom.xml', 3);
insert into questions (postedTimestamp, title, question_text, postedby_uid)
	values ('2012-05-01 00:00:00', 'Deadlock in my MPI_Send() and MPI_Recv() when run on multiple nodes', 'some text about multi threading', 4);
insert into questions (postedTimestamp, title, question_text, postedby_uid)
	values ('2012-09-04 00:00:00', 'How to extract frames from a GIF file preserving frame dimensions', 'some text about images', 5);

-- Answers
insert into answers (postedTimestamp, answer_text, qid, postedby_uid) values ('2012-02-03 00:00:00', 'I''m With You', 1, 2);

-- JobPostings
insert into jobpostings (date_posted, linkedinid, headline, description, company, location_text)
	values(makedate(2011, 32), 123, '.NET Web Developer', 'We have a cool job posting.', 'Supersoft LLC', 'Nashville, TN');

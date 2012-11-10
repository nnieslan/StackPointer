-- This file assumes the existance of a MySQL database named 'stackpointer'.
-- For the sake of consistency please make a user called 'spuser' (for stackpointer user) and assign a password of 'pass'.
--
-- This script can be run as follows:
--     1) Open up a command prompt and login to the database
--           mysql -u<username> -p<password> stackpointer
--     2) Run the script
--           source C:\path\to\file\create.sql

-- Drop all of the tables if they already exist.
drop table if exists jobpostings;
drop table if exists answers;
drop table if exists questions;
drop table if exists sxusers;

-- SXUsers
create table sxusers (
	uid int not null auto_increment,
	sxid varchar(15) not null unique,
	display_name varchar(20),
	location varchar(50),
	primary key (uid)
);

-- Questions
create table questions (
	qid int not null auto_increment,
	postedTimestamp datetime not null,
	title varchar(100) not null,
	question_text text,
	postedby_uid int not null,
	constraint fk_question_postedby foreign key (postedby_uid)
	references sxusers(uid),
	primary key(qid)
);

-- Answers
create table answers (
	aid int not null auto_increment,
	postedTimestamp datetime not null,
	answer_text text not null,
	qid int not null,
	postedby_uid int not null,
	constraint fk_answers_question foreign key (qid)
	references questions(qid),
	constraint fk_answer_postedby foreign key (postedby_uid)
	references sxusers(uid),
	primary key (aid)
);

-- JobPostings
create table jobpostings (
	jpid int not null auto_increment,
	date_posted date,
	headline varchar(100),
	description text,
	company varchar(30),
	location varchar(50),
	primary key (jpid)
);

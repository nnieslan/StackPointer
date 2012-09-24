-- This file assumes the existance of a MySQL database named 'stackpointer'.
-- For the sake of consistency please make a user called 'spuser' (for stackpointer user) and assign a password of 'pass'.
--
-- This script can be run as follows:
--     1) Open up a command prompt and login to the database
--           mysql -u<username> -p<password> stackpointer
--     2) Run the script
--           source C:\path\to\file\create.sql

-- Drop all of the tables if they already exist.
drop table if exists answers;
drop table if exists questions;

-- Questions
create table questions (
	qid bigint not null auto_increment,
	question_text text not null,
	primary key(qid)
);

-- Answers
create table answers (
	aid bigint not null auto_increment,
	answer_text text not null,
	qid bigint not null,
	constraint fk_answers_question foreign key (qid)
	references questions(qid),
	primary key (aid)
);

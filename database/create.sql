-- This file assumes the existance of a MySQL database named 'stackpointer'.
-- This script can be run as follows:
--     1) Open up a command prompt and login to the database
--           mysql -u<username> -p<password> stackpointer
--     2) Run the script
--           source C:\path\to\file\create.sql

-- Drop all of the tables if they already exist.
drop table if exists questions;

-- Questions
create table questions (
	qid bigint not null,
	question_text text not null,
	primary key(qid)
);

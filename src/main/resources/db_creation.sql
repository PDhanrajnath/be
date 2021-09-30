drop database IF EXISTS jobs;
create database jobs;
\c jobs;
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE EXTENSION IF NOT EXISTS "postgis";
SET timezone TO 'UTC';
CREATE USER jobs_usr WITH PASSWORD 'us3rs';
GRANT ALL PRIVILEGES  ON DATABASE jobs TO jobs_usr;
-- usage: mysql -u root < init.sql
create database if not exists lambdadb;
grant all on lambdadb.* to 'lambdateam'@'localhost' identified by 'idp';
DROP TABLE IF EXISTS usertoservice;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS services;
CREATE TABLE IF NOT EXISTS users (
         username VARCHAR(100) NOT NULL PRIMARY KEY,
		 usertype VARCHAR(100) NOT NULL,
		 password VARCHAR(100) NOT NULL,
		 active INT NOT NULL
       );
CREATE TABLE IF NOT EXISTS services (
         name VARCHAR(100) NOT NULL PRIMARY KEY,
		 resource_name VARCHAR(100)
       );
CREATE TABLE UserToService
(
  user_name VARCHAR(100) NOT NULL,
  service_name VARCHAR(100) NOT NULL,
  FOREIGN KEY ( user_name ) REFERENCES users ( username ) ,
  FOREIGN KEY ( service_name ) REFERENCES services ( name ) ,
  PRIMARY KEY CLUSTERED ( user_name, service_name )
);
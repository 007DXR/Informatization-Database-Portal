CREATE DATABASE showdata;
USE showdata;
CREATE TABLE IF NOT EXISTS records(
    dataID integer not null AUTO_INCREMENT PRIMARY key,
    Country varchar(10), 
    Year char(4), 
    FirstIndexID integer, 
    SecondIndexID integer, 
    ThirdIndexID integer, 
    IndexValue double(20,16)
)
CREATE TABLE IF NOT EXISTS firstindex(
    IndexID integer AUTO_INCREMENT PRIMARY key, 
    IndexName varchar(30), 
    IndexNumeration varchar(10)
)
CREATE TABLE IF NOT EXISTS secondindex(
    IndexID integer AUTO_INCREMENT PRIMARY key, 
    IndexName varchar(30), 
    IndexNumeration varchar(10)
)
CREATE TABLE IF NOT EXISTS thirdindex(
    IndexID integer AUTO_INCREMENT PRIMARY key, 
    IndexName varchar(30), 
    IndexNumeration varchar(10)
)

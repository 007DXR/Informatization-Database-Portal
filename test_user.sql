show variables like 'validate_password%';
set global validate_password.policy=LOW
set global validate_password.length=4
create user 'java21a'@'localhost' identified by '1234';
grant select,insert,update,delete,create on *.* to 'java21a'@'localhost';
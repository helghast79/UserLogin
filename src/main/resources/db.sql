DROP DATABASE IF EXISTS javabase;
CREATE database javabase;
Use javabase;
CREATE table users(
  uid BIGINT AUTO_INCREMENT,
  username char(15) NOT NULL UNIQUE,
  password char(15) NOT NULL,
  email char(25),
  primary key (uid)
);
insert into users(uid, username, password, email) values (1, 'miguel', 'cool', 'm.a.chambel@gmail.com');
/* 1) 사용자 생성 */
CREATE USER 'webapp'@'localhost' IDENTIFIED BY '1111';

/* 2) 데이터베이스 생성 */
CREATE DATABASE webappdb
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;

/* 3) 사용자가 사용할 데이터베이스를 지정하고 사용 권한을 부여 */
GRANT ALL ON webappdb.* TO 'webapp'@'localhost';

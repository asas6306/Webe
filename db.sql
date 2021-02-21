CREATE DATABASE web;

USE web;

CREATE TABLE article (
aid INT(10) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
uid INT(10) UNSIGNED NOT NULL,
title CHAR(50) NOT NULL,
`body` TEXT NOT NULL,
regDate DATETIME DEFAULT NOW(),
updateDate DATETIME DEFAULT NOW(),
hit INT(10) UNSIGNED NOT NULL,
`like` INT(10) UNSIGNED NOT NULL,
board INT(5) UNSIGNED NOT NULL
);

CREATE TABLE `member`(
uid INT(10) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
ID CHAR(30) NOT NULL,
PW CHAR(30) NOT NULL,
nickname CHAR(30) NOT NULL,
email CHAR(50) NOT NULL,
phoneNo CHAR(20) NOT NULL,
regDate DATETIME DEFAULT NOW(),
updateDate DATETIME DEFAULT NOW()
);

CREATE TABLE `like`(
aid INT(10) UNSIGNED NOT NULL,
uid INT(10) UNSIGNED NOT NULL
);

CREATE TABLE reply(
rid INT(10) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
uid INT(10) UNSIGNED NOT NULL,
`body` TEXT NOT NULL,
relTypeCode CHAR(10) NOT NULL,
relid INT(10) UNSIGNED NOT NULL,
regDate DATETIME DEFAULT NOW(),
updateDate DATETIME DEFAULT NOW(),
KEY rel (relTypeCode , relid)
);
#ALTER TABLE reply ADD KEY rel (relTypeCode , relid); 

CREATE TABLE board(
bid INT(10) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
boardCode INT(3) NOT NULL,
boardName CHAR(20) NOT NULL
);

INSERT INTO MEMBER SET ID = 'asas', PW = '1234', nickname = 'manager', email = 'asas6306@naver.com', phoneNo = '010-3372-3049';
INSERT INTO MEMBER SET ID = 'as', PW = '1234', nickname = 'jung', email = 'asas6306@naver.com', phoneNo = '010-3372-3049';

INSERT INTO board SET boardCode=1, boardName='공지사항';
INSERT INTO board SET boardCode=2, boardName='자유게시판';

/* article 테스트 데이터 생성 */
/* insert into article (title, body, uid, tag) 
select concat('제목_', floor(rand()*1000)+1), CONCAT('내용_', FLOOR(RAND()*1000)+1), 1, 0 from article;

select count(*) from article; */
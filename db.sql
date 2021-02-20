/* 테스트 데이터 생성 */
/* insert into article (title, body, uid, tag) 
select concat('제목_', floor(rand()*1000)+1), CONCAT('내용_', FLOOR(RAND()*1000)+1), 1, 0 from article;

select count(*) from article; */


CREATE TABLE boardtag (
bid INT(5) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
tag INT(3) UNSIGNED UNIQUE,
tagname CHAR(20) NOT NULL
);

INSERT INTO boardtag SET tag=1, tagname='공지사항';
INSERT INTO boardtag SET tag=2, tagname='자유게시판';
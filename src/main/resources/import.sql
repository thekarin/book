insert into member(member_id, email, name, password, enabled, create_date, modified_date) values(1L, 'test1@test.com', '김남열', '$2a$10$Jxa0uY7zveD/YcBBjXNoGuMcnI98/uwuA8biuEyjVPvi2B4jGi04C', 'Y','2018-07-13 23:08:42.859','2018-07-13 23:08:42.859');
insert into member(member_id, email, name, password, enabled, create_date, modified_date) values(2L, 'test2@test.com', '김남일', '$2a$10$Jxa0uY7zveD/YcBBjXNoGuMcnI98/uwuA8biuEyjVPvi2B4jGi04C', 'Y','2018-07-13 23:08:42.859','2018-07-13 23:08:42.859');

insert into bookmark(bookmark_id, isbn, title, member_id, create_date, modified_date) values(1L, '9791188621279', '스프링 부트로 배우는 자바 웹 개발', 1L, '2018-07-13 23:08:42.859','2018-07-13 23:08:42.859');
insert into bookmark(bookmark_id, isbn, title, member_id, create_date, modified_date) values(2L, '9788960777330', '자바 ORM 표준 JPA 프로그래밍', 1L, '2018-07-13 23:08:42.859','2018-07-13 23:08:42.859');

insert into search_history(member_id, search_date, search_keyword) values(1L, '2018-07-13 23:08:42.859', '스프링부트');
insert into search_history(member_id, search_date, search_keyword) values(1L, '2018-07-13 23:08:44.859', '미움받을용기');
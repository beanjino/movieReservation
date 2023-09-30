INSERT INTO member(email,password, name, is_Admin) VALUES('a@a', '1111','가', true);
INSERT INTO member(email,password, name) VALUES('b@b', '2222','나');
INSERT INTO member(email,password, name) VALUES('c@c', '3333','나');

INSERT INTO movie(name,runningtime, price, genre, director, actors, rating, age) VALUES('가', '2시간 30분', 1000, '범죄', '봉준호', '이병헌,정우성', 4.0, '15세 이용가');
INSERT INTO movie(name,runningtime, price, genre, director, actors, rating, age) VALUES('나', '2시간 40분', 2000, '로맨스', '하정우', '하정우, 조인성', 4.3, '12세 이용가');
INSERT INTO movie(name,runningtime, price, genre, director, actors, rating, age) VALUES('다', '2시간 10분', 3000, '코미디', '박찬욱', '전지현, 공유', 4.2, '전체 이용가');
INSERT INTO movie(name,runningtime, price, genre, director, actors, rating, age) VALUES('라', '2시간 5분', 4000, '스릴러', '김기덕', '김혜수, 조승우', 3.8, '청소년 관란불가');
INSERT INTO movie(name,runningtime, price, genre, director, actors, rating, age) VALUES('마', '2시간 12분', 5000, '공포', '봉준호', '한가인,정우성', 3.0, '15세 이용가');

INSERT INTO comment(movie_id, nickname, body) VALUES(4, 'Park', '굿 윌 헌팅');
INSERT INTO comment(movie_id, nickname, body) VALUES(4, 'Kim', '아이 엠 샘');
INSERT INTO comment(movie_id, nickname, body) VALUES(4, 'Choi', '쇼생크 탈출');

INSERT INTO theater(city, region) VALUES('포항', '경북');
INSERT INTO theater(city, region) VALUES('서울', '서울');
INSERT INTO theater(city, region) VALUES('성남', '경기');

INSERT INTO movie_theater(theater_id, floor, screening_Movie, entire_Seat, number, date) VALUES(1, 1, '가', 30, '1관', '2023-09-09');
INSERT INTO movie_theater(theater_id, floor, screening_Movie, entire_Seat, number, date) VALUES(1, 1, '나', 40, '2관', '2023-09-09');
INSERT INTO movie_theater(theater_id, floor, screening_Movie, entire_Seat, number, date) VALUES(1, 2, '다', 50, '3관', '2023-09-09');
INSERT INTO movie_theater(theater_id, floor, screening_Movie, entire_Seat, number, date) VALUES(1, 2, '라', 60, '4관', '2023-09-09');
INSERT INTO movie_theater(theater_id, floor, screening_Movie, entire_Seat, number, date) VALUES(1, 3, '가', 70, '5관', '2023-09-09');
INSERT INTO movie_theater(theater_id, floor, screening_Movie, entire_Seat, number, date) VALUES(1, 3, '나', 80, '6관', '2023-09-09');
INSERT INTO movie_theater(theater_id, floor, screening_Movie, entire_Seat, number, date) VALUES(1, 4, '다', 90, '7관', '2023-09-09');
INSERT INTO movie_theater(theater_id, floor, screening_Movie, entire_Seat, number, date) VALUES(1, 4, '라', 100, '8관', '2023-09-09');

INSERT INTO start_time(movie_Theater_id, left_Seat, time) VALUES(1, 30, '10:00');
INSERT INTO start_time(movie_Theater_id, left_Seat, time) VALUES(1, 30, '11:00');
INSERT INTO start_time(movie_Theater_id, left_Seat, time) VALUES(1, 30, '12:00');
INSERT INTO start_time(movie_Theater_id, left_Seat, time) VALUES(1, 30, '13:00');
INSERT INTO start_time(movie_Theater_id, left_Seat, time) VALUES(5, 70, '10:30');
INSERT INTO start_time(movie_Theater_id, left_Seat, time) VALUES(5, 70, '11:30');
INSERT INTO start_time(movie_Theater_id, left_Seat, time) VALUES(5, 70, '12:30');
INSERT INTO start_time(movie_Theater_id, left_Seat, time) VALUES(5, 70, '13:30');

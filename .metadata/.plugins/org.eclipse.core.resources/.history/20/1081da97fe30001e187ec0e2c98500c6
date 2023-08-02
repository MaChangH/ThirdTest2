create table tp_board (
	tp_b_no number(4) primary key,
	tp_b_writer varchar2(15 char) not null,
	tp_b_title varchar2(50 char) not null,
	tp_b_txt varchar2(300 char) not null,
	tp_b_photo varchar2(500 char),
	tp_b_when date not null
);

create sequence tp_board_seq;

insert into tp_board (tp_b_no, tp_b_writer, tp_b_title, tp_b_txt, tp_b_when) values(tp_board_seq.nextval, '트기', '안녕안녕', '진짜 안녕안녕 나는 지수야', sysdate);
insert into tp_board (tp_b_no, tp_b_writer, tp_b_title, tp_b_txt, tp_b_when) values(tp_board_seq.nextval, '테스트1', '테스트용 글', '테스트용 게시글의 내용', sysdate);
insert into tp_board (tp_b_no, tp_b_writer, tp_b_title, tp_b_txt, tp_b_when) values(tp_board_seq.nextval, '테스트a', 'test', 'text for test', sysdate);

drop table tp_board cascade constraint purge
drop table tp_reply cascade constraint purge
drop table tp_reply cascade constraint purge

select * from TP_BOARD


create table tp_reply (
	tp_r_no number(4) primary key,
	tp_r_b_no number(4) not null,
	tp_r_writer varchar2(10 char) not null,
	tp_r_text varchar2(256 char) not null,
	tp_r_date date not null
);

create sequence tp_reply_seq;
select * from TP_reply

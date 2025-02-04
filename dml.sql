
-- board
select * from board;
insert into board(seq, member_seq, title, content, creationDate, modificationDate, count) 
values(seqBoard.nextVal, '스프링팀', '학원 팀', default, default, 0);
update board set title = '프로젝트' where seq = 1;

-- chat
insert into chat(seq, creationDate, content) 
values(seqChat.nextVal, default, '');
update chat set content = '' where seq = 1;

-- class
select * from class;
insert into class(seq, className) values(seqClass.nextVal, 'Front-end 개발자(aka 클라이언트 개발자) 과정A');
insert into class(seq, className) values(seqClass.nextVal, 'Back-end 개발자(aka 서버 개발자) 과정A');
insert into class(seq, className) values(seqClass.nextVal, 'Full-stack 개발자 과정');
insert into class(seq, className) values(seqClass.nextVal, 'Mobile 개발자 과정A');
--update class set className = '' where seq = 1;

--member
select * from member;
insert into member(seq, username, password, permission, name, birth, gender, phone, status) 
values(seqMember.nextVal, 'hong1234', 'hong1111', '0', '홍길동', TO_DATE('1995-04-08', 'YYYY-MM-DD'), 'm', '01012345678', '1');
commit;

-- classList
select * from classList;
insert into classList(seq, member_seq, class_seq) values(seqClassList.nextVal, 21, 1);
insert into classList(seq, member_seq, class_seq) values(seqClassList.nextVal, 22, 1);


select c.className, m.username 
from classList l 
inner join  class c
on l.class_seq = c.seq
inner join member m
on l.member_seq = m.seq;

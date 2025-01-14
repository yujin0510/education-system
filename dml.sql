
-- board
insert into board(seq, member_seq, title, content, creationDate, modificationDate, count) 
values(seqBoard.nextVal, '스프링팀', '학원 팀', default, default, 0);
update board set title = '프로젝트' where seq = 1;

-- chat
insert into chat(seq, creationDate, content) 
values(seqChat.nextVal, default, '');
update chat set content = '' where seq = 1;

-- class
insert into class(seq, className) values(seqClass.nextVal, 'Front-end 개발자(aka 클라이언트 개발자) 과정A');
insert into class(seq, className) values(seqClass.nextVal, 'Back-end 개발자(aka 서버 개발자) 과정A');
insert into class(seq, className) values(seqClass.nextVal, 'Full-stack 개발자 과정');
insert into class(seq, className) values(seqClass.nextVal, 'Mobile 개발자 과정A');
update class set className = '' where seq = 1;



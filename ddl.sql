show user;

// 계정생성
--create user c##education identified by java1234;
--grant connect, resource, dba to c##education;


--교육생 계정 
create sequence seqMember;
 --drop table member;

CREATE TABLE member (
	seq	number	PRIMARY KEY,
	username 	varchar2(500)	NOT NULL,
	password	varchar2(500)	NOT NULL,
	permission	char(1)	NOT NULL,   --권한(0:사용자, 1:관리자)
	name	varchar2(30)	NOT NULL,
	birth	date	NOT NULL,
	gender	char(1)	NOT NULL,
	phone	varchar2(30)	NOT NULL,
	status	char(1)	NOT NULL        --탈퇴여부(0:탈퇴, 1:회원)
);

create sequence seqBoard;
-- drop sequence seqBoard;
-- drop table board;
-- delete from board;
CREATE TABLE board (
    seq NUMBER PRIMARY KEY,
    member_seq NUMBER NOT NULL,
    title VARCHAR2(1000) NOT NULL,
    content VARCHAR2(4000) NOT NULL,
    creationDate date DEFAULT TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') NOT NULL,
    modificationDate date DEFAULT SYSDATE NULL,
    count NUMBER DEFAULT 0 NOT NULL,
    CONSTRAINT fk_board_member FOREIGN KEY (member_seq) REFERENCES member(seq)
);
commit;

create sequence seqClass;
-- drop sequence seqClass;
-- drop table class;
CREATE TABLE class (
	seq	number	PRIMARY KEY,
	className	varchar2(500)	NOT NULL
);


create sequence seqChat;
-- drop sequence seqChat;
-- drop table chat;
CREATE TABLE chat (
	seq	number	PRIMARY KEY,
	creationDate date DEFAULT SYSDATE NOT NULL,
	content	varchar2(4000)	NOT NULL
);

create sequence seqChatList;
-- drop sequence seqChatList;
-- drop table chatList;
CREATE TABLE chatList (
	seq	number	PRIMARY KEY,
	member_seq	number	NOT NULL,
	chat_seq	number	NOT NULL,
	class_seq	number	NOT NULL
    ,CONSTRAINT fk_chatList_member FOREIGN KEY (member_seq) REFERENCES member(seq)
    ,CONSTRAINT fk_chatList_chat FOREIGN KEY (chat_seq) REFERENCES chat(seq)
    ,CONSTRAINT fk_chatList_class FOREIGN KEY (class_seq) REFERENCES class(seq)
);





commit;

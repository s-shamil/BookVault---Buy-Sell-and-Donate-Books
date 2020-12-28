drop table  "AUTHOR_BOOK";
drop table  "GENRE_BOOK";
drop table  "D_WISHLIST";
drop table  "D_STOCK";
drop table  "NOTIFICATION" ;
drop table  "D_HISTORY";
drop table  "D_DISTRIBUTION";
--drop table  "CREDIT_TRANSFER";
drop table  "GENRE" ;
drop table  "AUTHOR";
drop table  "BOOK_ENTRY";
drop table "BOOK_CATALOG";
drop table "PUBLISHER" ;
drop table  "USER";




CREATE TABLE "PUBLISHER" (
  "PUB_ID" varchar2(20),
  "PUB_NAME" varchar2(40),
  "RATING" char(1),
  PRIMARY KEY ("PUB_ID")
);
CREATE TABLE "BOOK_CATALOG" (
  "ISBN" varchar2(20),
  "PUB_ID" varchar2(20) references PUBLISHER(PUB_ID),
  "BOOK_NAME" varchar2(100),
  "PRICE_A" number,
  "PRICE_E" number,
  "RATING" char(1),
  PRIMARY KEY ("ISBN")
);
CREATE TABLE "GENRE" (
  "GENRE_ID" varchar2(20),
  "GENRE_NAME" varchar2(20),
  "POPULARITY" char(1),
  PRIMARY KEY ("GENRE_ID")
);

CREATE TABLE "AUTHOR" (
  "AUTHOR_ID" varchar2(20),
  "AUTHOR_NAME" varchar2(60),
  "POPULARITY" char(1),
  PRIMARY KEY ("AUTHOR_ID")
);

CREATE TABLE "USER" (
  "USER_ID" varchar2(20),
  "NAME" varchar2(60),
  "PHONE_NUM" char(14),
  "EMAIL" varchar2(100),
  "CURR_CREDIT" number,
  "LOCATION" varchar2(100),
  "BALANCE" number,
  "ZIP_CODE" varchar2(20),
  "PASSWORD" varchar2(20),
  "LAST_LOGIN" date,
  PRIMARY KEY ("USER_ID")
);

CREATE TABLE "AUTHOR_BOOK" (
  "AUTHOR_ID" varchar2(20) references AUTHOR(AUTHOR_ID),
  "ISBN" varchar2(20) references BOOK_CATALOG(ISBN)
);

CREATE TABLE "GENRE_BOOK" (
  "GENRE_ID" varchar2(20) references GENRE(GENRE_ID),
  "ISBN" varchar2(20) references BOOK_CATALOG(ISBN)
);

CREATE TABLE "BOOK_ENTRY" (
  "BOOK_ID" varchar2(20),
  "ISBN" varchar2(20) references BOOK_CATALOG(ISBN),
  "DONOR_ID" varchar2(20) references "USER"(USER_ID),
  PRIMARY KEY ("BOOK_ID")
);

CREATE TABLE "D_WISHLIST" (
  "USER_ID" varchar2(20) references "USER"(USER_ID),
  "ISBN" varchar2(20) references BOOK_CATALOG(ISBN),
  "WISH_DATE" date
);

CREATE TABLE "D_STOCK" (
  "BOOK_ID" varchar2(20) references BOOK_ENTRY(BOOK_ID),
  "STOCK_ID" number
);

CREATE TABLE "NOTIFICATION" (
  "NOTI_ID" varchar2(20),
  "RECEIVER_ID" varchar2(20) references "USER"(USER_ID),
  "BOOK_ID" varchar2(20) references BOOK_ENTRY(BOOK_ID),
  "NOTI_DATE" date,
  "TEXT" varchar2(100),
  PRIMARY KEY ("NOTI_ID")
);

CREATE TABLE "D_HISTORY" (
  "BOOK_ID" varchar2(20) references BOOK_ENTRY(BOOK_ID),
  "RECIPIENT_ID" varchar2(20) references "USER"(USER_ID),
  "CH_DONOR" number,
  "CH_RECIPIENT" number,
  "DELIVERY_DATE" date

);
/*
CREATE TABLE "D_ORDER" (
  "BOOK_ID" varchar2(20) references BOOK_ENTRY(BOOK_ID),
  "APPLICANT_ID" varchar2(20) references "USER"(USER_ID)
);
*/
CREATE TABLE "D_DISTRIBUTION" (
  "BOOK_ID" varchar2(20) references BOOK_ENTRY(BOOK_ID),
  "RECIPIENT_ID" varchar2(20) references"USER"(USER_ID),
  "DUE_DATE" date
);
/*
CREATE TABLE "CREDIT_TRANSFER" (
  "CRT_ID" varchar2(20),
"CRT_ID" varchar2(20) references CREDIT_TRANSFER(CRT_ID),
  PRIMARY KEY ("CRT_ID")
);
*/
CREATE TABLE "TEMP"
(
"ISBN" varchar2(20) references BOOK_CATALOG(ISBN),
"BOOK_NAME" varchar2(100),
"AUTHOR_NAME" varchar2(80)

);



insert into AUTHOR_BOOK (AUTHOR_ID, ISBN)
SELECT A.AUTHOR_ID,E.ISBN
FROM AUTHOR A, TEMP E
WHERE (A.AUTHOR_NAME=E.AUTHOR_NAME);

drop table d_wishlist;

CREATE TABLE "D_WISHLIST" (
  "USER_ID" varchar2(20) references "USER"(USER_ID),
  "ISBN" varchar2(20) references BOOK_CATALOG(ISBN),
  "WISH_DATE" date
);
drop table notification;


CREATE TABLE "NOTIFICATION" (
  "NOTI_ID" varchar2(20),
  "RECEIVER_ID" varchar2(20) references "USER"(USER_ID),
  "BOOK_ID" varchar2(20) references BOOK_ENTRY(BOOK_ID),
  "NOTI_DATE" date,
  "TEXT" varchar2(100),
  PRIMARY KEY ("NOTI_ID")
);







Insert into GENRE (GENRE_ID,GENRE_NAME,POPULARITY) values ('HT4381','Literature','D');
Insert into GENRE (GENRE_ID,GENRE_NAME,POPULARITY) values ('KY4449','Music','C');
Insert into GENRE (GENRE_ID,GENRE_NAME,POPULARITY) values ('LH2754','Photography','D');
Insert into GENRE (GENRE_ID,GENRE_NAME,POPULARITY) values ('RL5356','Textbook','D');
Insert into GENRE (GENRE_ID,GENRE_NAME,POPULARITY) values ('TL1832','Accounting','C');
Insert into GENRE (GENRE_ID,GENRE_NAME,POPULARITY) values ('SL9126','Food','E');
Insert into GENRE (GENRE_ID,GENRE_NAME,POPULARITY) values ('QM5297','Management','D');
Insert into GENRE (GENRE_ID,GENRE_NAME,POPULARITY) values ('ZS6159','Computer Networks','D');
Insert into GENRE (GENRE_ID,GENRE_NAME,POPULARITY) values ('SL5144','Programming','E');
Insert into GENRE (GENRE_ID,GENRE_NAME,POPULARITY) values ('RL5484','Software Engineering','D');
Insert into GENRE (GENRE_ID,GENRE_NAME,POPULARITY) values ('BP9584','Adult Education','E');
Insert into GENRE (GENRE_ID,GENRE_NAME,POPULARITY) values ('VG3367','Poetry','C');
Insert into GENRE (GENRE_ID,GENRE_NAME,POPULARITY) values ('LB7689','Medicine','D');
Insert into GENRE (GENRE_ID,GENRE_NAME,POPULARITY) values ('ZC7619','Mental Health','A');
Insert into GENRE (GENRE_ID,GENRE_NAME,POPULARITY) values ('KN1124','Encyclopedia','C');
Insert into GENRE (GENRE_ID,GENRE_NAME,POPULARITY) values ('HD3487','Travel','C');
Insert into GENRE (GENRE_ID,GENRE_NAME,POPULARITY) values ('LJ1838','Dictionary','D');
Insert into GENRE (GENRE_ID,GENRE_NAME,POPULARITY) values ('MB8471','Romance','D');
Insert into GENRE (GENRE_ID,GENRE_NAME,POPULARITY) values ('CT3189','Biology','D');
Insert into GENRE (GENRE_ID,GENRE_NAME,POPULARITY) values ('DH7493','Philosophy','C');
Insert into GENRE (GENRE_ID,GENRE_NAME,POPULARITY) values ('GL3123','Psychology','E');
Insert into GENRE (GENRE_ID,GENRE_NAME,POPULARITY) values ('HW3678','Physics','E');
Insert into GENRE (GENRE_ID,GENRE_NAME,POPULARITY) values ('RL3796','Religion','B');
Insert into GENRE (GENRE_ID,GENRE_NAME,POPULARITY) values ('SM2562','Magazing','A');
Insert into GENRE (GENRE_ID,GENRE_NAME,POPULARITY) values ('RK3933','Novel','D');

    insert into GENRE_BOOK (GENRE_ID, ISBN)
    SELECT GENRE_ID FROM
    ( SELECT * FROM GENRE
    ORDER BY dbms_random.value )
    WHERE rownum = 1,  ISBN FROM BOOK_CATALOG;

 SELECT * FROM GENRE
ORDER BY dbms_random.value;
drop table genre_book;
drop table genre;


CREATE TABLE "GENRE" (
  "GENRE_ID" varchar2(20),
  "GENRE_NAME" varchar2(20),
  "POPULARITY" char(1),
  PRIMARY KEY ("GENRE_ID")
);

CREATE TABLE "GENRE_BOOK" (
  "GENRE_ID" varchar2(20) references GENRE(GENRE_ID),
  "ISBN" varchar2(20) references BOOK_CATALOG(ISBN)
);

SELECT 'DROP TABLE "' || TABLE_NAME || '" CASCADE CONSTRAINTS;' FROM user_tables;



CREATE TABLE "AUTHOR_BOOK" (
  "AUTHOR_ID" varchar2(20) references AUTHOR(AUTHOR_ID),
  "ISBN" varchar2(20) references BOOK_CATALOG(ISBN)
);

UPDATE "USER"
SET NAME='Rayhan Rashed'
WHERE USER_ID=61001;

UPDATE "USER"
SET PASSWORD='123'
WHERE USER_ID=61001;

UPDATE "USER"
SET NAME='Salman Shamil'
WHERE USER_ID=61002;

UPDATE "USER"
SET PASSWORD='1234'
WHERE USER_ID=61002;


SELECT BOOK_NAME,ISBN,AUTHOR_NAME
FROM BOOK_CATALOG B JOIN AUTHOR_BOOK AB USING(ISBN) JOIN AUTHOR USING(AUTHOR_ID)
WHERE (UPPER(BOOK_NAME) LIKE UPPER('%animal%')) OR (UPPER(BOOK_NAME) LIKE UPPER('%farm%')) OR 
WHERE (UPPER(AUTHOR_NAME) LIKE UPPER('%animal%')) OR (UPPER(AUTHOR_NAME) LIKE UPPER('%farm%'));

 SELECT USER_ID FROM "USER" WHERE (UPPER("NAME") LIKE UPPER('%Rayh%'));

SELECT ISBN, FIND_BOOK(ISBN)
FROM D_WISHLIST
WHERE USER_ID=( SELECT USER_ID FROM "USER" WHERE UPPER(NAME) LIKE UPPER('%Rayh%'));


------NOW-----

SELECT BOOK_NAME,ISBN,WISH_DATE
FROM 
BOOK_CATALOG JOIN D_WISHLIST USING(ISBN)
WHERE USER_ID='61001';

--------------

CREATE OR REPLACE FUNCTION FIND_BOOK(GIVEN IN VARCHAR2)
RETURN VARCHAR2 IS
    BOOK VARCHAR2(100);

BEGIN
    SELECT BOOK_NAME INTO BOOK FROM BOOK_CATALOG WHERE(ISBN=GIVEN);
    RETURN BOOK;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
    RETURN 'NO BOOK WITH THIS ISBN';
    WHEN OTHERS THEN
    RETURN 'UNKNOWN ERROR. GENERATED FROM PLSQL';
END;
/


CREATE OR REPLACE PROCEDURE ADD_TO_WISH(GIVEN_ISBN IN VARCHAR2,GIVEN_ID VARCHAR2) IS
    IDX NUMBER;
BEGIN
    IDX:=0;
    FOR R IN (SELECT ISBN, USER_ID FROM D_WISHLIST)
    LOOP
        IF(R.ISBN=GIVEN_ISBN AND R.USER_ID=GIVEN_ID) THEN
            IDX:=1;
        END IF;
    END LOOP;
    DBMS_OUTPUT.PUT_LINE('VALUE ISNOT' || IDX);
    IF (IDX=0) THEN
        DBMS_OUTPUT.PUT_LINE('VALUE IS HERE');
        INSERT INTO D_WISHLIST (ISBN, USER_ID,WISH_DATE)
        VALUES (GIVEN_ISBN,GIVEN_ID,SYSDATE);
    END IF;
END;







CREATE OR REPLACE PROCEDURE BDP(GIVEN_ISBN IN VARCHAR2,GIVEN_ID IN VARCHAR2) IS
    IDX VARCHAR2(50);
BEGIN
    IDX:=0;
    FOR R IN (SELECT ISBN, USER_ID FROM D_WISHLIST)
    LOOP
        IF((R.ISBN=GIVEN_ISBN) AND (R.USER_ID=GIVEN_ID)) THEN
            IDX:=1;
        END IF;
    END LOOP;
    DBMS_OUTPUT.PUT_LINE('VALUE IS ' || IDX);
END;
/
SET SERVEROUTPUT ON;
DECLARE 
BEGIN
BDP(100000,61001);
END;

DECLARE 
BEGIN
ADD_TO_WISH(100031,61001);
END;


SELECT ISBN,BOOK_NAME AS "BOOK NAME",AUTHOR_NAME AS "AUTHOR_NAME",PRICE_E AS "MARKET PRICE"
FROM D_WISHLIST JOIN BOOK_CATALOG USING(ISBN) JOIN AUTHOR_BOOK USING (ISBN) JOIN AUTHOR USING(AUTHOR_ID)
WHERE USER_ID='61001';



create sequence book_isbn_auto start with 101305 increment by 1;



create or replace trigger book_isbn_insert
before insert on BOOK_CATALOG
for each row
begin
    select book_isbn_auto.nextval into :new.ISBN from dual;
end;


insert into book_catalog (PUB_ID,BOOK_NAME,PRICE_A,PRICE_E,RATING)
values ('7012','TOMAR BOI',14,66,'A');


select * from book_catalog where book_name like('%BOI%');



--TO SHAMIL
--------------
create or replace PROCEDURE ADD_USER(
TID IN VARCHAR2,
TNAME IN VARCHAR2, 
TPHONE IN CHAR, 
TEMAIL IN VARCHAR2,
TLOCATION IN VARCHAR2,
TZIP IN VARCHAR2,
TPASS IN VARCHAR2) IS
    IDX NUMBER;
BEGIN
    IDX:=0;
    FOR R IN (SELECT USER_ID FROM "USER")
    LOOP
        IF(R.USER_ID=TID) THEN
            IDX:=1;
        END IF;
    END LOOP;

    IF (IDX=0) THEN
        
        INSERT INTO "USER"(USER_ID,NAME,PHONE_NUM,EMAIL,CURR_CREDIT,LOCATION,BALANCE,ZIP_CODE,PASSWORD)
        VALUES (TID,TNAME,TPHONE,TEMAIL,500,TLOCATION,0,TZIP,TPASS);
    END IF;
END;



----------------------------------------------------------------

create sequence noti_id_auto start with 7100227 increment by 1;


create or replace trigger noti_insert
before insert on NOTIFICATION
for each row
begin
    select noti_id_auto.nextval into :new.NOTI_ID from dual;
end;

----------------------
create sequence book_entry_auto start with 500901 increment by 1;

create or replace trigger book_entry_insert
before insert on BOOK_ENTRY
for each row
begin
    select book_entry_auto.nextval into :new.BOOK_ID from dual;
end;

-----------------------

create or replace trigger book_entry_triggers_stock
before insert on BOOK_ENTRY
for each row
declare
    temp_id varchar2(20);
    temp_isbn varchar2(20);
    temp_donor_id varchar2(20);
    
begin
    temp_id:= :NEW.BOOK_ID;
    temp_isbn:= :NEW.ISBN;
    temp_donor_id:= :NEW.DONOR_ID;
    INSERT INTO D_STOCK (BOOK_ID,STOCK_ID)
    VALUES (temp_id,2);
    ------STOCK NUMBER PORE DEKHBO;
end;



------------





-------
create or replace trigger stock_triggers_notif
before insert
on D_STOCK
for each row
DECLARE
    temp_book_id varchar2(20);
    receiver_id  varchar2(20);
    temp_isbn varchar2(20);
BEGIN
    temp_book_id:= :new.BOOK_ID;
    SELECT ISBN into temp_isbn FROM BOOK_ENTRY WHERE BOOK_ID=temp_book_id;
    SELECT USER_ID into receiver_id
    FROM(        
        SELECT USER_ID,CURR_CREDIT,ISBN FROM D_WISHLIST JOIN "USER" USING(USER_ID)
        WHERE (ISBN=temp_isbn)
        ORDER BY CURR_CREDIT DESC
        ) 
    WHERE 1=ROWNUM;
             
    -----------
    INSERT INTO NOTIFICATION (RECEIVER_ID,BOOK_ID,NOTI_DATE)
    VALUES (receiver_id,temp_book_id,SYSDATE);

END;
    
  declare
  begin  
    SELECT USER_ID into receiver_id
    FROM(        
        SELECT USER_ID,CURR_CREDIT,ISBN FROM D_WISHLIST JOIN "USER" USING(USER_ID)
        WHERE (ISBN=temp_isbn)
        ORDER BY CURR_CREDIT DESC
        ) 
    WHERE 1=ROWNUM;
    end;
    dbms_output.put_line(receiver_id)
    
    
DECLARE
receiver_id varchar2(50);
BEGIN

 SELECT USER_ID INTO receiver_id FROM(SELECT USER_ID FROM D_WISHLIST WHERE (ISBN='100002'))KP WHERE ROWNUM<=1;
DBMS_OUTPUT.PUT_LINE(receiver_id);
END;
    
SELECT USER_ID 
                  FROM(SELECT USER_ID FROM D_WISHLIST
                        WHERE (ISBN='100002'))BK
                        WHERE ROWNUM<=1;  

    
    INSERT INTO NOTIFICATION (RECEIVER_ID,BOOK_ID,NOTI_DATE)
    VALUES ('61001',500001,SYSDATE);

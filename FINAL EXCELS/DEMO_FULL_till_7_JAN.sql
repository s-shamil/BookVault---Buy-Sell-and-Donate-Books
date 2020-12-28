



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
 
show_serveroutput.on;    
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



















CREATE TABLE "DONATION_Q" (
  "ISBN" varchar2(20) references BOOK_CATALOG(ISBN),
  "DONOR_ID" varchar2(20) references "USER"(USER_ID),
  "GIVE_DATE" DATE
);



CREATE TABLE "BOOK_CONDITION" (
    "BOOK_ID" varchar2(20) references BOOK_ENTRY(BOOK_ID),
    "CONDITION" varchar2(20)

);


SELECT USER_ID FROM D_WISHLIST WHERE (ISBN='100642');

SELECT USER_ID FROM(
SELECT USER_ID FROM (SELECT USER_ID FROM D_WISHLIST WHERE (ISBN='100010'))kb join "USER" U using(USER_ID)

ORDER BY(CURR_CREDIT) DESC) WHERE ROWNUM<=1;




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
    SELECT USER_ID into receiver_id FROM(
        SELECT USER_ID 
        FROM (SELECT USER_ID FROM D_WISHLIST WHERE (ISBN=temp_isbn))kb join "USER" U using(USER_ID)
        ORDER BY(CURR_CREDIT) DESC)KP
    WHERE ROWNUM<=1;
    -----------
    INSERT INTO NOTIFICATION (RECEIVER_ID,BOOK_ID,NOTI_DATE)
    VALUES (receiver_id,temp_book_id,SYSDATE);

END;




create or replace TYPE MY_DONATED IS OBJECT( BOOK_ID varchar2(20),BOOK_NAME varchar2(100));

create or replace TYPE MY_DONATED_TAB IS TABLE OF MY_DONATED;

create or replace FUNCTION SHOW_MY_DONATED(MY_ID IN VARCHAR2)
RETURN MY_DONATED_TAB
IS
    TEMP_TAB MY_DONATED_TAB := MY_DONATED_TAB();
    NN INTEGER := 0;
BEGIN
    FOR RR IN (SELECT BOOK_ID,BOOK_NAME FROM BOOK_ENTRY JOIN BOOK_CATALOG USING(ISBN) WHERE DONOR_ID=MY_ID)
    LOOP
        TEMP_TAB.EXTEND;
        NN := NN+1;
        TEMP_TAB(NN) := MY_DONATED(RR.BOOK_ID, RR.BOOK_NAME);
    END LOOP;
    RETURN TEMP_TAB;
END;

-------------------------------------------

create or replace TYPE MY_RECEIVED IS OBJECT( BOOK_ID varchar2(20),BOOK_NAME varchar2(100), RECEIVE_DATE DATE);

create or replace TYPE MY_RECEIVED_TAB IS TABLE OF MY_RECEIVED;

create or replace FUNCTION SHOW_MY_RECEIVED(MY_ID IN VARCHAR2)
RETURN MY_RECEIVED_TAB
IS
    TEMP_TAB MY_RECEIVED_TAB := MY_RECEIVED_TAB();
    NN INTEGER := 0;
BEGIN
    FOR RR IN (SELECT BOOK_ID,BOOK_NAME,DELIVERY_DATE FROM D_HISTORY JOIN BOOK_ENTRY USING(BOOK_ID) JOIN BOOK_CATALOG USING(ISBN) WHERE RECIPIENT_ID=MY_ID)
    LOOP
        TEMP_TAB.EXTEND;
        NN := NN+1;
        TEMP_TAB(NN) := MY_RECEIVED(RR.BOOK_ID, RR.BOOK_NAME,RR.DELIVERY_DATE);
    END LOOP;
    RETURN TEMP_TAB;
END;


---------------------------------------

create or replace trigger wishlist_triggers_notif
before insert
on D_WISHLIST
for each row
DECLARE
    temp_book_id varchar2(20);
    receiver_id  varchar2(20);
    temp_isbn varchar2(20);
BEGIN
    temp_book_id:= :new.BOOK_ID;
    SELECT ISBN into temp_isbn FROM BOOK_ENTRY WHERE BOOK_ID=temp_book_id;
    SELECT USER_ID into receiver_id FROM(
        SELECT USER_ID 
        FROM (SELECT USER_ID FROM D_WISHLIST WHERE (ISBN=temp_isbn))kb join "USER" U using(USER_ID)
        ORDER BY(CURR_CREDIT) DESC)KP
    WHERE ROWNUM<=1;
    -----------
    INSERT INTO NOTIFICATION (RECEIVER_ID,BOOK_ID,NOTI_DATE)
    VALUES (receiver_id,temp_book_id,SYSDATE);

END;


declare
kp integer;
begin
    kp:=0;
    SELECT count(*) into kp FROM(
        SELECT USER_ID 
        FROM (SELECT USER_ID FROM D_WISHLIST WHERE (ISBN='100005'))kb join "USER" U using(USER_ID)
        ORDER BY(CURR_CREDIT) DESC);
    dbms_output.put_line(kp);
end;













SELECT BOOK_ID,BOOK_NAME,AUTHOR_NAME,PRICE_E 
FROM "NOTIFICATION" JOIN
BOOK_ENTRY USING(BOOK_ID) JOIN
BOOK_CATALOG USING(ISBN) JOIN 
AUTHOR_BOOK USING (ISBN) JOIN 
AUTHOR USING(AUTHOR_ID) 
WHERE RECEIVER_ID='61001'
ORDER BY NOTI_DATE;



create or replace TYPE MY_NOTI IS OBJECT( BOOK_ID varchar2(20),BOOK_NAME varchar2(100),AUTHOR_NAME varchar2(60),PRICE_E number);

create or replace TYPE MY_NOTI_TAB IS TABLE OF MY_NOTI;

create or replace FUNCTION SHOW_NOTI(MY_ID IN VARCHAR2)
RETURN MY_NOTI_TAB
IS
    TEMP_TAB MY_NOTI_TAB := MY_NOTI_TAB();
    NN INTEGER := 0;
BEGIN
    FOR RR IN (SELECT BOOK_ID,BOOK_NAME,AUTHOR_NAME,PRICE_E FROM "NOTIFICATION" JOIN BOOK_ENTRY USING(BOOK_ID) JOIN BOOK_CATALOG USING(ISBN) JOIN AUTHOR_BOOK USING (ISBN) JOIN AUTHOR USING(AUTHOR_ID) WHERE RECEIVER_ID=MY_ID)
    LOOP
        TEMP_TAB.EXTEND;
        NN := NN+1;
        TEMP_TAB(NN) := MY_NOTI(RR.BOOK_ID, RR.BOOK_NAME, RR.AUTHOR_NAME, RR.PRICE_E);
    END LOOP;
    RETURN TEMP_TAB;
END;


CREATE TABLE STUDENTS(
STUDENT_NAME VARCHAR2(250),
CGPA NUMBER
) ;


CREATE OR REPLACE TRIGGER HELLO_WORLD
AFTER INSERT
ON STUDENTS
for each row
DECLARE
kb number;
BEGIN
kb:=1;
if kb=1 then
    kakka(:new.STUDENT_NAME);
end if;
END ;


create or replace procedure kakka (eid in varchar2) IS
begin
delete from STUDENTS WHERE STUDENT_NAME=eid;
dbms_output.put_line('holo');
end;

INSERT INTO STUDENTS VALUES ('SHAkil ahMED', 3.80);
INSERT INTO STUDENTS VALUES ('masum billah', 3.60);

select * from students;

    select count(*) 
    from D_WISHLIST
    WHERE(ROUND=1) AND (ISBN='100005') AND(WISH_DATE<SYSDATE);




create or replace trigger wishlist_auto_round
before insert
on wishlist 
for each row
begin
insert into D_WISHLIST (ROUND) values (1);
end;

drop table D_STOCK;
drop table D_WISHLIST;

CREATE TABLE "D_STOCK" (
  "BOOK_ID" varchar2(20) references BOOK_ENTRY(BOOK_ID),
  "STOCK_ID" number,
  "BOOKED_STATUS" varchar2(20)
);

CREATE TABLE "D_WISHLIST" (
  "USER_ID" varchar2(20) references "USER"(USER_ID),
  "ISBN" varchar2(20) references BOOK_CATALOG(ISBN),
  "WISH_DATE" date,
 "ROUND" integer
);


    SELECT min(ROUND)
    FROM D_WISHLIST
    WHERE(ISBN='100003');
create or replace trigger stock_triggers_notif
before insert
on D_STOCK
for each row
DECLARE
    temp_book_id varchar2(20);
    receiver_id  varchar2(20);
    temp_isbn varchar2(20);
    cnt integer;
    min_round integer;
BEGIN
    temp_book_id:= :new.BOOK_ID;
    SELECT ISBN into temp_isbn FROM BOOK_ENTRY WHERE BOOK_ID=temp_book_id;
    cnt :=0;
    SELECT min(ROUND) into min_round
    FROM D_WISHLIST
    WHERE(ISBN=temp_isbn);
    SELECT count(*) into cnt FROM(
        SELECT USER_ID 
        FROM (SELECT USER_ID FROM D_WISHLIST WHERE (ISBN=temp_isbn) AND (ROUND=min_round))kb join "USER" U using(USER_ID) 
        ORDER BY(CURR_CREDIT) DESC);
    
    IF cnt >0 THEN
    
        SELECT USER_ID into receiver_id FROM(
            SELECT USER_ID 
            FROM (SELECT USER_ID FROM D_WISHLIST WHERE (ISBN=temp_isbn)AND (ROUND=min_round))kb join "USER" U using(USER_ID)
            ORDER BY(CURR_CREDIT) DESC)KP
        WHERE ROWNUM<=1;
    -----------
        --1
        INSERT INTO NOTIFICATION (RECEIVER_ID,BOOK_ID,NOTI_DATE)
        VALUES (receiver_id,temp_book_id,SYSDATE);
        --2
        UPDATE D_WISHLIST SET ROUND=ROUND+1
        WHERE USER_ID=receiver_id;
        --3
        :new.BOOKED_STATUS:='Y';
    END IF;

END;




CREATE OR REPLACE TRIGGER WISHLIST_TRIGGERS_NOTIF
BEFORE INSERT 
ON D_WISHLIST
FOR EACH ROW
DECLARE
    temp_isbn varchar2(20);
    temp_book_id varchar2(20);
    receiver_id varchar2(20);
    wish_dt DATE;
    cnt integer;
    cnt2 integer;
    
BEGIN
    cnt:=0;
    temp_isbn:=:new.ISBN;
    wish_dt:=:new.WISH_DATE;
    receiver_id:=:new.USER_ID;
    select count(*) into cnt
    from D_WISHLIST
    WHERE(ROUND=1) AND (ISBN=temp_isbn) AND(WISH_DATE<wish_dt);
    
    if (cnt=0) THEN
        
        cnt2:=0;
        
        select count(*) into cnt2
        from D_STOCK JOIN BOOK_ENTRY USING(BOOK_ID)
        WHERE (ISBN=temp_isbn);
        
        IF cnt2>0 THEN
            SELECT BOOK_ID into temp_book_id
            FROM(
                select BOOK_ID
                from D_STOCK JOIN BOOK_ENTRY USING(BOOK_ID)
                WHERE (ISBN=temp_isbn))kp
            WHERE ROWNUM<=1;
        --1
            INSERT INTO NOTIFICATION (RECEIVER_ID,BOOK_ID,NOTI_DATE)
            VALUES (receiver_id,temp_book_id,SYSDATE);
        
        --2
            UPDATE D_STOCK SET BOOKED_STATUS='Y'
            WHERE BOOK_ID=temp_book_id;
        END IF;
        
        --3
        :new.ROUND:=2;
        
        
    END IF;
 
    
END;




CREATE OR REPLACE PROCEDURE NOTIF_ACCEPTED (U_ID IN VARCHAR2, BOOK IN VARCHAR2) IS
  -- this_isbn varchar2(20); 
    
    
    
BEGIN
    --1
    INSERT INTO D_DISTRIBUTION (BOOK_ID, RECIPIENT_ID,DUE_DATE)
    VALUES(BOOK,U_ID,SYSDATE);
    --2
    DELETE FROM D_WISHLIST
    WHERE USER_ID=U_ID AND 
    ISBN=(SELECT ISBN FROM BOOK_ENTRY WHERE BOOK_ID=BOOK);
    --3
    DELETE FROM D_STOCK
    WHERE BOOK_ID=BOOK;
    --4
    DELETE FROM NOTIFICATION
    WHERE BOOK_ID=BOOK AND RECEIVER_ID=U_ID;
    

END;


----------------


CREATE OR REPLACE PROCEDURE NOTIF_REFRESH IS
    DATEBET number;
    REC VARCHAR2(20);
    BOOK VARCHAR(200;
BEGIN
    FOR RR IN (SELECT * FROM NOTIFICATION)
    LOOP
        --SELECT sysDATE-RR.NOTI_DATE intO DATEBET FROM DUAL;
        IF DATEBET>14 THEN
            REC:=RR.RECEIVER_ID;
            BOOK:=RR.BOOK_ID;
            DELETE FROM NOTIFICATION
            WHERE (RECEIVER_ID=REC) and (BOOK_ID=BOOK);
            
            --1
            UPDATE D_STOCK SET BOOKED_STATUS='N'
            WHERE BOOK_ID=BOOK;
            
        END IF;
        
    END LOOP;
    
END;



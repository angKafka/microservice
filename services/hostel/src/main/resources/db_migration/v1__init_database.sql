CREATE TABLE ROOM(
                     ROOM_ID RAW(16) DEFAULT SYS_GUID() PRIMARY KEY,
                     ROOM_NUMBER NUMBER NOT NULL PRIMARY KEY,
                     FLOOR NUMBER NOT NULL,
                     IS_EMPTY VARCHAR2(10) NOT NULL,
                     LEFT_BED NUMBER,
                     RIGHT_BED NUMBER
);

CREATE TABLE HOSTEL(
                       HOSTEL_ID RAW(16) DEFAULT SYS_GUID() PRIMARY KEY,
                       HOSTEL_NAME VARCHAR2(8) NOT NULL,
                       ROOM_ID RAW(16),
                       FOREIGN KEY (ROOM_ID) REFERENCES ROOM(ROOM_ID)
);
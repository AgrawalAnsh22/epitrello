CREATE TABLE USERS(USER_NAME VARCHAR(50) PRIMARY KEY);
CREATE TABLE LISTS(LIST_NAME VARCHAR(80) PRIMARY KEY);

CREATE TABLE TASKS( LIST_NAME VARCHAR(80) FOREIGN KEY REFERENCES LISTS(LIST_NAME) ,TASK VARCHAR(100) PRIMARY KEY, EST_TIME INT, PRIORITY INT, DESCRIPTION VARCHAR(200), ASSIGNED_TO VARCHAR(50) FORIEGN KEY REFERENCES USERS(USER_NAME), STATUS VARCHAR(20));

INSERT INTO USERS (USER_NAME) VALUES ("Thomas");
INSERT INTO LISTS (LIST_NAME) VALUES ("CODE");
INSERT INTO TASKS (LIST_NAME ,TASK, EST_TIME, PRIOROITY, DESCRIPTION, STATUS) VALUES ("CODE", "DO EVERYTHING", 12, 1, "USE PROPER INDENTATION", "INCOMPLETE");
INSERT INTO TASKS (ASSIGNED_TO) VALUES ("Thomas")
UPDATE TASKS SET EST_TIME=10 WHERE TASK="DO EVERYTHING";
UPDATE TASKS SET PRIORITY=2 WHERE TASK="DO EVERYTHING";
UPDATE TASKS SET DESCRIPTION="UPDATE ACCORDING TO COMMENTS" WHERE TASK="DO EVERYTHING";

SELECT TASK FROM TASKS;

DELETE FROM TASKS WHERE TASK="DO EVERYTHING";//to delete the task
UPDATE TASKS SET LIST_NAME="LIST 2" WHERE TASK="DO EVERYTHING";//to move the task to another list

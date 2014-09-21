CREATE TABLE Account
( id NUMBER
, username VARCHAR2(60)
, password CHAR(44)
, role VARCHAR2(5)
, birthday DATE
, last_login DATE
, active_since DATE
, CONSTRAINT pk_Account_Id PRIMARY KEY(id)
, CONSTRAINT uq_Account_Username UNIQUE(username)
);

CREATE TABLE Account_Setting
( account_id NUMBER
, date_changed DATE,
, recalculation_day VARCHAR2(9) NOT NULL
, activity_multiplier VARCHAR2(17) NOT NULL,
, tdee_formula CHAR(15) NOT NULL,
, CONSTRAINT pk_Account_Setting_Id PRIMARY KEY(account_id, date_changed)
, CONSTRAINT fk_Account_Id_Setting FOREIGN KEY(account_id) REFERENCES Account(id)
);

CREATE TABLE Account_Weight
( account_id NUMBER
, weigh_in_date DATE
, weight NUMBER(5,2)
, CONSTRAINT pk_Account_Weight PRIMARY KEY(account_id, weigh_in_date)
, CONSTRAINT fk_Account_Id_Weight FOREIGN KEY(account_id) REFERENCES Account(id)
);

CREATE TABLE Ingredient
( name VARCHAR2(40)
, brand VARCHAR2(40)
, category VARCHAR2(12)
, default_serving VARCHAR2(11)
, default_size NUMBER(7,2)
, alternate_serving VARCHAR2(11)
, alternate_size NUMBER(7,2)
, calories NUMBER(10,2)
, fat NUMBER(5,2)
, carbohydrates NUMBER(5,2)
, protein NUMBER(5,2)
, CONSTRAINT pk_Ingredient PRIMARY KEY(name, brand)
);

CREATE SEQUENCE ACCOUNT_ID_SEQ;

SELECT *
FROM Account;
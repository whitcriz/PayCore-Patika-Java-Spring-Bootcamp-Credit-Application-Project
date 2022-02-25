
DROP TABLE IF EXISTS customer;

CREATE TABLE customer
(
    national_identity_number VARCHAR(11) PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL,
    monthly_income DOUBLE PRECISION NOT NULL,
    credit_score INT,
    gender    VARCHAR(10),
    age       INT DEFAULT 0,
    phone     VARCHAR(15) NOT NULL,
    email     VARCHAR(25) UNIQUE
);



-- ////////////////////////////////////////////////////////////////////////////
--  Sample static test values
-- ////////////////////////////////////////////////////////////////////////////


INSERT INTO customer (national_identity_number,name,surname,monthly_income,credit_score,gender,age,phone,email)
VALUES
    ('16419939227','Fleur','Campbell',19327.69,0,'FEMALE',84,'57586137967','campbell7937@outlook.edu'),
    ('68716983955','Orli','Hatfield',18713.78,300,'MALE.',21,'25187466673','hatfield5079@outlook.com'),
    ('91740498566','Finn','Hicks',14635.70,550, 'FEMALE',59,'86242204768','hicks655@protonmail.couk'),
    ('53179906110','Nash','Pate', 35123.07,755,'MALE',20,'40284123316','pate@protonmail.com'),
    ('69726904564','Tana','Hogan', 27671.63 ,1050,'FEMALE,',19,'62847241498','hogan4378@google.com'),
    ('50347966878','Catherine','Christensen',47633.07,1275,'FEMALE',88,'67813439628','christensen940@yahoo.org'),
    ('67166158484','Vera','Joyner',15919.19,255,'FEMALE',19,'14822347386','joyner1629@aol.com'),
    ('30225975999','Kyra','Brown',14500.48,905,'FEMALE',89,'33628125784','brown4671@yahoo.ca'),
    ('80368440585','Xena','Roman',14289.79,763,'FEMALE',46,'20026582551','roman@aol.couk'),
    ('44100219190','Hayes','Guzman',19391.28,1400,'MALE',22,'17325470330','guzman@google.net');

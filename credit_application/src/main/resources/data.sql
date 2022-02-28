
-- ////////////////////////////////////////////////////////////////////////////
--  Sample static test values
-- ////////////////////////////////////////////////////////////////////////////


INSERT INTO customer (national_identity_number,name,surname,monthly_income,gender,age,phone,email)
VALUES
    ('16419939227','Fleur','Campbell',19327.69,'FEMALE',84,'05586137967','campbell7937@outlook.edu'),
    ('68716983955','Orli','Hatfield',18713.78,'MALE.',21,'05187466673','hatfield5079@outlook.com'),
    ('91740498566','Finn','Hicks',14635.70, 'FEMALE',59,'05242204768','hicks655@protonmail.couk'),
    ('53179906110','Nash','Pate', 35123.07,'MALE',20,'05284123316','pate@protonmail.com'),
    ('69726904564','Tana','Hogan', 27671.63,'FEMALE,',19,'05847241498','hogan4378@google.com'),
    ('50347966878','Catherine','Christensen',47633.07,'FEMALE',88,'05813439628','christensen940@yahoo.org'),
    ('67166158484','Vera','Joyner',15919.19,'FEMALE',null,'05822347386','joyner1629@aol.com'),
    ('30225975999','Kyra','Brown',14500.48,'FEMALE',null,'05628125784','brown4671@yahoo.ca'),
    ('80368440585','Xena','Roman',14289.79,'FEMALE',null,'05026582551','roman@aol.couk'),
    ('25791020456','Yawn','Puqet',15653.5, null, 25,'05435152550','puqetyawn@gmail.com'),
    ('44100219190','Hayes','Guzman',19391.28,'MALE',null,'05325470330','guzman@google.net');


INSERT INTO credit_application (credit_application_date,credit_result,credit_limit,application_status,national_identity_number)
VALUES
    ('16-02-2022','REJECTED',0.00,'PASSIVE','16419939227'),
    ('17-02-2022','REJECTED',0.00,'PASSIVE','68716983955'),
    ('26-02-2022','APPROVED',10069.76,'ACTIVE','91740498566'),
    ('27-02-2022','NOT_RESULTED',null,'ACTIVE','67166158484'),
    ('23-02-2022','APPROVED',65871.95,'ACTIVE','30225975999'),
    ('24-02-2022','REJECTED',0.00,'PASSIVE','80368440585'),
    ('26-02-2022','APPROVED',53683.55,'PASSIVE','67166158484'),
    ('22-02-2022','APPROVED',22795.71,'PASSIVE','50347966878'),
    ('13-02-2022','REJECTED',0.00,'PASSIVE','69726904564'),
    ('23-02-2022','APPROVED',30596.89,'PASSIVE','53179906110'),
    ('22-02-2022','APPROVED',36243.60,'PASSIVE','30225975999'),
    ('25-02-2022','APPROVED',41970.50,'ACTIVE','53179906110'),
    ('28-02-2022','NOT_RESULTED',null,'ACTIVE','91740498566'),
    ('18-02-2022','APPROVED',14472.58,'PASSIVE','50347966878'),
    ('23-02-2022','APPROVED',30461.99,'PASSIVE','91740498566'),
    ('27-02-2022','NOT_RESULTED',null,'ACTIVE','25791020456'),
    ('25-02-2022','APPROVED',67620.60,'ACTIVE','68716983955');





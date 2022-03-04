
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


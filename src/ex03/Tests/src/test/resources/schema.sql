DROP TABLE IF EXISTS table_product;

CREATE TABLE table_product(
    identifier int GENERATED ALWAYS AS IDENTITY(START WITH 1) NOT NULL,
    name varchar(20) NOT NULL,
    price double NOT NULL,
    PRIMARY KEY (identifier)
);
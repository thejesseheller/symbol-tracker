BEGIN TRANSACTION;

DROP TABLE IF EXISTS stocks;
DROP SEQUENCE IF EXISTS seq_stock_id;

CREATE SEQUENCE seq_stock_id
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;

CREATE TABLE stocks (
        stock_id int DEFAULT nextval('seq_stock_id'::regclass) NOT NULL,
        symbol varChar(12) not null,
        open varChar(12),
        high varChar(12),
        low varChar(12),
        close varChar(12),
        volume varChar(12),
        CONSTRAINT PK_stock PRIMARY KEY (stock_id)
);

COMMIT TRANSACTION;
BEGIN TRANSACTION;

DROP TABLE IF EXISTS stocks;
DROP SEQUENCE IF EXISTS seq_stock_id;

CREATE SEQUENCE seq_stock_id
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;

CREATE SEQUENCE seq_dd_id
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;

CREATE TABLE stocks (
        stock_id int DEFAULT nextval('seq_stock_id'::regclass) NOT NULL,
        symbol varChar(12) not null,
        CONSTRAINT PK_stock PRIMARY KEY (stock_id)
);

CREATE TABLE daily_data (
        daily_data_id int DEFAULT nextval('seq_dd_id'::regclass) NOT NULL,
        date varChar(12),
        open varChar(12),
        high varChar(12),
        low varChar(12),
        close varChar(12),
        volume varChar(24),
        stock_id int REFERENCES stocks (stock_id),
        CONSTRAINT PK_daily_data PRIMARY KEY (daily_data_id)
);

COMMIT TRANSACTION;
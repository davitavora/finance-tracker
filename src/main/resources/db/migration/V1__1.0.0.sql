CREATE TYPE CATEGORY_TYPE AS ENUM ('EXPENSE', 'INCOMING');

CREATE TABLE category
(
    id   SERIAL        NOT NULL,
    name VARCHAR       NOT NULL,
    type CATEGORY_TYPE NOT NULL,
    CONSTRAINT category_pk PRIMARY KEY (id)
);

CREATE TABLE financial_transaction
(
    id          BIGSERIAL      NOT NULL,
    name        VARCHAR        NOT NULL,
    detail      VARCHAR,
    amount      DECIMAL(10, 2) NOT NULL,
    created_at  DATE DEFAULT NOW(),
    category_id INTEGER,
    CONSTRAINT transaction_pk PRIMARY KEY (id),
    CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES category (id)
        ON DELETE SET NULL
);

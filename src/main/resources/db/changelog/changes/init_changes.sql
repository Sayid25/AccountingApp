--liquibase formatted sql

-- changeset init:1
CREATE SCHEMA IF NOT EXISTS accounting_schema;

-- changeset init:2
CREATE TABLE accounting_schema.user
(
    id         BIGSERIAL PRIMARY KEY,
    username   VARCHAR(255),
    password   VARCHAR(255),
    full_name  VARCHAR(255),
    created_at TIMESTAMP DEFAULT now(),
    updated_at TIMESTAMP DEFAULT now()
);
-- changeset init:3
CREATE TABLE accounting_schema.account
(
    id             BIGSERIAL PRIMARY KEY,
    account_number VARCHAR(255),
    balance        NUMERIC(19, 4),
    status         VARCHAR(50),
    user_id        BIGINT NOT NULL,
    created_at     TIMESTAMP DEFAULT now(),
    updated_at     TIMESTAMP DEFAULT now(),

    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES accounting_schema.user (id)
);
-- changeset init:4
CREATE TABLE accounting_schema.transaction
(
    id                 BIGSERIAL PRIMARY KEY,
    debit_account_id   BIGINT NOT NULL,
    credit_account_id  BIGINT NOT NULL,
    transaction_status VARCHAR(50),
    description        TEXT,
    amount             NUMERIC(19, 4),
    created_at         TIMESTAMP DEFAULT now(),
    updated_at         TIMESTAMP DEFAULT now(),

    CONSTRAINT fk_debit_account FOREIGN KEY (debit_account_id) REFERENCES accounting_schema.account (id),
    CONSTRAINT fk_credit_account FOREIGN KEY (credit_account_id) REFERENCES accounting_schema.account (id)
);

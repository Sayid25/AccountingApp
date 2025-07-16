-- changeset init:5
-- Insert 5 users
INSERT INTO accounting_schema.user (username, password, full_name)
VALUES
    ('alice', '$2a$10$uUmJ0HJ90UQCDoq5IznEO.3Dg6kScnl3uAcVuXKJYl/OlklCmcN9O', 'Alice Johnson'), -- alicepass
    ('bob',   '$2a$10$JvUGX3k4f3PH8ihzH7Z.mOSNKnGMd8C1MYEv9.CWVa71YXK7B2pM.', 'Bob Smith'),     -- bobpass
    ('carol', '$2a$10$dNDEf8VE20trNfKHiJcVZOF17M3VjT0rYJZyD6hPVzfnXWlsb6/Hu', 'Carol Williams'), -- carolpass
    ('dave',  '$2a$10$VVZDCpvlr5EdePF/fUeFMOG/4nEG.VoHcq8mGmN0MglHi2Ewco11C', 'Dave Brown'),     -- davepass
    ('eve',   '$2a$10$qInbwRz8wS6z4wzNE65mwe6Bk0zTISbwy11AIX94SgexfqkTVee9y', 'Eve Davis');     -- evepass

-- changeset init:6
-- Insert 5 accounts
INSERT INTO accounting_schema.account (account_number, balance, status, user_id)
VALUES
    ('10000000000000000001', 5000.00, 'ACTIVE', 1),
    ('10000000000000000002', 8000.00, 'ACTIVE', 2),
    ('10000000000000000003', 12000.00, 'ACTIVE', 3),
    ('10000000000000000004', 15000.00, 'ACTIVE', 4),
    ('10000000000000000005', 20000.00, 'ACTIVE', 5);

-- changeset init:7
INSERT INTO accounting_schema.transaction (debit_account_id, credit_account_id, transaction_status, description, amount)
VALUES
    (1, 2, 'SUCCESS', 'Alice sends money to Bob', 200.00),
    (2, 3, 'SUCCESS', 'Bob pays Carol', 150.00),
    (3, 4, 'SUCCESS', 'Carol transfers to Dave', 300.00),
    (4, 5, 'SUCCESS', 'Dave sends to Eve', 250.00),
    (5, 1, 'SUCCESS', 'Eve pays back Alice', 180.00),
    (1, 3, 'SUCCESS', 'Alice pays Carol', 220.00),
    (2, 4, 'SUCCESS', 'Bob pays Dave', 170.00),
    (3, 5, 'SUCCESS', 'Carol sends to Eve', 200.00),
    (4, 1, 'SUCCESS', 'Dave sends to Alice', 260.00),
    (5, 2, 'SUCCESS', 'Eve pays Bob', 190.00);

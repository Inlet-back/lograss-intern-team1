CREATE TABLE journal_entry_detail

(
    journal_entry_number CHAR(10) NOT NULL,
    account_code         VARCHAR(30) NOT NULL,
    journal_entry_type         VARCHAR(10) NOT NULL,    -- 'debit' or 'credit'
    amount               DECIMAL(15,2) CHECK (amount > 0) NOT NULL,
    
    FOREIGN KEY (journal_entry_number) REFERENCES journal_entry_header(journal_entry_number)
);

INSERT INTO journal_entry_detail (journal_entry_number, account_code, journal_entry_type, amount)
VALUES
('1000000000', '3001', 'debit', 1000),
('1000000000', '1001', 'credit', 1000),
('1000000001', '3001', 'debit', 2000),
('1000000001', '1001', 'credit', 2000),
('1000000002', '3002', 'debit', 2000),
('1000000002', '1011', 'credit', 2000),
('1000000003', '2001', 'debit', 500),
('1000000003', '2003', 'debit',100),
('1000000003', '3001', 'credit', 600),
('1000000004', '3001', 'debit', 2000),
('1000000004', '4001', 'credit', 2000),
('1000000005', '1011', 'debit', 2000),
('1000000005', '3002', 'credit', 2000),
('1000000006', '3002', 'debit', 1500),
('1000000006', '1011', 'credit', 1500),


('1000000007', '1011', 'credit', 1500);

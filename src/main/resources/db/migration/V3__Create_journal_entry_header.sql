CREATE TABLE journal_entry_header

(
    journal_entry_number CHAR(10) PRIMARY KEY,
    journal_entry_date   DATE NOT NULL,
    department_code        VARCHAR(30) NOT NULL
);

INSERT INTO journal_entry_header (journal_entry_number, journal_entry_date, department_code)
VALUES
('1000000000', '2024-01-01', '1001'),
('1000000001', '2024-01-03', '1002'),
('1000000002', '2024-01-19', '1002'),
('1000000003', '2024-01-20', '1001'),
('1000000004', '2024-01-20', '1001'),
('1000000005', '2024-01-21', '1002'),
('1000000006', '2024-01-21', '1002');

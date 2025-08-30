CREATE TABLE departments

(
    department_code        VARCHAR(30) NOT NULL,
    name                VARCHAR(50) NOT NULL,
    parent_department_code VARCHAR(30),
    PRIMARY KEY (department_code)
);


INSERT INTO departments(department_code, name, parent_department_code) VALUES ('1000', '東京営業部', NULL);
INSERT INTO departments(department_code, name, parent_department_code) VALUES ('1001', '東京営業1課', '1000');
INSERT INTO departments(department_code, name, parent_department_code) VALUES ('1002', '東京営業2課', '1000');

INSERT INTO departments(department_code, name, parent_department_code) VALUES ('2000', '大阪営業部', NULL);
INSERT INTO departments(department_code, name, parent_department_code) VALUES ('2001', '大阪営業1課', '2000');
INSERT INTO departments(department_code, name, parent_department_code) VALUES ('2002', '大阪営業2課', '2000');

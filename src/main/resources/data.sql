DROP TABLE IF EXISTS user;
 
CREATE TABLE user (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  account_number VARCHAR(250) DEFAULT NULL
);

INSERT INTO user (first_name, last_name, account_number) VALUES
('John', 'Doe', 'A1238374'),
('Jane', 'Smith', 'A09458584');
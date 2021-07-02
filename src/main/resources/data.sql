DROP TABLE IF EXISTS person;

CREATE TABLE person (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  address VARCHAR(250) DEFAULT NULL,
  cellphone INT DEFAULT NULL,
  city_name VARCHAR(250) DEFAULT NULL
);

DROP TABLE IF EXISTS position;

CREATE TABLE position (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL
);

DROP TABLE IF EXISTS employee;

CREATE TABLE employee (
  id INT AUTO_INCREMENT  PRIMARY KEY,
person_id INT,
position_id INT,
salary INT DEFAULT NULL,
foreign key (person_id) references person(id),
foreign key (position_id) references position(id)
);


Insert into position (id,name) values (1,'dev');

INSERT INTO person (id,first_name, last_name, address,cellphone,city_name) VALUES
  (1,'Aliko', 'Dangote', 'Billionaire Industrialist',123123,'1'),
  (2,'Bill', 'Gates', 'Billionaire Tech Entrepreneur',1231231,'2'),
  (3,'Folrunsho', 'Alakija', 'Billionaire Oil Magnate',123123,'3');

insert into employee (id,person_id,position_id,salary) values (1,1,1,123);
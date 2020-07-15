ws-db
=====

Library:
* Spring boot (https://spring.io)
* Vavr (https://www.vavr.io/vavr-docs)
* Lombok (https://projectlombok.org/features/all)

Setup
---
Create postgresql db 'mydb' user 'mydb' pass: mydb

```
create database mydb;
create user mydb with encrypted password 'mydb';
grant all privileges on database gandal to mydb;
```

create table
```
CREATE TABLE employee (	
	name varchar(36) NOT NULL,
	role varchar(36) NOT null,
	CONSTRAINT name_pkey PRIMARY KEY (name)	
);
```

Test
----

`mvn test -Dspring.profiles.active=it`

Compile
---
`mvn package -DskipTests`

Run
----

`java -Dspring.profiles.active=local -jar target/ws-db-0.0.1-SNAPSHOT.war`

add new Employee:

`curl -v -X POST localhost:8080/employees/v1/3 -H 'Content-Type:application/json' -d '{"name": "Samwise", "role": "ring bearer"}'`

read Employee:

`curl -v -X GET localhost:8080/employees/v1/Samwise`

throw an error:
`curl -v -X POST localhost:8080/employees/v1/3 -H 'Content-Type:application/json' -d '{"name": "", "role": " "}'`

TODO
----
* add `id` int field in Employee table and use it for unique key instead of name
* add `PUT` (update) and `DELETE` in EmployeeController and add unit test

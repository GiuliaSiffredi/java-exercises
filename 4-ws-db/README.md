ws-db
=====

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
	name varchar(25) NOT NULL,
	role varchar(25) NOT null,
	department varchar(15) null,
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

`curl -v -X POST localhost:8080/employee/3 -H 'Content-Type:application/json' -d '{"name": "bob", "role": "developer"}'`

read Employee by name:

`curl -v -X GET localhost:8080/employee/bob`

read all Employees by role:

`curl -v -X GET localhost:8080/employee/role/developer`

throw an error:
`curl -v -X POST localhost:8080/employee/3 -H 'Content-Type:application/json' -d '{"name": "", "role": " "}'`

TODO
----
* add `id` int field in Employee table and use it as unique key instead of name (change create table in BaseTestClass too)
* add `PUT` (update) and `DELETE` in EmployeeController and modify unit test

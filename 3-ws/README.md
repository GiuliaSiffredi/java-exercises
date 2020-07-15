rest web service
=====

Compile
---
`mvn package`

Run
----

`java  -Dspring.profiles.active=local -jar target/ws-0.0.1-SNAPSHOT.jar`

`curl http://localhost:8080/greeting?name=bob`

`curl -v -X POST localhost:8080/employees/3 -H 'Content-Type:application/json' -d '{"name": "Samwise Gamgee", "role": "ring bearer"}'`


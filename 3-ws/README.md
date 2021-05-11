rest web service
=====

Compile
---
`mvn clean package`

Run
----

`java -jar target/ws-0.0.1-SNAPSHOT.jar`

`curl --location --request GET 'http://localhost:8080/greeting?name=bob' --header 'foo: bar'`

`curl -v -X POST localhost:8080/employees/3 -H 'Content-Type:application/json' -d '{"name": "Samwise Gamgee", "role": "ring bearer"}'`


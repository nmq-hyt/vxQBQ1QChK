# vxQBQ1QChK
The coding challenge for Genesys. Candidate name: Conor O'Boyle

I wasn't able to fully complete the coding challenge, in specific because of trying to get the query system working. It's precisely failing due to (what I guess) improper handling of the JSON query string and translating that query to actually calling the database. I forgot completely that you can use a query language, and ended up leanign too much on directly manipulating the storage objects.

REGARDLESS

This is a small spring application.

It requires maven, and java 17. Make sure your $JAVA_PATH= environment variable is set to point to the appropriate file in your home system.

Once downloaded, build using ./mvnw clean spring-boot:run 

The best tool to work with it is curl.

Some example queries.

curl -v localhost:8080/Readings
Get all readings.

curl -v localhost:8080/sensors
Get all sensors.

curl -X POST localhost:8080/Readings -H 'Content-type:application/json' -d '{"timestamp":1700944131000,"readingNumber":5,"dateTimeStamp":"2023-05-25","temperatureMetricCelsius":21,"humiditiyPercentag":35,"windspeedKmPerHour":51,"deviceName":"Jeremiah"}'
Post a new reading to Readings.

APPROACH
The general approach is to follow the classic MVC that both Spring uses and that I remember learning in first year comp sci.

The "view" is really just the series of http calls between the server and your terminal. The model is the internal storage of the objects, persisted to a database using hibernate.

The controllers - QueryHandler and ReadingController - handle the inputs from the user and manipulate the underlying model, although this is mostly in the part of QueryHandler, which deals with handling http requests.

The endpoints are mostly self-explanatory. I made one - /sensors/{id}/latest to explicitly handly the last known reading from a sensor, because sometimes you just want the last reading.

For testing, it was mostly manual but I did write some function tests in order to make sure changes to the entities wouldn't cause errors in their expected operation - like retrieivng the list of sensors and to validate that the app had started sucessfully.


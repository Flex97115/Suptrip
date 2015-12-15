# Suptrip
JEE application.

Retrieve direct flight between Supinfo campuses. Using QPX-express API by Google.

## Search 
![Alt text](https://github.com/Flex97115/Suptrip/blob/master/screen/Capture%20d%E2%80%99%C3%A9cran%202015-12-15%20%C3%A0%2010.08.30.png)

## List of flight
![Alt text](https://github.com/Flex97115/Suptrip/blob/master/screen/Capture%20d%E2%80%99%C3%A9cran%202015-12-15%20%C3%A0%2010.08.50.png)

##Web service

Retrieve flight for all airport in the city

http://localhost:8080/suptrip/rest/trips/city/{Departure city}/{Arrival city}

Retrieve flight for 2 airports

http://localhost:8080/suptrip/rest/trips/airport/{Departure airport}/{Arrival airport}

## API Key
Get your API Key for QPX-express : https://console.developers.google.com

Paste it in /suptrip/src/com/supinfo/suptrip/util/QPXExpress.java

###Requierment
- [Java EE 8](http://www.oracle.com/technetwork/java/javaee/downloads/java-archive-downloads-eesdk-419427.html)
- [Hibernate](http://hibernate.org/)
- [Gson](https://github.com/google/gson)
- [Jstl](https://jstl.java.net/)
- [My SQL connector](https://dev.mysql.com/downloads/connector/)
- [javax.ws](https://docs.oracle.com/javaee/6/api/javax/ws/rs/package-summary.html)



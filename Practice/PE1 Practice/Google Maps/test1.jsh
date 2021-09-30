/open City.java
/open Route.java

City clementi = new City("clementi")
City west_coast = new City("west_coast")
City jurong = new City("jurong")
City holland_village = new City("holland_village")

new Route(clementi, west_coast, 2.4)
new Route(clementi, jurong, 3.2)
new Route(jurong, west_coast, 5.2)
new Route(jurong, clementi, 3.2)
new Route(jurong, holland_village, 6.3)
new Route(clementi, clementi, 0.0)
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Statistics {
    
    private final List<Route> routes;

    public Statistics(List<Route> routes) {
        this.routes =routes;
    }

    public List<String> getUniqueCities() {
        List<String> cityNames = new ArrayList<>();
        for (Route routes : this.routes) {
            String City1 = routes.getCity1().getName();
            String City2 = routes.getCity2().getName();
            if (!cityNames.contains(City1)) {
                cityNames.add(City1);
            } else if(!cityNames.contains(City2)) {
                cityNames.add(City2);
            }
        }
        return cityNames;
    }

    public String getNumberOfCities() {
        int number = this.getUniqueCities().size();
        System.out.println(String.format("Number of cities: %d", number));
        return "";
    }

    public String getNumberOfRoutes() {
        System.out.println(String.format("Number of routes: %d", this.routes.size()));
        return "";
    }

    public String getListOfCities() {
        System.out.println("List of cities:");
        for (String city : this.getUniqueCities()) {
            System.out.println(city);
        }
        return "";
    }

    public String getMaxCityOccurence() {
        int max = 0;
        String maxCityName = "";
        Map<String, Integer> occurence = new HashMap<>();
        for (String cityName : this.getUniqueCities()) {
            // initialise the hashmap
            occurence.put(cityName, 0);
        }
        List<City> cityList = new ArrayList<>();
        for (Route route : this.routes) {
            City city1 = route.getCity1();
            City city2 = route.getCity2();
            cityList.add(city1);
            cityList.add(city2);
        }
        for (City city : cityList) {
            String cityName = city.getName();
            if (occurence.containsKey(cityName)) {
                occurence.put(cityName, occurence.get(cityName) + 1);
            }
        }
        for (int value : occurence.values()) {
            max = Math.max(max, value);
        }
        for (String name : occurence.keySet()) {
            if (occurence.get(name) == max) {
                maxCityName = name;
                break;
            }
        }
        System.out.println(String.format("Most accessible city: %s", maxCityName));
        return "";
    }

    public String getLongestRoute() {
        double distance = 0;
        Route answer = new Route(new City("dummy"), new City("dummy"), 69.0);
        for (Route route : this.routes) {
            distance = Math.max(distance, route.getDistance());
        }

        // now find the route
        for (Route route : this.routes) {
            if (route.getDistance() == distance) {
                answer = route;
            }
        }
        System.out.println(String.format("Longest route: %s",answer.toString()));
        return "";
    }

    @Override
    public String toString() {
        System.out.println("List of routes:");
        for (Route route : this.routes) {
            System.out.println(route.toString());
        }
        return "";
    }
}

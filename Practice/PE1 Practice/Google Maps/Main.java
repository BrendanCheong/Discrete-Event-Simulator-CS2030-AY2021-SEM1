import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Route> routes = new ArrayList<>();
        while (sc.hasNext()) {
            City city1 = new City(sc.next());
            City city2 = new City(sc.next());
            double distance = sc.nextDouble();
            Route route = new Route(city1, city2, distance);
            if (routes.size() == 0) {
                routes.add(route);
                continue;
            } else {
                int check1 = 0;
                int check2 = 0;
                for (Route theRoute : routes) {
                    City firstCity = theRoute.getCity1();
                    City secondCity = theRoute.getCity2();
                    if (city1.getName().equals(firstCity.getName()) || city1.getName().equals(secondCity.getName())) {
                        check1 += 1;
                    }
                }
                for (Route theRoute: routes) {
                    City firstCity = theRoute.getCity1();
                    City secondCity = theRoute.getCity2();
                    if (city2.getName().equals(firstCity.getName()) || city2.getName().equals(secondCity.getName())) {
                        check2 += 1;
                    }
                }
                if (check1 >= 2 && check2 >= 2) {
                    continue;
                }
            }
            if (city1.equals(city2)) {
                continue;
            } else {
                routes.add(route);
            }
        }
        Statistics stats = new Statistics(routes);
        stats.getNumberOfCities();
        stats.getNumberOfRoutes();
        System.out.println("\n");

        stats.getListOfCities();
        System.out.println("\n");
        stats.toString();

        System.out.println("\n");
        stats.getMaxCityOccurence();
        stats.getLongestRoute();
    }
}


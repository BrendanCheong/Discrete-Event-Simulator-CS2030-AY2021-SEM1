import java.util.Scanner;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A BusSg class encapsulate the data related to the bus services and
 * bus stops in Singapore, and supports queries to the data.
 *
 * @author: Brendan Cheong
 * @version: CS2030 AY21/22 Semester 1, Lab 10
 */
class BusSg {

    /**
     * Return the future set of bus services that serve this bus stop as
     * a set.  Query the web server.
     * @return A set of BusService that serve this bus stop.
     */
    public static CompletableFuture<Set<BusService>> getBusServices(BusStop stop) {

        CompletableFuture<Scanner> futureScanner = BusAPI.getBusServicesAt(stop.getStopId())
            .thenApply((x) -> new Scanner(x));

        CompletableFuture<Set<BusService>> busServices = futureScanner.thenApply((scanner) -> {
                return scanner
                    .useDelimiter("\n")
                    .tokens()
                    .skip(1)
                    .flatMap(line -> Stream.<String>of(line.split(",")))
                    .map(id -> new BusService(id))
                    .collect(Collectors.toSet());
            }
        );

        futureScanner
            .thenAccept((x) -> x.close());
        return busServices;
    }


    /**
     * Given a bus stop and a name, find the bus services that serve between
     * the given stop and any bus stop with matching mame.
     * @param  stop The bus stop.  Assume to be not null.
     * @param  searchString The (partial) name of other bus stops, assume not null.
     * @return The (optional) bus routes between the stops.
     */
    public static CompletableFuture<BusRoutes> 
        findBusServicesBetween(BusStop stop, String searchString) {
        try {
            CompletableFuture<Map<BusService,
                CompletableFuture<Set<BusStop>>>> validServices = getBusServices(stop)
                    .thenApply((services) -> {
                        return services
                        .stream()
                        .collect(Collectors.toMap(
                            service -> service, 
                            service -> service.findStopsWith(searchString)));
                    }
                );
            return validServices
                .thenApply((services) -> {
                    return new BusRoutes(stop, searchString, services);
                }
            );
        } catch (CompletionException e) {
            System.err.println("Unable to complete query: " + e);
            return CompletableFuture
                .completedFuture(new BusRoutes(stop, searchString, Map.of()));
        }
    }
}

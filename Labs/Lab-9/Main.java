import java.time.Instant;
import java.time.Duration;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

/**
 * This program finds different ways one can travel by bus (with a bit 
 * of walking) from one bus stop to another.
 *
 * @author: Brendan Cheong
 * @version: CS2030 AY21/22 Semester 1, Lab 10
 */
public class Main {
    /**
     * The program read a sequence of (id, search string) from standard input.
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        Instant start = Instant.now();
        Scanner sc = new Scanner(System.in);
        List<CompletableFuture<String>> answers = new ArrayList<>();
        while (sc.hasNext()) {
            BusStop srcId = new BusStop(sc.next());
            String searchString = sc.next();

            CompletableFuture<String> routes = BusSg
                .findBusServicesBetween(srcId, searchString)
                .thenCompose((route) -> route.description());
            answers.add(routes);
        }
        sc.close();

        CompletableFuture<?>[] joinedAnswers = new CompletableFuture<?>[answers.size()];
        joinedAnswers = answers.toArray(joinedAnswers);

        // after gathering all the async tasks, output all of them only when all are done
        CompletableFuture
            .allOf(joinedAnswers)
            .join();
        
        // execute and print out each completed future
        List<CompletableFuture<?>> iterableFutures = new ArrayList<>(Arrays.asList(joinedAnswers));
        IntStream
            .range(0, iterableFutures.size())
            .forEachOrdered((index) -> {
                CompletableFuture<?> description = iterableFutures.get(index);
                description
                    .thenAccept((x) -> System.out.println(x));
            });

        Instant stop = Instant.now();
        System.out.printf("Took %,dms\n", Duration.between(start, stop).toMillis());
    }
}

import java.util.concurrent.CompletableFuture;

public class CF {

    public static void doSomething(int i) {
        wait(i * 1000);
    }

    public static CompletableFuture<Void> printAsync(int i) {
        return CompletableFuture.runAsync(() -> {
            doSomething(i);
            System.out.println(i);
        });
    }

    public static void wait(int ms) {
        try {
            Thread.sleep(ms);
        }
        catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        printAsync(1).join();
        CompletableFuture.allOf(printAsync(3), printAsync(2))
            .thenRun(() -> printAsync(4));

        doSomething(5);
    }
}

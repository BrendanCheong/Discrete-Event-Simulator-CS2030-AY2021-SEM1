import java.util.Scanner;

public class Main4 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long n = sc.nextLong();
        sc.close();

        Compute<Long> result = sum(n, 0);
        while (result.isRecursive()) {
            result = result.recurse();
        }

        System.out.println(result.getAnswer());
    }

    static Compute<Long> sum(long n, long s) {
        if (n == 0) {
            return new Base<>(() -> s);
        } else {
            return new Recursive<>(() -> sum(n - 1, n + s));
        }
    }
}

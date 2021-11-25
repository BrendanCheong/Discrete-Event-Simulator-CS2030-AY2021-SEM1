import java.util.Scanner;
import java.util.stream.IntStream;

public class Main17 {
    
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int i = sc.nextInt();
            int j = sc.nextInt();
            sc.close();
            int len1 = String.valueOf(i).length();
            int len2 = String.valueOf(j).length();
            IntStream // continue the stream pipeline below
                .rangeClosed(len1, len2)
                .mapToObj((x) -> {
                    int[] first = new int[] { 
                        len1 >= x + 1
                            ? Integer.parseInt(String.valueOf(i).substring(len1 - 1 - x, len1))
                            : i,
                        len2 >= x + 1
                            ? Integer.parseInt(String.valueOf(j).substring(len2 - 1 - x, len2))
                            : j,
                            x
                    };
                    return first;
                }).map(x -> {
                    int l = x[0] / (int) Math.pow(10, x[2]) % 10;
                    int r = x[1] / (int) Math.pow(10, x[2]) % 10;
                    int q = (x[0] + x[1]) / (int) Math.pow(10, x[2]) % 10;
                    int c = (x[0] + x[1]) / (int) Math.pow(10, x[2]);
                    return new String[]{l + " + " + r + ((l + r) % 10 != (q % 10) ? " + (1)" : "") + " = " + q + (c >= 10 ? " carry 1" : ""), String.valueOf(x[2])};
                })
                .filter(x -> !(x[0].equals("0 + 0 = 0") && x[1].equals(String.valueOf(Math.max(len1, String.valueOf(j).toString().length())))))
                .map(x -> x[0])
                .forEach(System.out::println);
    }
}

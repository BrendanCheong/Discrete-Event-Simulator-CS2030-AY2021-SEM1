import java.util.Scanner;
import java.util.HashMap;

class Main {
    static final int NFACES = 6;
    static final int NROWS = 3;
    static final int NCOLS = 3;
    static final HashMap<String, Rubik> DICTIONARY = new HashMap<>();

    static Rubik oneMove(Rubik rubik, String move) {
        Rubik result = rubik.clone();

        // Add all possible combinations into our global HashMap
        // Too lazy to use switch statements LOL
        DICTIONARY.put("F", new RubikFront(result.getGrid()).right());
        DICTIONARY.put("R", new RubikRight(result).right());
        DICTIONARY.put("U", new RubikUp(result).right());
        DICTIONARY.put("L", new RubikLeft(result).right());
        DICTIONARY.put("B", new RubikBack(result).right());
        DICTIONARY.put("D", new RubikDown(result).right());

        DICTIONARY.put("F'", new RubikFront(result.getGrid()).left());
        DICTIONARY.put("R'", new RubikRight(result).left());
        DICTIONARY.put("U'", new RubikUp(result).left());
        DICTIONARY.put("L'", new RubikLeft(result).left());
        DICTIONARY.put("B'", new RubikBack(result).left());
        DICTIONARY.put("D'", new RubikDown(result).left());

        DICTIONARY.put("F2", new RubikFront(result.getGrid()).half());
        DICTIONARY.put("R2", new RubikRight(result).half());
        DICTIONARY.put("U2", new RubikUp(result).half());
        DICTIONARY.put("L2", new RubikLeft(result).half());
        DICTIONARY.put("B2", new RubikBack(result).half());
        DICTIONARY.put("D2", new RubikDown(result).half());

        result = DICTIONARY.get(move);

        return result;
    }


    public static void main(String[] args) {
        int[][][] grid = new int[NFACES][NROWS][NCOLS];

        Scanner sc = new Scanner(System.in);
        for (int k = 0; k < NFACES; k++) {
            for (int i = 0; i < NROWS; i++) {
                for (int j = 0; j < NCOLS; j++) {
                    grid[k][i][j] = sc.nextInt();
                }
            }
        }
        Rubik rubik = new RubikFront(grid);
        Rubik result = rubik.clone();

        while (sc.hasNext()) {
            rubik = oneMove(rubik, sc.next());
        }
        System.out.println(rubik);
    }
}

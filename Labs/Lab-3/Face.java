import java.util.Arrays;

class Face implements Cloneable {

    private final int[][] grid;

    public Face(int[][] grid) {
        this.grid = grid;
    }

    public Face right() {
        final int gridRowLength = this.grid[0].length;
        final int gridColumnLength = this.grid.length;
        final int[][] result = new int[gridRowLength][gridColumnLength];
        for (int i = 0; i < gridColumnLength; i++) {
            for (int j = 0; j < gridRowLength; j++) {
                result[j][gridColumnLength - 1 - i] = this.grid[i][j];
            }
        }
        return new Face(result);
    }

    public Face left() {
        final int gridRowLength = this.grid[0].length;
        final int gridColumnLength = this.grid.length;
        final int[][] result = new int[gridRowLength][gridColumnLength];
        for (int i = 0; i < gridColumnLength; i++) {
            for (int j = 0; j < gridRowLength; j++) {
                result[i][j] = this.grid[j][gridColumnLength - i - 1];
            }
        }
        return new Face(result);
    }

    public Face half() {
        return this.right().right();
    }

    public int[][] getGrid() {
        final int[][] result = new int[this.grid.length][];
        for (int i = 0; i < this.grid.length; i++) {
            result[i] = Arrays.copyOf(this.grid[i], this.grid[i].length);
        }
        return result;
    }

    @Override
    public Face clone() {
        final int[][] result = new int[this.grid.length][];
        for (int i = 0; i < this.grid.length; i++) {
            result[i] = Arrays.copyOf(this.grid[i], this.grid[i].length);
        }
        return new Face(result);
    }

    public int[][] toIntArray() {
        return this.grid;
    }

    @Override
    public String toString() {
        String result = "\n";
        for (int i = 0; i < this.grid.length; i++) {
            for (int j = 0; j < this.grid[0].length; j++) {
                if (j == this.grid[0].length - 1) {
                    result += String.format("%02d\n", this.grid[i][j]);
                } else {
                    result += String.format("%02d", this.grid[i][j]);
                }
            }
        }
        return result;
    }
}

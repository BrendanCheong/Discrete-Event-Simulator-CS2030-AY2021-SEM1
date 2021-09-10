abstract class Rubik implements Cloneable {

    protected static final int TOP = 0;
    protected static final int LEFT = 1;
    protected static final int MIDDLE = 2;
    protected static final int RIGHT = 3;
    protected static final int ROW_AMOUNT = 3;
    protected static final int COLUMN_AMOUNT = 3;
    protected static final int DOWN = 4;
    protected static final int BACK = 5;
    protected static final int SIDES = 6;
    private final int[][][] grid;

    public Rubik(int[][][] grid) {
        this.grid = grid;
    }

    protected abstract Rubik right();

    protected abstract Rubik left();

    protected abstract Rubik half();

    protected abstract Rubik rightView();

    protected abstract Rubik leftView();

    protected abstract Rubik upView();

    protected abstract Rubik backView();

    protected abstract Rubik downView();

    protected abstract Rubik frontView();

    public abstract Rubik clone();


    @Override
    public String toString() {
        String result = "\n";
        final String bufferDots = "......";
        final int gridRows = this.grid[0][0].length;
        final int gridColumns = this.grid[0].length;
        // Start from Top -> Left to Mid to Right -> Down -> Back
        // So 4 nested loops in total

        // Top
        for (int rows = 0; rows < gridRows; rows++) {
            result += bufferDots;
            for (int cols = 0; cols < gridColumns; cols++) {
                if (cols == gridColumns - 1) {
                    result += String.format("%02d", this.grid[0][rows][cols]) +
                        bufferDots + "\n";
                } else {
                    result += String.format("%02d", this.grid[0][rows][cols]);
                }
            }
        }

        // Left -> Middle -> Right
        for (int rows = 0; rows < gridRows; rows++) {
            for (int rubikFace = 1; rubikFace < gridColumns + 1; rubikFace++) {
                for (int cols = 0; cols < gridColumns; cols++) {
                    result += String.format("%02d", this.grid[rubikFace][rows][cols]);
                }
            }
            result += "\n";
        }

        // Down
        for (int rows = 0; rows < gridRows; rows++) {
            result += bufferDots;
            for (int cols = 0; cols < gridColumns; cols++) {
                if (cols == gridColumns - 1) {
                    result += String.format("%02d", this.grid[DOWN][rows][cols]) +
                        bufferDots + "\n";
                } else {
                    result += String.format("%02d", this.grid[DOWN][rows][cols]);
                }
            }
        }

        //Back
        for (int rows = 0; rows < gridRows; rows++) {
            result += bufferDots;
            for (int cols = 0; cols < gridColumns; cols++) {
                if (cols == gridColumns - 1) {
                    result += String.format("%02d", this.grid[BACK][rows][cols]) +
                        bufferDots + "\n";
                } else {
                    result += String.format("%02d", this.grid[BACK][rows][cols]);
                }
            }
        }

        return result;
    }
}

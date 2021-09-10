class RubikFront extends Rubik {

    private final int[][][] gridA;

    public RubikFront(int[][][] grid) {
        super(grid);
        this.gridA = grid;
    }

    @Override
    public RubikFront right() {
        final int gridRowX = ROW_AMOUNT;
        final int gridColumnY = COLUMN_AMOUNT;
        int[][][] result = new int[SIDES][gridColumnY][gridRowX];
        for (int i = 0; i < SIDES; ++i) {
            for (int j = 0; j < gridColumnY; ++j) {
                result[i][j] = this.gridA[i][j].clone();
            }
        }

        result[MIDDLE] = new Face(result[MIDDLE]).right().getGrid();
        for (int i = 0; i < gridColumnY; i++) {
            int holder1 = this.gridA[0][2][i];
            int holder2 = this.gridA[RIGHT][i][0];
            int holder3 = this.gridA[DOWN][0][2 - i];
            int holder4 = this.gridA[1][2 - i][2];

            result[RIGHT][i][0] = holder1;
            result[DOWN][0][2 - i] = holder2;
            result[1][2 - i][2] = holder3;
            result[0][2][i] = holder4;
        }

        return new RubikFront(result);
    }

    @Override
    public RubikFront left() {
        return this.right().right().right();
    }

    @Override
    public RubikFront half() {
        return this.right().right();
    }

    @Override
    public RubikFront rightView() {
        final int gridRowX = ROW_AMOUNT;
        final int gridColumnY = COLUMN_AMOUNT;
        final int gridDimensionZ = SIDES;
        final int[][][] result = new int[gridDimensionZ][gridColumnY][gridRowX];
        result[TOP] = new Face(this.gridA[TOP]).right().getGrid();
        result[LEFT] = new Face(this.gridA[MIDDLE]).getGrid();
        result[MIDDLE] = new Face(this.gridA[RIGHT]).getGrid();
        result[RIGHT] = new Face(this.gridA[BACK]).right().right().getGrid();
        result[DOWN] = new Face(this.gridA[DOWN]).left().getGrid();
        result[BACK] = new Face(this.gridA[LEFT]).half().getGrid();

        return new RubikFront(result);
    }

    @Override
    public RubikFront upView() {
        final int gridRowX = ROW_AMOUNT;
        final int gridColumnY = COLUMN_AMOUNT;
        final int gridDimensionZ = SIDES;
        final int[][][] result = new int[gridDimensionZ][gridColumnY][gridRowX];
        result[TOP] = new Face(this.gridA[BACK]).getGrid();
        result[LEFT] = new Face(this.gridA[LEFT]).right().getGrid();
        result[MIDDLE] = new Face(this.gridA[TOP]).getGrid();
        result[RIGHT] = new Face(this.gridA[RIGHT]).left().getGrid();
        result[DOWN] = new Face(this.gridA[MIDDLE]).getGrid();
        result[BACK] = new Face(this.gridA[DOWN]).getGrid();

        return new RubikFront(result);
    }

    @Override
    public RubikFront leftView() {
        return this.rightView().rightView().rightView();
    }

    @Override
    public RubikFront downView() {
        return this.upView().upView().upView();
    }

    @Override
    public RubikFront backView() {
        return this.leftView().leftView();
    }

    @Override
    public RubikFront frontView() {
        return this.clone();
    }

    @Override
    public RubikFront clone() {
        final int gridRowX = ROW_AMOUNT;
        final int gridColumnY = COLUMN_AMOUNT;
        final int gridDimensionZ = SIDES;
        final int[][][] result = new int[gridDimensionZ][gridColumnY][gridRowX];
        for (int k = 0; k < gridDimensionZ; k++) {
            for (int j = 0; j < gridColumnY; j++) {
                for (int i = 0; i < gridRowX; i++) {
                    result[k][i][j] = this.gridA[k][i][j];
                }
            }
        }
        return new RubikFront(result);
    }
}

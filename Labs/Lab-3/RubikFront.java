class RubikFront extends Rubik {

    public RubikFront(int[][][] grid) {
        super(grid);
    }

    @Override
    public RubikFront right() {
        final int gridRowX = ROW_AMOUNT;
        final int gridColumnY = COLUMN_AMOUNT;
        int[][][] result = new int[SIDES][gridColumnY][gridRowX];
        for (int i = 0; i < SIDES; ++i) {
            for (int j = 0; j < gridColumnY; ++j) {
                result[i][j] = super.getGrid()[i][j].clone();
            }
        }

        result[MIDDLE] = new Face(result[MIDDLE]).right().getGrid();
        for (int i = 0; i < gridColumnY; i++) {
            int holder1 = super.getGrid()[0][2][i];
            int holder2 = super.getGrid()[RIGHT][i][0];
            int holder3 = super.getGrid()[DOWN][0][2 - i];
            int holder4 = super.getGrid()[1][2 - i][2];

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
        result[TOP] = new Face(super.getGrid()[TOP]).right().getGrid();
        result[LEFT] = new Face(super.getGrid()[MIDDLE]).getGrid();
        result[MIDDLE] = new Face(super.getGrid()[RIGHT]).getGrid();
        result[RIGHT] = new Face(super.getGrid()[BACK]).right().right().getGrid();
        result[DOWN] = new Face(super.getGrid()[DOWN]).left().getGrid();
        result[BACK] = new Face(super.getGrid()[LEFT]).half().getGrid();

        return new RubikFront(result);
    }

    @Override
    public RubikFront upView() {
        final int gridRowX = ROW_AMOUNT;
        final int gridColumnY = COLUMN_AMOUNT;
        final int gridDimensionZ = SIDES;
        final int[][][] result = new int[gridDimensionZ][gridColumnY][gridRowX];
        result[TOP] = new Face(super.getGrid()[BACK]).getGrid();
        result[LEFT] = new Face(super.getGrid()[LEFT]).right().getGrid();
        result[MIDDLE] = new Face(super.getGrid()[TOP]).getGrid();
        result[RIGHT] = new Face(super.getGrid()[RIGHT]).left().getGrid();
        result[DOWN] = new Face(super.getGrid()[MIDDLE]).getGrid();
        result[BACK] = new Face(super.getGrid()[DOWN]).getGrid();

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
                    result[k][i][j] = super.getGrid()[k][i][j];
                }
            }
        }
        return new RubikFront(result);
    }
}

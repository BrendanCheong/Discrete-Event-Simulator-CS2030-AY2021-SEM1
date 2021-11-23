class MinMax {

    private final int min, max;

    public MinMax(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int getMin() {
        return this.min;
    }

    public int getMax() {
        return this.max;
    }

    @Override
    public String toString() {
        return min + ", " + max;
    }
}

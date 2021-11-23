public long count() {
    return this.
        .map((x) -> 1L)
        .reduce(0L, (x, y) -> x + y);
}
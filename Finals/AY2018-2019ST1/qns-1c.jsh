public static Optional<Double> variance(int[] data) {

    if (data.length == 0) {
        return Optional.empty();
    }
    long sum = Arrays.stream(data)
        .sum();
    double average = sum / (double) data.length;
    double topPart = Arrays.stream(data)
        .mapToObj((x) -> Math.pow(x - average, 2))
        .reduce(0.0, (x,y) -> x + y);
    return Optional.<Double>of(topPart / (double) data.length - 1)
        .filter(x -> !x.isNaN());
}

public static <T> long myCount(Stream<T> stream) {
    return stream.
        mapToInt(x -> 1).
        reduce(0, (x,y) -> x + y);
}

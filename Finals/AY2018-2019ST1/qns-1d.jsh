public static String reverse(String str) {
    return str
        .chars()
        .parallel()
        .mapToObj((x) -> "" + (char) x)
        .reduce("", (x, y) -> y + x);
}

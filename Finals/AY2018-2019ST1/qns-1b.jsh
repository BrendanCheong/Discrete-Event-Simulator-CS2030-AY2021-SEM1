public static long countRepeats(String str) {
    ArrayList<Character> list = str.
        chars().
        mapToObj((x) -> (char) x)
        .collect(Collectors.toCollection(() -> new ArrayList<>()));

    return IntStream.rangeClosed(1, list.size() - 2)
        .filter(x -> (list.get(x) == list.get(x + 1) && list.get(x) != list.get(x - 1)
            || x == 1 && list.get(x) == list.get(x - 1)))
        .count();
}

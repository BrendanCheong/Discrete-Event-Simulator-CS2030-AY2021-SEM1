int count(List<String> words) {
    return words.stream()
    .reduce("", (x, y) -> x + y)
    .length();
}
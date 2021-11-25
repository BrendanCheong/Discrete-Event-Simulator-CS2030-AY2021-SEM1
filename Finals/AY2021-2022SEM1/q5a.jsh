Pair<Optional<T>,Stack<T>> pop() {
    Optional<T> head = this.isEmpty()
        ? Optional.empty()
        : Optional.ofNullable(this.list.remove(0));
    Stack<T> newStack = new Stack<T>(this.list);
    return new Pair<>(head, newStack);
}

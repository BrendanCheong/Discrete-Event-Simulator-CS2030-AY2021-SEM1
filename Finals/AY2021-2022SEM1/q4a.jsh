public void forEach(Consumer<? super T> action, int count) {
    for (int i = 0; i < count ; ++i) {
        action.accept(this.get());
    }
}
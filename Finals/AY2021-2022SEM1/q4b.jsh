public <R> MyStream<R> map(Function<? super T, ? extends R> mapper) {
    Supplier<R> seed = () -> mapper.apply(this.get());
    return MyStream.generate(seed);
}
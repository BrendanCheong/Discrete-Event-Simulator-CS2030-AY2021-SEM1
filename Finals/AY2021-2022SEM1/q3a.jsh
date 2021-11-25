convert(int[] array , Function<? super T, ? extends R> fn){
    List<Integer> list = Arrays
        .stream(array)
        .boxed()
        .map(fn)
        .collect(Collectors.toList());
    return list.toArray();
}

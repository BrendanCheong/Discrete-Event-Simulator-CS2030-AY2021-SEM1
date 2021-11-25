convert(int[] array , FunctionalInterface<Integer> fn){
    List<Integer> list = Arrays
        .stream(array)
        .boxed()
        .collect(Collectors.toList());
    return list.stream().map((x) -> fn.apply(x)).toArray();
}

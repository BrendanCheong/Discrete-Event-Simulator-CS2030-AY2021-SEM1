void replace(List<T> src, List<T> dst, BiPredicate<? super T, ? super T> pred) {
    if (src.size() == dst.size()) {
        for (int i = 0; i < src.size(); i++) {
            if (pred.test(src.get(i), dst.get(i))) {
                dst.set(i, src.get(i));
            }
        }
    }
}
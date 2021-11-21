void replace(List<? extends T> src, List<? super T> dst) {
    if (src.size() == dst.size()) {
        for (int i = 0; i < src.size(); i++) {
            if (src.get(i).compareTo(dst.get(i)) > 0) {
                dst.set(i, src.get(i));
            }
        }
    }
}
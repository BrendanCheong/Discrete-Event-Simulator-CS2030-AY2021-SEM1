ImList<T> update(int index, T elem) {
    return new ImList<T>(this.remove(index).add(elem).list);
}
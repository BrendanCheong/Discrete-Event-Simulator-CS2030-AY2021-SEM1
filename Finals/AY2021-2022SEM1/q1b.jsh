ImList<T> add(T elem) {
    return this.set(0, elem);
}

ImList<T> set(int index, T elem) {
    ImList<T> newList = new ImList<T>(this.list);
    newList.remove(index).update(index - 1, elem);
    return newList;
}

private List<T> getList() {
    return new ImList<T>(this.list).list;
}

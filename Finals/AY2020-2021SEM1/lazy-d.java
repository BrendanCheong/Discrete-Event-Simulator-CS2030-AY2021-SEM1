<T> LazyList<LazyList<T>> choose(LazyList<T> LL, int r) {
    if (r == 0)
        return LLmake(LazyList.makeEmpty(), LazyList.makeEmpty());
    else if (LL.isEmpty())
        return LazyList.makeEmpty();
    else {
        T head = LL.head();
        LazyList<T> rm = remove(LL, head);
        return choose(rm, r - 1)
                .map(y -> LLmake(head, y))
                .concat(choose(rm, r));
    }
}
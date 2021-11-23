<T> LazyList<LazyList<T>> choose(LazyList<T> LL, int r) {
    if (r == 0)
        return LLmake(LazyList.makeEmpty(), LazyList.makeEmpty());
    else if (LL.isEmpty())
        return LazyList.makeEmpty();
    else
        //insert your code here
        LazyList<T> tail = LLmake.flatmap(x ->
            choose(remove(LL, x), r - 1)
            .map(y -> LLmake(x, y)));
        return LLmake(this.head(), tail.concat(this.head()));
}
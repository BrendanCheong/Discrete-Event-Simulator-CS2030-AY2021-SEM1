public Logger<Integer> add(Logger<Integer> a, int b) {
    return a.map((x) -> x + b);
}

public Logger<Integer> sum(int n) {
    if (n == 0) {
        return Logger.<Integer>of(0);
    } else {
        return add(sum(n - 1), n);
    }
}

public Logger<Integer> f(int n) {
    if (n == 1) {
        return Logger.<Integer>of(n);
    } else if (n % 2 == 0) {
        return Logger.<Integer>of(n).map((x) -> x / 2).flatMap((y) -> f((y)));
    } else {
        return add(Logger.<Integer>of(n).map((x) -> x * 3), 1).flatMap((y) -> f(y));
    }
}
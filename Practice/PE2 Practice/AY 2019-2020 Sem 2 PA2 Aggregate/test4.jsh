
/open Circle.java
/open Pair.java
/open Aggregate.java
/open Count.java

Aggregate.<Integer,String>seed(0).
    map(s -> Pair.of(s + 1, "one"))
//$.. ==> (1, one)

Aggregate.<Integer,String>seed(0).
    map(s -> Pair.of(s + 1, "one")).
    flatMap(t -> Aggregate.<Integer,String>of(
        s -> Pair.<Integer,String>of(s + 2, t + " two")))
//$.. ==> (3, one two)

Aggregate.<Integer,String>of(
        s -> Pair.<Integer,String>of(s + 2, "two"))
//$.. ==> Aggregate

Aggregate.<Integer,String>seed(11).
    flatMap(t -> Aggregate.<Integer,String>of(
        s -> Pair.<Integer,String>of(s + 2, "two")))
//$.. ==> Invalid Aggregate

Aggregate.<Integer,String>seed(11).
    flatMap(t -> Aggregate.<Integer,String>of(
        s -> Pair.<Integer,String>of(s + 2, "two"))).
    map(s -> Pair.of(s + 1, "one"))
//$.. ==> Invalid Aggregate

Aggregate.<Integer,String>of(
        s -> Pair.<Integer,String>of(s + 2, "two")).
    map(s -> Pair.of(s + 1, "one"))
//$.. ==> Invalid Aggregate

Aggregate.<Integer,String>of(
        s -> Pair.<Integer,String>of(s + 2, "two")).
    map(s -> Pair.of(s + 1, "one")).
    map(s -> Pair.of(s + 3, "three"))
//$.. ==> Invalid Aggregate

Aggregate.<Integer,String>of(
        s -> Pair.<Integer,String>of(s + 2, "two")).
    map(s -> Pair.of(s + 1, "one")).
    flatMap(t -> Aggregate.<Integer,String>of(
        s -> Pair.<Integer,String>of(s + 2, t + " two")))
//$.. ==> Invalid Aggregate

Aggregate.<Integer,String>seed(0).
    map(s -> Pair.of(s + 1, "one"))
//$.. ==> (1, one)

Aggregate.<Integer,String>seed(0).
    map(s -> Pair.of(s + 1, "one")).
    flatMap(t -> Aggregate.<Integer,String>of(
        s -> Pair.<Integer,String>of(s + 2, t + " two")))
//$.. ==> (3, one two)

Aggregate.<Integer,String>seed(0).
    map(s -> Pair.<Integer,String>of(s + 1, "one")).
    flatMap(t -> Aggregate.<Integer,String>of(
        s -> Pair.<Integer,String>of(s + 2, "two")))
//$.. ==> (3, two)

Aggregate.<Integer,String>seed(0).
    map(s -> Pair.<Integer,String>of(s + 1, "one")).
    flatMap(t -> Aggregate.<Integer,String>of(
        s -> Pair.<Integer,String>of(s + 2, t + "two")))
//$.. ==> (3, onetwo)

Function<Integer, Pair<Integer,String>> doit(String s) {
        return x -> Pair.<Integer,String>of(x + s.length(), s);
    }

Function<String, Aggregate<Integer,String>> doit2(String s) {
        return x -> Aggregate.<Integer,String>of(y -> {
            Pair<Integer,String> pair = doit(s).apply(y);
            return Pair.<Integer,String>of(
                pair.first(), x + " " + pair.second());
        });
    }

Aggregate.<Integer,String>seed(0).
    map(doit("one"));
//$.. ==> (3, one)

Aggregate.<Integer,String>seed(0).
    map(doit("one")).
    flatMap(doit2("two")).
    map(doit("three"))
//$.. ==> (11, three)

Aggregate.<Integer,String>seed(0).
    map(doit("one")).
    flatMap(doit2("two")).
    flatMap(doit2("three"))
//$.. ==> (11, one two three)

Aggregate.<Integer,String>seed(10).
    map(s -> Pair.of(s + 1, "one")).
    flatMap(t -> Aggregate.<Integer,Integer>of(
        s -> Pair.<Integer,Integer>of(
            s + 2, t.length() + 5))).
    flatMap(t -> Aggregate.<Integer,Circle>of(
        s -> Pair.<Integer,Circle>of(
            s + 3, new Circle(t))))
//$.. ==> (16, Circle of radius 8.0)
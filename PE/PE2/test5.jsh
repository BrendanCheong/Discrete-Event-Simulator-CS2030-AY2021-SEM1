/open Rand.java
/open Point.java
/open Circle.java
/open Main.java

Stream.iterate(10, x -> x * 10).
    limit(6).
    map(x -> Main.simulate(2030, x)).
    forEach(x -> System.out.println(x))
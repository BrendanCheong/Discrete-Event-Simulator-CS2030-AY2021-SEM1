/open Circle.java
/open Pair.java
/open Aggregate.java
/open Count.java

Count<Circle> countCircle1 = Count.<Circle>of(new Circle(1.0))
countCircle1.
    map(new Circle(2.0))
Count<String> one = Count.<String>of("one")

one.map("two").map("three")
/open Circle.java
/open Pair.java
/open Aggregate.java
/open Count.java

Aggregate.<Integer,Circle>seed(0)
//(0)

Aggregate.<Integer,Circle>seed(10)
//(10)

Aggregate.<Integer,Circle>seed(1).
    map(x -> x * 2, new Circle(2.0))
//(2, Circle of radius 2.0)

Count<String> count = Count.<String>of("one").
    map("two").map("three")
//count ==> (3, three)

Aggregate<Integer,String> agg = count.map("four")
//agg ==> (4, four)

count
//count ==> (3, three)

Aggregate.<Integer,Circle>seed(0).
    map(x -> x + 1, new Circle(1.0)).
    map(x -> x + 2, new Circle(2.0))
//(3, Circle of radius 2.0)

Aggregate.<Double,Circle>seed(0.0).
    map(x -> x + 1.0, new Circle(1.0)).
    map(x -> x + 2.0, new Circle(2.0))
//(3.0, Circle of radius 2.0)


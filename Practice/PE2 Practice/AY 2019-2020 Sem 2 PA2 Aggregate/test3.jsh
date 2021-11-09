/open Circle.java
/open Pair.java
/open Aggregate.java
/open Count.java

Pair.<Integer,String>of(1, "one")
//(1, one)

Aggregate.<Double,Circle>seed(0.0).
    map(x -> x + 1.0, new Circle(1.0)).
    map(x -> Pair.<Double,Circle>of(x + 2.0, new Circle(2.0)))
//(3.0, Circle of radius 2.0)

Aggregate.<Double,Circle>seed(0.0).
    map(x -> x + 1.0, new Circle(1.0)).
    map(x -> {
        Circle c = new Circle(2.0); 
        return Pair.<Double,Circle>of(x + c.getRadius(), c); 
    })
//(3.0, Circle of radius 2.0)

Count<String> count = Count.<String>of("one").
    map("two").map("three")
//count ==> (3, three)

Aggregate<Integer,String> agg = count.map("four").
    map(x -> Pair.<Integer,String>of(x + 1, "five"))
//agg ==> (5, five)

count
//count ==> (3, three)
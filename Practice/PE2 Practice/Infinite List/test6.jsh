/open Lazy.java
/open InfiniteList.java
/open InfiniteListImpl.java
/open EmptyList.java

UnaryOperator<Integer> op = x -> { System.out.printf("iterate: %d -> %d\n", x, x + 1); return x + 1; };
System.out.println("###### Test Case 1 ######");
Supplier<Integer> generator = () -> { System.out.println("generate: 1"); return 1; };
Function<Integer,Integer> doubler = y -> { System.out.printf("map: %d -> %d\n", y, y * 2); return y * 2; };
Predicate<Integer> lessThan100 = z -> { System.out.printf("takeWhile: %d -> %b\n", z, z < 2); return z < 2;};
Predicate<Integer> moreThan10 = k -> { System.out.printf("filter: %d -> %b\n", k, k > 10); return k > 10; };
System.out.println(InfiniteList.iterate(0, op).takeWhile(lessThan100).count());

System.out.println("###### Test Case 2 ######");
lessThan100 = z -> { System.out.printf("takeWhile: %d -> %b\n", z, z < 1); return z < 1;};
System.out.println(InfiniteList.iterate(0, op).takeWhile(lessThan100).count());

 System.out.println("###### Test Case 3 ######");
lessThan100 = z -> { System.out.printf("takeWhile: %d -> %b\n", z, z < 0); return z < 0;};
System.out.println(InfiniteList.iterate(0, op).takeWhile(lessThan100).count());

System.out.println("###### Test Case 4 ######");
generator = () -> { System.out.println("generate: 251"); return 251; };
doubler = y -> { System.out.printf("map: %d -> %d\n", y, y ); return y; };
lessThan100 = z -> { System.out.printf("takeWhile: %d -> %b\n", z, z < 251); return z < 251;};
InfiniteList.generate(generator).map(doubler).takeWhile(lessThan100).takeWhile(lessThan100).toArray();

System.out.println("###### Test Case 5 ######");
System.out.println(InfiniteList.iterate(0, op).filter(moreThan10).limit(4).count());  //should be 4

System.out.println("###### Test Case 5b ######"); //not hidden, level 3 last test case
Predicate<Integer> isEven = x -> { System.out.printf("filter: %d -> %b\n", x, x % 2 == 0); return x % 2 == 0; };
Predicate<Integer> lessThan10 = x -> { System.out.printf("filter: %d -> %b\n", x, x < 10); return x < 10; };
InfiniteList.iterate(1, op).filter(isEven).filter(lessThan10).peek().peek();

System.out.println("###### Test Case 6 ######");
InfiniteList.iterate(10, op).filter(moreThan10).limit(4).toArray(); //should be [11, 12, 13, 14]

System.out.println("###### Test Case 7 ######");
moreThan10 = k -> { System.out.printf("filter: %d -> %b\n", k, k % 10 == 1); return k % 10 == 1; };
InfiniteList.iterate(0, op).filter(moreThan10).limit(4).toArray();   //should be [1,11,21,31]

/exit
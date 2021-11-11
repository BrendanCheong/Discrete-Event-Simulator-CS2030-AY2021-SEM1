/open Lazy.java
/open InfiniteList.java
/open InfiniteListImpl.java

InfiniteList<Integer> list, list2
list = InfiniteList.generate(() -> 1).map(x -> x * 2)
list2 = list.peek()
2
list2 = list.peek()
2
InfiniteList.generate(() -> 1).map(x -> x * 2) instanceof InfiniteListImpl
// $.. ==> true
list = InfiniteList.generate(() -> 1).map(x -> x * 2).peek()
2
list = InfiniteList.generate(() -> 1).map(x -> x * 2).peek().peek()
2
2
list = InfiniteList.iterate(1, x -> x + 1).map(x -> x * 2).peek().peek()
2
4

Supplier<Integer> generator = () -> { System.out.println("generate: 1"); return 1; }
Function<Integer,Integer> doubler = x -> { System.out.printf("map: %d -> %d\n", x, x * 2); return x * 2; };
Function<Integer,Integer> oneLess = x -> { System.out.printf("map: %d -> %d\n", x, x - 1); return x - 1; };
list = InfiniteList.generate(generator).map(doubler).peek().peek()
// generate: 1
// map: 1 -> 2
// 2
// generate: 1
// map: 1 -> 2
// 2
list = InfiniteList.generate(generator).map(doubler).map(oneLess).peek().peek()
// generate: 1
// map: 1 -> 2
// map: 2 -> 1
// 1
// generate: 1
// map: 1 -> 2
// map: 2 -> 1
// 1

list = InfiniteList.iterate(1, x -> x + 1).filter(x -> x % 2 == 0)
list2 = list.peek()
list2 = list.peek()
InfiniteList.iterate(1, x -> x + 1).filter(x -> x % 2 == 0) instanceof InfiniteListImpl
// $.. ==> true
list = InfiniteList.iterate(1, x -> x + 1).filter(x -> x % 2 == 0).peek().peek()
2
list = InfiniteList.iterate(1, x -> x + 1).filter(x -> x % 2 == 0).filter(x -> x < 4).peek().peek().peek().peek()
2

Predicate<Integer> isEven = x -> { System.out.printf("filter: %d -> %b\n", x, x % 2 == 0); return x % 2 == 0; }
Predicate<Integer> lessThan10 = x -> { System.out.printf("filter: %d -> %b\n", x, x < 10); return x < 10; }
UnaryOperator<Integer> op = x -> { System.out.printf("iterate: %d -> %d\n", x, x + 1); return x + 1; };
list = InfiniteList.iterate(1, op).filter(isEven).peek().peek()
// filter: 1 -> false
// iterate: 1 -> 2
// filter: 2 -> true
// 2
// iterate: 2 -> 3
list = InfiniteList.iterate(1, op).filter(isEven).filter(lessThan10).peek().peek()
// filter: 1 -> false
// iterate: 1 -> 2
// filter: 2 -> true
// filter: 2 -> true
// 2
// iterate: 2 -> 3

list = InfiniteList.iterate(1, op).map(doubler).filter(isEven).filter(lessThan10).peek().peek()
// map: 1 -> 2
// filter: 2 -> true
// filter: 2 -> true
// 2
// iterate: 1 -> 2
// map: 2 -> 4
// filter: 4 -> true
// filter: 4 -> true
// 4
// iterate: 2 -> 3
list = InfiniteList.iterate(1, op).filter(isEven).map(doubler).filter(lessThan10).peek().peek()
// filter: 1 -> false
// iterate: 1 -> 2
// filter: 2 -> true
// map: 2 -> 4
// filter: 4 -> true
// 4
// iterate: 2 -> 3
list = InfiniteList.iterate(1, op).filter(isEven).filter(lessThan10).map(doubler).peek().peek()
// filter: 1 -> false
// iterate: 1 -> 2
// filter: 2 -> true
// filter: 2 -> true
// map: 2 -> 4
// 4
// iterate: 2 -> 3
/exit
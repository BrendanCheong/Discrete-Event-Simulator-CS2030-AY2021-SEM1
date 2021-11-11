/open Lazy.java
/open InfiniteList.java
/open InfiniteListImpl.java

InfiniteList<Integer> list
InfiniteList.generate(() -> 1) instanceof InfiniteListImpl
// $.. ==> true
InfiniteList.iterate(1, x -> x + 1) instanceof InfiniteListImpl
// $.. ==> true
list = InfiniteListImpl.generate(() -> 1).peek()
1
list = InfiniteListImpl.iterate(1, x -> x + 1).peek()
1
list = InfiniteListImpl.iterate(1, x -> x + 1).peek().peek()
1
2
InfiniteList<Integer> list2 = list.peek()
3
list != list2
// $.. ==> true
InfiniteList<String> list = InfiniteListImpl.iterate("A", x -> x + "Z").peek().peek().peek()
/**
 * A
 * AZ
 * AZZ
 */
UnaryOperator<Integer> op = x -> { System.out.printf("iterate: %d -> %d\n", x, x + 1); return x + 1; };
list2 = InfiniteList.iterate(1, op).peek().peek()
/**
 * 1
 * iterate: 1 -> 2
 * 2
 * iterate: 2 -> 3
*/
/exit
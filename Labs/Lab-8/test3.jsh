/open Pair.java
/open Logger.java

Logger<Integer> five = Logger.<Integer>of(5)
five.flatMap(x -> Logger.of(x + 1))
five.map(x -> x + 2).map(x -> x * 10)

five.flatMap(x -> Logger.of(x).map(y -> y + 2)).
    flatMap(y -> Logger.of(y).map(z -> z * 10))

Logger.<Integer>of(5).
flatMap(x -> Logger.of(x).
    map(y -> y + 2).
    flatMap(y -> Logger.of(y).map(z -> z * 10)))

Function<Object, Logger<Integer>> f = x -> Logger.<Object>of(x).map(y -> y.hashCode())
Logger.of("hello").flatMap(f)

Function<String, Logger<Integer>> g = x -> Logger.<String>of(x).map(y -> y.length())
Logger<Number> lognum = Logger.<String>of("hello").flatMap(g)

// new test cases
five.flatMap(x -> Logger.of(x).map(y -> y + 2)).
    flatMap(y -> Logger.of(y).map(z -> z))

Logger.<Integer>of(5).
flatMap(x -> Logger.of(x).
    map(y -> y + 2).
    flatMap(y -> Logger.of(y).map(z -> z)))

List<String> list0 = List.<String>of("Starting Value", "5", "7")
List<String> list1 = List.<String>of("Starting Value")
List<String> list2 = List.<String>of("Starting Value", "7", "70")
List<String> list3 = List.<String>of("Starting Value", "2", "1")
List<String> list4 = List.<String>of("Starting Value", "4", "2")
List<String> list5 = List.<String>of("Starting Value", "8", "4")
List<String> list6 = List.<String>of("Starting Value", "4", "2", "1")
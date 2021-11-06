MySet<Integer> mySet = MySetImpl.<Integer>of(1,2,3,4,5,6);
mySet;
mySet.duplicate(3).size() == 18;
mySet.filter(x -> x % 2 == 0);
mySet;
mySet.reduce(0, (subtotal, element) -> subtotal + element)
mySet.map(number -> number * 3)
mySet.map(number -> number + 2)
mySet.sort((x, y) -> y - x)
MySet<String> otherSet = MySetImpl.<String>of("a", "b", "c", "d", "e");
otherSet.duplicate(2).size() == 10;
otherSet.filter(x -> x == "a");
otherSet.filter(x -> true);
otherSet.reduce("", (partialString, element) -> partialString + element)
otherSet.map(letter -> letter + letter)
otherSet.map(String::toUpperCase)
otherSet.sort((x, y) -> y.compareTo(x))
otherSet;
/exit
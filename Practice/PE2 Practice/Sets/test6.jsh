/open MySetImpl.java
/open MySet.java
/open DuplicateNotAllowedException.java

MySet<Integer> mySet = MySetImpl.<Integer>of(1,2,3,4,5,6);
MySet<Integer> otherSet = MySetImpl.<Integer>of(6,2,7,4,5,8);
mySet.union(otherSet);
mySet;
mySet.intersect(otherSet);
mySet;
mySet.union(mySet);
mySet.intersect(mySet);
MySet<String> anotherSet = MySetImpl.of("a", "b", "c", "d", "e");
MySet<String> lastSet = MySetImpl.of("a", "d", "e", "q", "r");
anotherSet.union(lastSet);
anotherSet.intersect(lastSet);
lastSet.union(lastSet);
lastSet.intersect(lastSet);
/exit
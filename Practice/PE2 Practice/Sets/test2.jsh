/open MySetImp.java
/open MySet.java

MySet<Integer> mySet = new MySetImpl<>();
mySet.size();
mySet.add(2);
mySet.add(2);
MySet<Integer> otherSet = MySetImpl.<Integer>of(1, 2, 3, 4);
MySet<Integer> anotherSet = MySetImpl.<Integer>of(1, 2, 3, 4);
try {
    MySet<Integer> lastSet = MySetImpl.<Integer>of(1, 1, 2, 3, 4);
} catch (DuplicateNotAllowedException ex) {
    System.out.println(ex.getMessage());
}
otherSet.contains(2);
otherSet.add(5);
otherSet;
otherSet.remove(1);
/exit

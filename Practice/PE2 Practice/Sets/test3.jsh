/open MySetImp.java
/open MySet.java

MySet<Integer> mySet = new MySetImpl<>();
mySet.add(1);
mySet.add(2);
mySet.add(3);
mySet.join(" -> ");
mySet;
MySet<String> otherSet;
try {
    otherSet = MySetImpl.of("have", "fun", "doing", "it");
} catch (DuplicateNotAllowedException ex) {
    System.out.println(ex.getMessage());
} 
otherSet.join(" ");
otherSet.join(" - ");
otherSet;
/exit


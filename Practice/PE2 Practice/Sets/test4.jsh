/open MySetImp.java
/open MySet.java

MySet<Integer> mySet = new MySetImpl<>();
mySet.add(1);
mySet.add(2);
mySet.add(3);
MySet<String> otherSet;
try {
    otherSet = MySetImpl.<String>of("have", "fun", "doing", "it");
} catch (DuplicateNotAllowedException ex) {
    System.out.println(ex.getMessage());
} 
mySet.get(x -> x > 2);
mySet.get(x -> x > 2).orElse(0);
mySet.get(x -> x > 3);
mySet.get(x -> x > 3).orElse(0);
mySet;
otherSet;
otherSet.get(x -> x.length() == 2);
otherSet.get(x -> x.length() == 2).orElse("not found");
otherSet.get(x -> x.length() > 10);
otherSet.get(x -> x.length() > 10).orElse("not found");
/exit


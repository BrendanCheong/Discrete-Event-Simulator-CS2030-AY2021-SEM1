A a1 = new A()
A a2 = new A()
a1.set(a2)
a2.set(a1)

boolean checker(A obj1, A obj2) {
    return obj1.get().equals(obj2) && obj2.get().equals(obj1);
}
checker(a1,a2)
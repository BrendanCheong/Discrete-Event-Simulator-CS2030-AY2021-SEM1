public class Main {

    public static void main(String[] args) {
        O a = new A().set(new A().set(new B().set(new B().set(a))));

        System.out.println(a); // a references an instance of type A
        System.out.println(a.get());
        System.out.println(a.get().get());
        System.out.println(a.get().get().get());
        System.out.println(a.get().get().get().get());
        System.out.println(a.get().get().get().get().get());
    }
}
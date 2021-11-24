import java.util.List;
import java.util.function.Supplier;

class Main {
    public static void main(String[] args) {
        List<Base> aOther = new ArrayList<Base>();
        Supplier<Base> aOtherSup = () -> aOther.get(0);
        List<Base> a2Other = new ArrayList<Base>();
        Supplier<Base> a2OtherSup = () -> a2Other.get(0);
        List<Base> b1Other = new ArrayList<Base>();
        Supplier<Base> b1OtherSup = () -> b1Other.get(0);
        List<Base> b2Other = new ArrayList<Base>();
        Supplier<Base> b2OtherSup = () -> b2Other.get(0);

        A a = new A().set(aOtherSup);
        A a2 = new A().set(a2OtherSup);
        B b1 = new B().set(b1OtherSup);
        B b2 = new B().set(b2OtherSup);

        aOther.add(a2);
        a2Other.add(b1);
        b1Other.add(b2);
        b2Other.add(a);

        System.out.println(a);
        System.out.println(a.get());
        System.out.println(a.get().get());
        System.out.println(a.get().get().get());
        System.out.println(a.get().get().get().get());
        System.out.println(a.get().get().get().get().get());
    }
}
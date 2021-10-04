import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate; // filter
import java.util.function.Function; // map
import java.uti.function.BiFunction; // reduce

class ImList<T> { // scope of T is within the class
    private final List<T> list;

    ImList() {
        this.list = new ArrayList<T>();
    }

    private ImList(List<T> oldList) {
        this.list = new ArrayList<T>(oldList);
    }

    // note for generic methods, we don't make use of T at all
    // but if we change U to a T, we don't actually use the global T but rather the static's T
    // this is because its a static method, where static can only use its own T, not the class's T
    // but to prevent confusion, we will just use U type
    static <U> ImList<U> of(U ... arr) {
        ImList<U> newList = new ImList<U>();
        for (U elem : arr) {
            newList.list.add(elem);
        }
        return newList;
    }

    ImList<T> add(T elem) { // add elem to the back
        ImList<T> newList = new ImList<T>(this.list);
        newList.list.add(elem);
        return newList;
    }

    ImList<T> set(int index, T elem) { // set element at index to elem 
        ImList<T> newList = new ImList<T>(this.list);
        newList.list.set(index, elem);
        return newList;
    }

    ImList<T> remove(int index) { // remove element at index
        ImList<T> newList = new ImList<T>(this.list);
        newList.list.remove(index);
        return newList;
    }

    ImList<T> sort(Comparator<T> cmp) { // sort the list using desired comparator
        ImList<T> newList = new ImList<T>(this.list);
        newList.list.sort(cmp);
        return newList;
    }
    ImList<T> sortSuper(Comparator<? super T> cmp) { 
        ImList<T> newList = new ImList<T>(this.list);
        newList.list.sort(cmp);
        return newList;
    }

    ImList<T> filter(Predicate<T> pred) { // Predicate is an interface with 1 abstract method: test(T t)
        ImList<T> newList = new ImList<T>(this.list);
        for (T elem : this.list) {
            if (pred.test(elem)) { // if the element passes the predicate's test method, add to newList
                newList.list.add(elem);
            }
        }
        return newList;
    }
    ImList<T> filterSuper(Predicate<? super T> pred) { 
        ImList<T> newList = new ImList<T>(this.list); //  we HAVE to define wildcard super here
        for (T elem : this.list) { // to allow parent classes to be passed in
            if (pred.test(elem)) { 
                newList.list.add(elem);
            }
        }
        return newList;
    }

    <R> ImList<R> map(Function<? super T, ? extends R> mapper) { // we output a type R and take in Type T as input, so we must return a List <R>
        ImList<R> newList = new ImList<R>(); // output R
        for (T elem : this.list) { // input T
            newList.list.add(mapper.apply(elem));
        }
        return newList;
    }

    <U> U reduce(U seed, BiFunction<U, T, U> reducer) { // BiFunction<T, U, R> apply(T, U) takes in input T and input U, and outputs R, T is the seed, the first element
        // in this case, we want output U, and the first seed is U while we iterate over T arrayList
        for (T elem : this.list) {
            seed = reducer.apply(seed, elem); // U, T
        }
        return seed; // U
    }

    ImList<T> addAll(ImList<? extends T> otherList) { // we can add Cheesburger list with burger methods to list of burgers
        ImList<T> newList = new ImList<T>(this.list);
        newList.list.addAll(otherList.list);
        return newList;
    }

    @Override
    public String toString() {
        return this.list.toString();
    }
}

class FastFood {
    int x;

    FastFood(int x) { this.x = x; }

    int fastfood() { return this.x; } 

    public String toString() { 
        return super.toString() + " " + this.x; 
    } 
}

class Burger extends FastFood {
    Burger(int x) { 
        super(x); 
    }

    int burger() { return this.x; } 
}

class CheeseBurger extends Burger {
    CheeseBurger(int x) { 
        super(x); 
    }
}

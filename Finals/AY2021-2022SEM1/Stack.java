import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;
class Stack<T> {
    private final List<T> list;
    Stack() {
        this.list = new ArrayList<T>();
    }
    private Stack(List<T> oldList) {
        this.list = new ArrayList<T>(oldList);
    }
    Stack<T> push(T elem) {
        Stack<T> newStack = new Stack<T>(this.list);
        newStack.list.add(0, elem);
        return newStack;
    }
    boolean isEmpty() {
        return this.list.isEmpty();
    }
    public String toString() {
        return "Top -> " + this.list;
    }

    Pair<Optional<T>,Stack<T>> pop() {
        Optional<T> head = this.isEmpty()
            ? Optional.empty()
            : Optional.ofNullable(this.list.remove(0));
        Stack<T> newStack = new Stack<T>(this.list);
        return new Pair<>(head, newStack);
    }

    int evaluate(String expr) {
        Scanner sc = new Scanner(expr);
        Stack<String> stack = new Stack<>();
        Optional<Integer> answer = Optional.empty();
        while (sc.hasNext()) {
            String term = sc.next();
            stack.push(term);
            if (term.equals("+") || term.equals("*")) {
                String firstTerm = stack.pop().first().get();
                stack = stack.pop().second();
                String secondTerm = stack.pop().first().get();
                stack = stack.pop().second();
                String item = evaluate(firstTerm + term + secondTerm) + "";
                stack.push(item);
            } else {
                Integer value = Integer.parseInt(term);
                answer = Optional.of(value);
            }
        }
        sc.close();
        return answer.map((x) -> x).orElse(0);
    }
}

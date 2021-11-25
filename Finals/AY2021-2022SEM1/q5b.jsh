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

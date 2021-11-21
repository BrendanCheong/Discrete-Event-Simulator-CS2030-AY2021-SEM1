IntStream.
    range(1, 100).
    reduce(0, (x, y) -> x + y);
// the original stream was constructed by adding each number into a variable called int s, this is an example of imperative programming where there is a state change of variable s.
// the new stream is a type of declarative programming, where there is no state change as forEach is not used, meaning to say that the stream is self contained. 
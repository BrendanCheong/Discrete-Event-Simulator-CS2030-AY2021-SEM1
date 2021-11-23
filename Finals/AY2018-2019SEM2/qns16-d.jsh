List<Student> students = new ArrayList<>(
    List.of(new Student(1, "a2"),
        new Student(3, "b1"),
        new Student(3, "a2"),
        new Student(1, "b1"))
);
students.
    stream().
    sorted(new StudentComparator(listComparator)).
    forEach((x) -> System.out.println(x));
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.function.Predicate;

public class Main {

    public enum Gender {
        MALE,FEMALE
    }

    public static void main(String[] args) {

        String male = Gender.MALE.name();
        String female = Gender.FEMALE.name();

        List<Person> people = List.of(
                new Person("John Cena", male),
                new Person("Harry Styles", male),
                new Person("Rebecca Black", female),
                new Person("Alice Rice", female),
                new Person("Marge Thatcher", female),
                new Person("Jerry Seinfeld", male)
            );
        System.out.println(people);

        // Create a filter -> only get Females
        List<Person> females = people.stream()
            .filter(item -> item.getGender().equals(female))
            .collect(Collectors.toList());
        females.forEach(System.out::println);

        // Replace the filter with a Predicate(anon Boolean Function)
        Predicate<Person> personPredicate = person -> person.getGender().equals(female);

        List<Person> females2 = people.stream()
            .filter(personPredicate)
            .collect(Collectors.toList());
        females2.forEach(System.out::println);
    }

}

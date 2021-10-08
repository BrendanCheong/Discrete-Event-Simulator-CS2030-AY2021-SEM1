import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class Main {

    private static final int STUDENT_CLASS_LENGTH = 4;
    private static final int QUERY_LENGTH = 3;
    
    /**
     * Computes whether the selected queries for a created Roster exists or not.
     * <p>First, it adds all the entries for the roster into a new roster</p>
     * <p>It also allows duplicate entries like same name and same module to be 
     * put into the nested roster HashMap</p>
     * <p>Finally, it then takes all the queries and checks if they are in the 
     * roster one by one </p>
     * @param args takes in a String of scanner queries to form students
     */
    public static void main(String[] args) {
        Roster roster = new Roster("Help La Why CS2030 So Hard");
        Scanner sc = new Scanner(System.in);
        int entries = sc.nextInt(); // Iterate through the students in fours
        sc.nextLine(); // Clears the buffer
        List<String> students = new ArrayList<>(); // store the names of students and grades here
        List<String> query = new ArrayList<>(); // store our queries for the roster here

        while (entries > 0) { // add all student details into our student list
            for (int  index = 0; index < STUDENT_CLASS_LENGTH; ++index) {
                students.add(sc.next());
            }
            --entries;
        }

        for (int index = 0; index < students.size(); index += STUDENT_CLASS_LENGTH) {
            // every 4 strings is a full set of student details
            // add student details into the mutatble roster for our queries later
            String name = students.get(index);
            String module = students.get(index + 1);
            String assessement = students.get(index + 2);
            String grade = students.get(index + QUERY_LENGTH);

            // check for duplicated cases
            // same name same module different assessments
            // same name different module
            Student stressedOutStudent = new Student(name)
                .put(new Module(module)
                .put(new Assessment(assessement, grade)));

            if (Objects.isNull(roster.get(name))) {
                roster.put(stressedOutStudent); // check same name and same module
            } else if (Objects.nonNull(roster.get(name).get(module))) {
                roster.get(name).get(module).put(new Assessment(assessement, grade));
            } else { // check same name 
                roster.get(name).put(new Module(module).put(new Assessment(assessement, grade)));
            }
        }
        
        while (sc.hasNext()) { // add all queries into the query list
            for (int i = 0; i < QUERY_LENGTH; ++i) {
                query.add(sc.next());
            }
        }

        for (int index = 0; index < query.size(); index += QUERY_LENGTH) {
            // every 3 strings is a full query for our roster
            String name = query.get(index);
            String module = query.get(index + 1);
            String assessement = query.get(index + 2);
            System.out.println(
                roster.getGrade(name, module, assessement)
            );
        }
        
        sc.close();
    }
}

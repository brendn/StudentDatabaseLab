import java.util.Arrays;
import java.util.Scanner;

/**
 * LAB 3.1  Student Database
 *
 * Application objectives:
 *  Provide information about students in a class of at least 10 people
 *  Prompt the user to ask about a particular student
 *  Give proper responses according to user-submitted information
 *  Ask if the user would like to learn about another student
 *  Validate user choices and ask again if they provide an invalid number or information type
 */
public class StudentDatabaseLab {

    private static final Student[] STUDENTS = {
            new Student("Greg", "Detroit, MI", "Pork Rinds"),
            new Student("Bob", "Lansing, MI", "Pizza"),
            new Student("Joe", "Flint, MI", "Ice cream"),
            new Student("Steve", "Royal Oak, MI", "Sushi"),
            new Student("Mike", "Southfield, MI", "Bread")
    };

    //Error messages
    private static final String ERROR_MESSAGE_NO_STUDENT = "That student does not exist.  Please try again. (enter a number 1-10):";
    private static final String ERROR_MESSAGE_NO_DATA = "That data does not exist.  Please try again. (enter or “hometown” or “favorite food”):";

    //General messages
    private static final String MESSAGE_WELCOME = "Welcome to our Java class.  Which student would you like to learn more about? (enter a number 1-10):";
    private static final String MESSAGE_STUDENT_INFO = "Student %s is %s.  What would you like to know about %s? (enter or “hometown” or “favorite food”):%n";
    private static final String MESSAGE_STUDENT_HOMETOWN = "%s is from %s.  Would you like to know more? (enter “yes” or “no“):";
    private static final String MESSAGE_STUDENT_FOOD = "%s's favorite food is %s.  Would you like to know more? (enter “yes” or “no“):";
    private static final String MESSAGE_EXIT = "Thanks!";

    //Accepted inputs for asking for information
    private static final String[] ACCEPTED_INPUTS_FOOD = { "favorite food", "food" };
    private static final String[] ACCEPTED_INPUTS_HOMETOWN = { "town", "hometown", "home"};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean runProgram = true;
        boolean outOfBounds = false;

        do {
            if (!outOfBounds) {
                System.out.println(MESSAGE_WELCOME);
            } else {
                outOfBounds = false;
            }
            String s = scanner.nextLine();
            int studentID = Integer.parseInt(s) - 1;

            try {
                do {
                    //Print student name and asks what the user would like to know
                    System.out.printf(MESSAGE_STUDENT_INFO, studentID + 1, STUDENTS[studentID].getName(), STUDENTS[studentID].getName());
                    s = scanner.nextLine();

                    //Prints the requested info and asks if the user wants to continue
                    String studentInfo = getStudentInfo(studentID, s);
                    System.out.println(studentInfo);

                    //If we get an error message, prompt the user to enter new info
                    while (ERROR_MESSAGE_NO_DATA.equals(studentInfo)) {
                        s = scanner.nextLine();
                        studentInfo = getStudentInfo(studentID, s);
                        System.out.println(studentInfo);
                    };

                    //Whether the user wants to continue the program or not.  Saying yes will restart this portion.
                    s = scanner.nextLine();
                } while (s.equalsIgnoreCase("yes"));
                //End the program
                runProgram = false;
                System.out.println(MESSAGE_EXIT);
            } catch (IndexOutOfBoundsException e) {
                System.out.println(ERROR_MESSAGE_NO_STUDENT);
                outOfBounds = true;
            }
        } while (runProgram);

        scanner.close();
    }

    /**
     * Provides a response message for the given student based on the given info requested.  At the moment, the
     * only info that can be provided is the student's favorite food and hometown.
     *
     * @param id The ID of the student (1-10)
     * @param info The requested info (e.g. "favorite food", "food", "hometown", "town")
     */
    private static String getStudentInfo(int id, String info) {
        try {
            if (Arrays.stream(ACCEPTED_INPUTS_FOOD).anyMatch(s -> s.equalsIgnoreCase(info))) {
                return String.format(MESSAGE_STUDENT_FOOD, STUDENTS[id].getName(), STUDENTS[id].getFavoriteFood());
            } else if (Arrays.stream(ACCEPTED_INPUTS_HOMETOWN).anyMatch(s -> s.equalsIgnoreCase(info))) {
                return String.format(MESSAGE_STUDENT_HOMETOWN, STUDENTS[id].getName(), STUDENTS[id].getHometown());
            } else {
                return ERROR_MESSAGE_NO_DATA;
            }
        } catch (IndexOutOfBoundsException e) {
            return ERROR_MESSAGE_NO_STUDENT;
        }
    }

    /**
     * Holds student information to keep things a bit more tidy.
     *
     * This class only contains three bits of info - their name, hometown, and favorite food.
     */
    static class Student {
        //The name of the student
        private final String NAME;

        //The student's hometown
        private final String HOMETOWN;

        //The student's favorite food
        private final String FAVORITE_FOOD;

        /**
         * Creates a student object with all the necessary information.
         *
         * @param name The name of the student
         * @param hometown The student's hometown
         * @param favoriteFood The student's favorite food
         */
        public Student(String name, String hometown, String favoriteFood) {
            this.NAME = name;
            this.HOMETOWN = hometown;
            this.FAVORITE_FOOD = favoriteFood;
        }

        /**
         * @return The name of the student
         */
        public String getName() {
            return NAME;
        }

        /**
         * @return The student's hometown
         */
        public String getHometown() {
            return HOMETOWN;
        }

        /**
         * @return The student's favorite food
         */
        public String getFavoriteFood() {
            return FAVORITE_FOOD;
        }
    }
}

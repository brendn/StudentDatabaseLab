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

    //Each of the students in the class
    private static final String[] STUDENTS = new String[] {
            "Greg", "Bob", "Joe", "Steve", "Mike"
    };

    //Each of the students' hometowns
    private static final String[] STUDENT_HOMETOWNS = new String[] {
            "Detroit, MI", "Lansing, MI", "Flint, MI", "Royal Oak, MI", "Southfield, MI"
    };

    //Each of the students' favorite foods
    private static final String[] STUDENT_FOODS = new String[] {
            "Pork Rinds", "Pizza", "Ice cream", "Sushi", "Bread"
    };

    //Error messages
    private static final String ERROR_NO_STUDENT = "That student does not exist.  Please try again. (enter a number 1-10):";
    private static final String ERROR_NO_DATA = "That data does not exist.  Please try again. (enter or “hometown” or “favorite food”):";

    //General messages
    private static final String MESSAGE_WELCOME = "Welcome to our Java class.  Which student would you like to learn more about? (enter a number 1-10):";
    private static final String MESSAGE_STUDENT_INFO = "Student %s is %s.  What would you like to know about %s? (enter or “hometown” or “favorite food”):";
    private static final String MESSAGE_STUDENT_HOMETOWN = "%s is from %s.  Would you like to know more? (enter “yes” or “no“):";
    private static final String MESSAGE_STUDENT_FOOD = "%s's favorite food is %s.  Would you like to know more? (enter “yes” or “no“):";
    private static final String MESSAGE_EXIT = "Thanks!";

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
                    System.out.printf(MESSAGE_STUDENT_INFO + "\n", studentID + 1, STUDENTS[studentID], STUDENTS[studentID]);
                    s = scanner.nextLine();

                    //Prints the requested info and asks if the user wants to continue
                    String studentInfo = getStudentInfo(studentID, s);
                    System.out.println(studentInfo);

                    //If we get an error message, prompt the user to enter new info
                    while (studentInfo.equals(ERROR_NO_DATA)) {
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
                System.out.println(ERROR_NO_STUDENT);
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
            if (info.equalsIgnoreCase("favorite food") || info.equalsIgnoreCase("food")) {
                return String.format(MESSAGE_STUDENT_FOOD, STUDENTS[id], STUDENT_FOODS[id]);
            } else if (info.equalsIgnoreCase("hometown") || info.equalsIgnoreCase("town")) {
                return String.format(MESSAGE_STUDENT_HOMETOWN, STUDENTS[id], STUDENT_HOMETOWNS[id]);
            } else {
                return ERROR_NO_DATA;
            }
        } catch (IndexOutOfBoundsException e) {
            return ERROR_NO_STUDENT;
        }
    }
}

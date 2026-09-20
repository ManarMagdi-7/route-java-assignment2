import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] studentNames = new String[5];
        double[][] grades = new double[5][3];

        System.out.println("Enter student name");
        for (int i = 0; i < 5; i++) {
            System.out.print("Student" + (i + 1) + " name: ");
            studentNames[i] = scanner.nextLine();
            for (int j = 0; j < 3; j++) {
                double tempGrade;
                while (true) {
                    System.out.print("Enter grade for Subject " + (j + 1) +": ");
                    if (scanner.hasNextDouble()) {
                        tempGrade = scanner.nextDouble();
                        if (tempGrade >= 0 && tempGrade <= 100) {
                            grades[i][j] = tempGrade;
                            break;
                        } else {
                            System.out.println("Invalid grade. Must be between 0 and 100.");
                        }
                    } else {
                        System.out.println("Invalid input. Please enter a numeric grade.");
                        scanner.next();
                    }
                }
            }
            scanner.nextLine();
        }

        int choice = -1;
        do {
            displayMenu();

            System.out.print("Enter your choice: ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Please enter a valid menu number.");
                scanner.next();
                continue;
            }

            System.out.println();
            switch (choice) {
                case 1:
                    showAllStudentNames(studentNames);
                    break;

                case 2:
                    showAllGrades(studentNames, grades, 5, 3);
                    break;

                case 3:
                    searchStudentByName(scanner, studentNames, grades, 5, 3);
                    break;

                case 4:
                    countPassedStudents(studentNames, grades, 5, 3);
                    break;

                case 5:
                    showSubjectStatistics(grades, 5, 3);
                    break;

                case 0:
                    System.out.println("Exiting the program. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option. Please choose a number from the menu.");
            }

            System.out.println();
        } while (choice != 0);

        scanner.close();
    }

    public static void displayMenu() {
        System.out.println("              MAIN MENU                 ");
        System.out.println("----------------------------------------");
        System.out.println("1. Show All Students names");
        System.out.println("2. Show all Students grades in each subject");
        System.out.println("3. Search Student by name");
        System.out.println("4. Count Passed Students");
        System.out.println("5. Show Subject Statistics");
        System.out.println("0. Exit");
    }

    public static void showAllStudentNames(String[] names) {
        System.out.println("=== List of Students ===");
        for (int i = 0; i < names.length; i++) {
            System.out.println((i + 1) + ". " + names[i]);
        }
    }

    public static void showAllGrades(String[] names, double[][] grades, int numStudents, int numSubjects) {
        System.out.println("=== All Students Grades ===");
        for (int i = 0; i < numStudents; i++) {
            System.out.println("Student: " + names[i]);
            for (int j = 0; j < numSubjects; j++) {
                double score = grades[i][j];
                char letter = getLetterGrade(score);
                System.out.println("   Subject " + (j + 1) + ": " + score + " (" + letter + ")");
            }
        }
    }

    public static void searchStudentByName(Scanner scanner, String[] names, double[][] grades, int numStudents, int numSubjects) {
        System.out.print("Enter the name of the student to search: ");
        String searchTarget = scanner.nextLine();
        boolean found = false;

        for (int i = 0; i < numStudents; i++) {
            if (names[i].equalsIgnoreCase(searchTarget)) {
                found = true;
                System.out.println("Record found for: " + names[i]);
                for (int j = 0; j < numSubjects; j++) {
                    double score = grades[i][j];
                    System.out.println("   Subject " + (j + 1) + ": " + score + " (" + getLetterGrade(score) + ")");
                }
                break;
            }
        }
        if (!found) {
            System.out.println("Student " + searchTarget + " was not found in the records.");
        }
    }

    public static void countPassedStudents(String[] names, double[][] grades, int numStudents, int numSubjects) {
        int passedCount = 0;

        for (int i = 0; i < numStudents; i++) {
            boolean passedAll = true;

            for (int j = 0; j < numSubjects; j++) {
                if (grades[i][j] < 50.0) {
                    passedAll = false;
                    break;
                }
            }

            if (passedAll) {
                passedCount++;
            }
        }

        System.out.println("Number of students who passed all subjects: " + passedCount + " out of " + numStudents);
    }

    public static void showSubjectStatistics(double[][] grades, int numStudents, int numSubjects) {
        System.out.println("=== Subject Statistics ===");

        for (int j = 0; j < numSubjects; j++) {
            double totalSum = 0;
            double highestGrade = grades[0][j];

            for (int i = 0; i < numStudents; i++) {
                double current = grades[i][j];
                totalSum += current;

                if (current > highestGrade) {
                    highestGrade = current;
                }
            }

            double average = totalSum / numStudents;

            System.out.println("Subject " + (j + 1) + ":");
            System.out.printf("   Average: %.2f\n", average);
            System.out.println("   Highest Grade: " + highestGrade + " (" + getLetterGrade(highestGrade) + ")");
        }
    }

    public static char getLetterGrade(double grade) {
        if (grade >= 85) {
            return 'A';
        } else if (grade >= 75) {
            return 'B';
        } else if (grade >= 65) {
            return 'C';
        } else if (grade >= 50) {
            return 'D';
        } else {
            return 'F';
        }
    }
}
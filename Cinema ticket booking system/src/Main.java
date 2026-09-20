import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rows = 5;
        int cols = 6;
        char[][] seats = new char[rows][cols];
        String[] movieNames = {"Superman", "Avatar", "Minecraft", "Inside Out", "F1"};

        initializeSeats(seats, rows, cols);

        int choice = -1;
        do {
            displayMenu();
            System.out.print("Enter your choice: ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
                continue;
            }

            System.out.println();
            switch (choice) {
                case 1:
                    displaySeats(seats, rows, cols);
                    break;
                case 2:
                    bookSeat(scanner, seats, rows, cols);
                    break;
                case 3:
                    cancelBooking(scanner, seats, rows, cols);
                    break;
                case 4:
                    showAllMovies(movieNames);
                    break;
                case 5:
                    showSeatStatistics(seats, rows, cols);
                    break;
                case 0:
                    System.out.println("Thank you for using the Cinema Booking System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select an option from the menu.");
            }
            System.out.println();

        } while (choice != 0);

        scanner.close();
    }

    public static void initializeSeats(char[][] seats, int rows, int cols) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                seats[i][j] = 'O';
            }
        }
    }

    public static void displayMenu() {
        System.out.println("========================================");
        System.out.println("              MAIN MENU                 ");
        System.out.println("========================================");
        System.out.println("1. Display Seats");
        System.out.println("2. Book Seat");
        System.out.println("3. Cancel Booking");
        System.out.println("4. Show all movies");
        System.out.println("5. Show number of available and booked seats");
        System.out.println("0. Exit");
        System.out.println("========================================");
    }

    public static void displaySeats(char[][] seats, int rows, int cols) {
        System.out.print("   ");
        for (int j = 1; j <= cols; j++) {
            System.out.print("Seat " + j + "  ");
        }
        System.out.println();

        for (int i = 0; i < rows; i++) {
            System.out.print("Row " + (i + 1) + " ");
            for (int j = 0; j < cols; j++) {
                System.out.print("  " + seats[i][j] + "     ");
            }
            System.out.println();
        }
    }

    public static void bookSeat(Scanner scanner, char[][] seats, int rows, int cols) {
        System.out.print("Enter seat number (e.g. 12 for Row 1, Col 2): ");
        int seatInput;

        if (scanner.hasNextInt()) {
            seatInput = scanner.nextInt();
            scanner.nextLine();
        } else {
            System.out.println("Invalid Seat.");
            scanner.next();
            return;
        }

        int row = seatInput / 10;
        int col = seatInput % 10;

        if (row < 1 || row > rows || col < 1 || col > cols) {
            System.out.println("Invalid Seat.");
            return;
        }

        int rowIndex = row - 1;
        int colIndex = col - 1;

        if (seats[rowIndex][colIndex] == 'X') {
            System.out.println("Seat already booked.");
        } else {
            seats[rowIndex][colIndex] = 'X';
            System.out.println("Seat booked successfully.");
        }
    }

    public static void cancelBooking(Scanner scanner, char[][] seats, int rows, int cols) {
        System.out.print("Enter seat number to cancel (e.g. 12 for Row 1, Col 2): ");
        int seatInput;

        if (scanner.hasNextInt()) {
            seatInput = scanner.nextInt();
            scanner.nextLine();
        } else {
            System.out.println("Invalid Seat.");
            scanner.next();
            return;
        }

        int row = seatInput / 10;
        int col = seatInput % 10;

        if (row < 1 || row > rows || col < 1 || col > cols) {
            System.out.println("Invalid Seat.");
            return;
        }

        int rowIndex = row - 1;
        int colIndex = col - 1;

        if (seats[rowIndex][colIndex] == 'O') {
            System.out.println("Seat is not booked.");
        } else {
            seats[rowIndex][colIndex] = 'O';
            System.out.println("Booking cancelled successfully.");
        }
    }

    public static void showAllMovies(String[] movieNames) {
        System.out.println("=== Movies Playing ===");
        for (int i = 0; i < movieNames.length; i++) {
            System.out.println((i + 1) + ". " + movieNames[i]);
        }
    }

    public static void showSeatStatistics(char[][] seats, int rows, int cols) {
        int bookedCount = 0;
        int availableCount = 0;
        int totalSeats = rows * cols;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (seats[i][j] == 'X') {
                    bookedCount++;
                } else {
                    availableCount++;
                }
            }
        }

        System.out.println("=== Seat Statistics ===");
        System.out.println("Booked seats: " + bookedCount);
        System.out.println("Available seats: " + availableCount);
        System.out.println("Total seats: " + totalSeats);

        double occupancyRate = ((double) bookedCount / totalSeats) * 100.0;
        if (occupancyRate > 80.0) {
            System.out.println("Status: Almost Full");
        }
    }
}
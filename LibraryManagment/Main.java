package LibraryManagment;
import java.util.*;
public class Main {
	public static void main(String[]args) {
		Scanner s=new Scanner(System.in);
		Library library = new Library();
		int choice;
        Librarian librarian = new Librarian("Ajay");
        do {

            System.out.println("\n==============================================");
            System.out.println("       LIBRARY MANAGEMENT SYSTEM");
            System.out.println("==============================================");

            System.out.println("1. Add Book");
            System.out.println("2. Display All Books");
            System.out.println("3. Search Book");
            System.out.println("4. Issue Book");
            System.out.println("5. Return Book");
            System.out.println("6. Check Book Availability");
            System.out.println("7. Calculate Fine");
            System.out.println("8. Due Date Reminder");
            System.out.println("9. Display Issued Books");
            System.out.println("10. Generate Report");
            System.out.println("11. Librarian Details");
            System.out.println("12. Exit");

            System.out.print("\nEnter your choice: ");

            choice = s.nextInt();

            s.nextLine();

            switch (choice) {

                // ================= ADD BOOK =================

                case 1:

                    System.out.print("Enter Book ID: ");
                    int id = s.nextInt();

                    s.nextLine();

                    System.out.print("Enter Book Title: ");
                    String title = s.nextLine();

                    System.out.print("Enter Author Name: ");
                    String author = s.nextLine();

                    Books book = new Books(id, title, author);

                    library.addBook(book);

                    break;


                // ================= DISPLAY =================

                case 2:

                    library.displayAllBooks();

                    break;


                // ================= SEARCH =================

                case 3:

                    System.out.print("Enter Book Title: ");

                    String searchTitle = s.nextLine();

                    library.searchBook(searchTitle);

                    break;


                // ================= ISSUE =================

                case 4:

                    System.out.print("Enter Student Name: ");

                    String studentName = s.nextLine();

                    System.out.print("Enter Book ID: ");

                    int issueId = s.nextInt();

                    librarian.approveIssue();

                    library.issueBook(studentName, issueId);

                    break;


                // ================= RETURN =================

                case 5:

                    System.out.print("Enter Student Name: ");

                    String returnStudent = s.nextLine();

                    library.returnBook(returnStudent);

                    break;


                // ================= AVAILABILITY =================

                case 6:

                    System.out.print("Enter Book ID: ");

                    int availabilityId = s.nextInt();

                    library.checkAvailability(availabilityId);

                    break;


                // ================= FINE =================

                case 7:

                    System.out.print("Enter Student Name: ");

                    String fineStudent = s.nextLine();

                    library.calculateFine(fineStudent);

                    break;


                // ================= REMINDER =================

                case 8:

                    System.out.print("Enter Student Name: ");

                    String reminderStudent = s.nextLine();

                    library.dueDateReminder(reminderStudent);

                    break;


                // ================= ISSUED BOOKS =================

                case 9:

                    library.displayIssuedBooks();

                    break;


                // ================= REPORT =================

                case 10:

                    library.generateReport();

                    break;


                // ================= LIBRARIAN =================

                case 11:

                    librarian.displayLibrarian();

                    break;


                // ================= EXIT =================

                case 12:

                    System.out.println("\nThank you for using Library Management System!");

                    break;


                default:

                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 12);


        s.close();
    
	}
}

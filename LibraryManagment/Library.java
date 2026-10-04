package LibraryManagment;
import java.util.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
public class Library {
	// Store all books
    ArrayList<Books> books = new ArrayList<>();

    // Store issued books
    // Student Name -> Book
    Map<String, Books> issuedBooks = new HashMap<>();

    // Store due dates
    // Student Name -> Due Date
    Map<String, LocalDate> dueDates = new HashMap<>();


    // ================= ADD BOOK =================

    public void addBook(Books book) {

        books.add(book);

        System.out.println("Book added successfully!");
    }


    // ================= DISPLAY BOOKS =================

    public void displayAllBooks() {

        if (books.isEmpty()) {

            System.out.println("No books available.");

            return;
        }

        System.out.println("\n------------------------------------------------------------");
        System.out.println("ID    Title                 Author          Status");
        System.out.println("------------------------------------------------------------");

        for (Books book : books) {

            book.displayBook();
        }
    }


    // ================= SEARCH BOOK =================

    public void searchBook(String title) {

        boolean found = false;

        for (Books book : books) {

            if (book.getTitle().equalsIgnoreCase(title)) {

                System.out.println("\nBook Found:");
                book.displayBook();

                found = true;
                break;
            }
        }

        if (!found) {

            System.out.println("Book not found.");
        }
    }


    // ================= SEARCH BY ID =================

    public Books findBookById(int id) {

        for (Books book : books) {

            if (book.getBookId() == id) {

                return book;
            }
        }

        return null;
    }


    // ================= ISSUE BOOK =================

    public void issueBook(String studentName, int bookId) {

        Books book = findBookById(bookId);

        if (book == null) {

            System.out.println("Book not found.");

            return;
        }

        if (!book.isAvailable()) {

            System.out.println("Book is already issued.");

            return;
        }

        book.setAvailable(false);

        issuedBooks.put(studentName, book);

        // Book can be kept for 7 days
        LocalDate dueDate = LocalDate.now().plusDays(7);

        dueDates.put(studentName, dueDate);

        System.out.println("\nBook issued successfully!");
        System.out.println("Student Name : " + studentName);
        System.out.println("Book        : " + book.getTitle());
        System.out.println("Issue Date  : " + LocalDate.now());
        System.out.println("Due Date    : " + dueDate);
    }


    // ================= RETURN BOOK =================

    public void returnBook(String studentName) {

        Books book = issuedBooks.get(studentName);

        if (book == null) {

            System.out.println("No book is issued to this student.");

            return;
        }

        LocalDate dueDate = dueDates.get(studentName);

        LocalDate today = LocalDate.now();

        long lateDays = ChronoUnit.DAYS.between(dueDate, today);

        if (lateDays > 0) {

            int fine = (int) lateDays * 10;

            System.out.println("Book returned late.");
            System.out.println("Late Days : " + lateDays);
            System.out.println("Fine      : ₹" + fine);

        } else {

            System.out.println("Book returned on time.");
            System.out.println("Fine      : ₹0");
        }

        book.setAvailable(true);

        issuedBooks.remove(studentName);

        dueDates.remove(studentName);

        System.out.println("Book returned successfully!");
    }


    // ================= BOOK AVAILABILITY =================

    public void checkAvailability(int bookId) {

        Books book = findBookById(bookId);

        if (book == null) {

            System.out.println("Book not found.");

            return;
        }

        System.out.println("\nBook: " + book.getTitle());

        if (book.isAvailable()) {

            System.out.println("Status: Available");

        } else {

            System.out.println("Status: Issued");
        }
    }


    // ================= FINE CALCULATOR =================

    public void calculateFine(String studentName) {

        LocalDate dueDate = dueDates.get(studentName);

        if (dueDate == null) {

            System.out.println("No issued book found for this student.");

            return;
        }

        LocalDate today = LocalDate.now();

        long lateDays = ChronoUnit.DAYS.between(dueDate, today);

        if (lateDays > 0) {

            int fine = (int) lateDays * 10;

            System.out.println("\nFine Details");
            System.out.println("--------------------");
            System.out.println("Student  : " + studentName);
            System.out.println("Due Date : " + dueDate);
            System.out.println("Today    : " + today);
            System.out.println("Late Days: " + lateDays);
            System.out.println("Fine     : ₹" + fine);

        } else {

            System.out.println("No fine.");
        }
    }


    // ================= DUE DATE REMINDER =================

    public void dueDateReminder(String studentName) {

        LocalDate dueDate = dueDates.get(studentName);

        if (dueDate == null) {

            System.out.println("No book issued to this student.");

            return;
        }

        LocalDate today = LocalDate.now();

        System.out.println("\nDue Date Reminder");
        System.out.println("------------------------");
        System.out.println("Student  : " + studentName);
        System.out.println("Due Date : " + dueDate);

        if (today.isAfter(dueDate)) {

            System.out.println("Status   : BOOK IS OVERDUE!");

        } else if (today.equals(dueDate)) {

            System.out.println("Status   : Book is due today!");

        } else {

            long days = ChronoUnit.DAYS.between(today, dueDate);

            System.out.println("Days Left: " + days);
        }
    }


    // ================= ISSUED BOOKS =================

    public void displayIssuedBooks() {

        if (issuedBooks.isEmpty()) {

            System.out.println("No books are currently issued.");

            return;
        }

        System.out.println("\nIssued Books");
        System.out.println("----------------------------------------");

        for (Map.Entry<String, Books> entry : issuedBooks.entrySet()) {

            String student = entry.getKey();

            Books book = entry.getValue();

            System.out.println(
                    "Student: " + student
                    + " | Book: " + book.getTitle()
                    + " | Due Date: " + dueDates.get(student)
            );
        }
    }


    // ================= REPORT =================

    public void generateReport() {

        int availableBooks = 0;
        int issuedBookCount = 0;

        for (Books book : books) {

            if (book.isAvailable()) {

                availableBooks++;

            } else {

                issuedBookCount++;
            }
        }

        System.out.println("\n==================================");
        System.out.println("       LIBRARY REPORT");
        System.out.println("==================================");

        System.out.println("Total Books     : " + books.size());
        System.out.println("Available Books : " + availableBooks);
        System.out.println("Issued Books    : " + issuedBookCount);

        System.out.println("==================================");
    }
}

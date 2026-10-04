package LibraryManagment;
import java.util.*;
public class Librarian {
	private String name;

    public Librarian(String name) {

        this.name = name;
    }

    public void displayLibrarian() {

        System.out.println("Librarian: " + name);
    }

    public void approveIssue() {

        System.out.println("Book issue approved by librarian.");
    }

    public void collectFine(int fine) {

        System.out.println("Fine collected: ₹" + fine);
    }
}

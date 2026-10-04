package LibraryManagment;
import java.util.*;
public class Books {
	 private int bookId;
	    private String title;
	    private String author;
	    private boolean available;

	    public Books(int bookId, String title, String author) {
	        this.bookId = bookId;
	        this.title = title;
	        this.author = author;
	        this.available = true;
	    }
	    public int getBookId() {
	        return bookId;
	    }

	    public String getTitle() {
	        return title;
	    }

	    public String getAuthor() {
	        return author;
	    }

	    public boolean isAvailable() {
	        return available;
	    }

	    public void setAvailable(boolean available) {
	        this.available = available;
	    }

	    public void displayBook() {

	        System.out.println(
	                bookId + " | "
	                + title + " | "
	                + author + " | "
	                + (available ? "Available" : "Issued")
	        );
	    }

}

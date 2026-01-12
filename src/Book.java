/*
* Stores and returns book object
*  information
*
* @author Christopher Cabrera
* @version 01/11/2026
*/
public class Book {

	private String id;
	private String title;
	private String author;
	private long isbn;
	private int pageNum;

	public Book() {
		id = "Uknown";
		title = "Uknown";
		author = "Uknown";
		isbn = 0;
		pageNum = 0;
	}

	public Book(String id, String title, String author, long isbn, int pageNum) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.pageNum = pageNum;
	}

	public String getID() {
		return this.id;
	}

	public String getTitle() {
		return this.title;
	}
	
	@Override
	public String toString() {
		return (title + " by " + author + " (Pg." + pageNum + ")" 
				+ "\n" + "ISBN: " + isbn + " ID: " + id + "\n");
	}
}

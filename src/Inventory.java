import java.util.Scanner;
import java.util.ArrayList;

public class Inventory {

	public static void main(String[] args) {

		String userInput = "0";

		Scanner scnr = new Scanner(System.in);

		ArrayList<Book> library = new ArrayList<>();
		ArrayList<Book> borrowed = new ArrayList<>();

		System.out.println("The library is open \n");

		while (!userInput.equals("6")) {
			
			printMenu();
			userInput = scnr.nextLine();
			
			switch (userInput) {
			case "1":
				addBook(scnr, library, borrowed, userInput);
				System.out.println();
				break;
			case "2":
				returnBook(library, borrowed, userInput, scnr);
				System.out.println();
				break;
			case "3":
				printAllBooks(library);
				System.out.println();
				break;
			case "4":
				borrowBook(library, borrowed, userInput, scnr);
				System.out.println();
				break;
			case "5":
				search(scnr, library, userInput);
				System.out.println();
				break;
			case "6":
				System.out.println("The library is now closed");
				break;
			default:
				System.out.println("Not a valid option");
				break;
			}
		}
		scnr.close();
	}

	private static void search(Scanner scnr, ArrayList<Book> library, String userInput) {
		System.out.println("Enter book's title");
		boolean foundMatch = true;
		userInput = scnr.nextLine();

		for (int i = 0; i < library.size(); i++) {
		    if (library.get(i).getTitle().toLowerCase().contains(userInput.toLowerCase())) {
		        System.out.println(library.get(i));
		        foundMatch = true;
		    }
		}

		if (!foundMatch) {
		    System.out.println("No matching book found");
		}
	}

	public static void borrowBook(ArrayList<Book> library, ArrayList<Book> borrowed, String userInput, Scanner scnr) {
		if (library.isEmpty()) {
			System.out.println("There's no books to borrow");
			return;
		}

		System.out.println("Enter book's ID to borrow");
		userInput = scnr.nextLine();

		for (int i = 0; i < borrowed.size(); i++) {
			if (userInput.equals(borrowed.get(i).getID())) {
				System.out.println("Book is already borrowed");
				return;
			}
		}
		for (int i = 0; i < library.size(); i++) {
			if (userInput.equals(library.get(i).getID())) {
				Book book = library.get(i);
				library.remove(i);
				borrowed.add(book);
				System.out.println("Book successfully borrowed");
				return;
			} else if (i == library.size() - 1) {
				System.out.println("Book not found");
				return;
			}
		}
	}

	public static void printAllBooks(ArrayList<Book> library) {

		if (library.isEmpty()) {
			System.out.println("There's no books to print");
			return;
		}
		for (Book b : library) {
			System.out.println(b);
		}
	}

	public static void returnBook(ArrayList<Book> library, ArrayList<Book> borrowed, String userInput, Scanner scnr) {
		if (borrowed.isEmpty()) {
			System.out.println("There's no books to return");
			return;
		}

		System.out.println("Enter book's ID to return");
		userInput = scnr.nextLine();

		for (int i = 0; i < borrowed.size(); i++) {
			if (userInput.equals(borrowed.get(i).getID())) {
				Book book = borrowed.get(i);
				borrowed.remove(i);
				library.add(book);
				System.out.println("Book returned to main library");
				return;
			} else if (i == borrowed.size() - 1) {
				System.out.println("Book not found");
				return;
			}
		}
	}

	public static void printMenu() {
		System.out.println(
				"1. Add Book 2. Return Book" + "\n3. Print All Books 4. Borrow Book" + "\n5. Search by Title 6. Exit");
	}

	public static void addBook(Scanner scnr, ArrayList<Book> library, ArrayList<Book> borrowed, String userInput) {

		String userID;
		String userTitle;
		String userAuthor;
		long userISBN;
		int userPageNum;

		while (true) {

			while (true) {
				System.out.println("Enter Book ID:");
				userID = scnr.nextLine();

				if (duplicateCheck(library, borrowed, userID)) {
					System.out.println("ID already exists");
					System.out.println("Try again\n");
				} else {
					break;
				}
			}

			System.out.println("Enter Book title:");
			userTitle = scnr.nextLine();

			System.out.println("Enter book author:");
			userAuthor = scnr.nextLine();

			while (true) {
				System.out.println("Enter book ISBN:");
				try {
					userISBN = Long.parseLong(scnr.nextLine());
					break;
				} catch (NumberFormatException nfe) {
					System.out.println("Enter a valid number");
				}
			}

			while (true) {
				System.out.println("Enter number of pages:");
				try {
					userPageNum = Integer.parseInt(scnr.nextLine());
					if (userPageNum <= 0) {
						System.out.println("Pages must be positive");
						continue;
					}
					break;
				} catch (NumberFormatException nfe) {
					System.out.println("Enter a valid number");
				}
			}

			library.add(new Book(userID, userTitle, userAuthor, userISBN, userPageNum));
			System.out.println(userTitle + " added to library!");

			System.out.println("Would you like to add more books? (y/n)");
			userInput = scnr.nextLine();

			if (userInput.equals("No") || userInput.equals("n")) {
				break;
			}
		}
	}

	public static boolean duplicateCheck(ArrayList<Book> library, ArrayList<Book> borrowed, String userID) {
		for (int i = 0; i < library.size(); i++) {
			if (userID.equals(library.get(i).getID())) {
				return true;
			}
		}
		for (int i = 0; i < borrowed.size(); i++) {
			if (userID.equals(borrowed.get(i).getID())) {
				return true;
			}
		}
		return false;
	}
}
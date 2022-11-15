package entity;

import java.util.Scanner;

public class Book_Shop_Main {
	public static void main(String[] args) {
		// test data
		Book book1 = new Book("bookname1", "author1", "description1", 201);
		Book book2 = new Book("bookname2", "author2", "description2", 202);
		Book book3 = new Book("bookname3", "author3", "description3", 203);
		Book book4 = new Book("bookname4", "author4", "description4", 204);
		Book book5 = new Book("bookname5", "author5", "description5", 205);
		Book newBooks1[] = { book1, book2 };
		Book favourite1[] = { book3 };
		Book completed1[] = { book4 };
		Book newBooks2[] = { book4, book2 };
		Book favourite2[] = { book3 };
		Book completed2[] = { book5 };
		User user1 = new User("nisha1", "nisha1@gmail.com", "password1", newBooks1, favourite1, completed1, "client");
		User user2 = new User("nisha2", "nisha2@gmail.com", "password2", newBooks2, favourite2, completed2, "client");
		User user3 = new User("admin1", "admin1@gmail.com", "adminpassword1", newBooks1, favourite1, completed1,
				"admin");
		User[] users = { user1, user2, user3 };
		Book[] books = { book1, book2, book3, book4, book5 };

		int option;
		String username = null;
		System.out.println("ONLINE BOOK SHOP");
		System.out.println("-----------------");
		showMenu(users, books, username);
	}

	public static void showMenu(User[] users, Book[] books, String username) {
		int option;

		// menu options
		do {
			System.out.println("Please select your option from the menu\n");
			System.out.println("-----------------");
			System.out.println("1.Register user");
			System.out.println("2.User login");
			System.out.println("3.Admin menu");
			System.out.println("4.See all my books for a user");
			System.out.println("5.Display all available books");
			System.out.println("6.Find book details with book-id");
			System.out.println("7.Add books by user to completed/favourite");
			System.out.println("8.Exit");
			System.out.println("---------------");
			Scanner scanner1 = new Scanner(System.in);
			String optionEntered = scanner1.nextLine();
			option = Integer.valueOf(optionEntered);

			if (option == 1 || option == 2 || option == 3 || option == 4 || option == 5 || option == 6 || option == 7
					|| option == 8) {
				switch (option) {
				case 1:
					users = registerUser(users);
					break;
				case 2:
					username = userLogin(users, books);
					break;
				case 3:
					books = admin(users, books);
					break;
				case 4:
					seeAllMyBooks(username, users, books);
					break;
				case 5:
					displayBooks(books);
					break;
				case 6:
					findBookById(books);
					break;
				case 7:
					addBooks(username, users, books);
					break;
				case 8:
					System.out.println("Exit");
					break;
				default:
					System.out.println("Unknown action\n");
					break;
				}
			} else {
				System.out.println("Error: Invalid input,Please Choose an option from the given menu\n");
			}

		} while ((option != 8));

	}

	// new user registration
	public static User[] registerUser(User[] users) {
		User user = new User();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter your username");
		String username = scanner.nextLine();
		user.setUserName(username);
		System.out.println("Please enter your email id");
		String emailid = scanner.nextLine();
		user.setEmailId(emailid);
		System.out.println("Please enter password");
		String password = scanner.nextLine();
		user.setPassword(password);
		user.setCompletedBooks(null);
		user.setNewBooks(null);
		user.getFavouriteBooks();
		System.out.println("User registered successfully");
		System.out.println(user.toString());
		//update users array with testdata which is already there + new user entered
		users = addUserToArray(users, user, users.length);
		return users;
	}

	public static User[] addUserToArray(User[] users, User user, int length) {
		User[] newarr = new User[length + 1];
		for (int i = 0; i < length; i++) {
			newarr[i] = users[i];
		}
		newarr[length] = user;

		return newarr;

	}

	// user login
	public static String userLogin(User[] users, Book[] books) {
		Boolean userfound = false;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter your username");
		String username = scanner.nextLine();
		System.out.println("Please enter password");
		String password = scanner.nextLine();

		for (int i = 0; i < users.length; i++) {
			if (username.equals(users[i].getUserName()) && password.equals(users[i].getPassword())) {
				System.out.println("You have logged in successfully");
				System.out.println("---------------");
				userfound = true;
				break;
			}
		}
		if (userfound == false) {
			System.out.println("User not found ,please register");
			showMenu(users, books, username);
		}
		return username;
	}

	// see all books of the current user
	public static void seeAllMyBooks(String username, User[] users, Book[] books) {
		if (username == null) {
			System.out.println("You have to login first to see you list of books");
		} else {
			for (int j = 0; j < users.length; j++) {
				String username_fromarray = users[j].getUserName();
				if (username.equals(username_fromarray)) {
					System.out.println("\nNewBooks for " + username);
					System.out.println("---------------");
					Book[] newbooks = users[j].getNewBooks();
					if (newbooks == null) {
						System.out.println("No new books in your list");
					} else {
						for (int i = 0; i < newbooks.length; i++) {
							System.out.println(newbooks[i].getBookName() + " " + newbooks[i].getAuthorName() + " "
									+ newbooks[i].getDescription() + " " + newbooks[i].getBookId());
						}
					}
					System.out.println("\nFavourite books for " + username);
					System.out.println("---------------");
					Book[] favbooks = users[j].getFavouriteBooks();
					if (favbooks == null) {
						System.out.println("No favourite books in your list");
					} else {
						for (int i = 0; i < favbooks.length; i++) {
							System.out.println(favbooks[i].getBookName() + " " + favbooks[i].getAuthorName() + " "
									+ favbooks[i].getDescription() + " " + favbooks[i].getBookId());
						}
					}
					System.out.println("\ncompleted books for " + username);
					System.out.println("---------------");
					Book[] completedbooks = users[j].getCompletedBooks();
					if (favbooks == null) {
						System.out.println("No completed books in your list");
					} else {
						for (int i = 0; i < completedbooks.length; i++) {
							System.out.println(completedbooks[i].getBookName() + " " + completedbooks[i].getAuthorName()
									+ " " + completedbooks[i].getDescription() + " " + completedbooks[i].getBookId());
						}
					}
				}
			}
		}
	}

	// display all books
	public static void displayBooks(Book[] books) {
		for (int i = 0; i < books.length; i++) {
			System.out.println(books[i].toString());
		}
	}
	// find book details with bookid

	public static void findBookById(Book[] books) {
		System.out.println("Enter the book id");
		Scanner scanner1 = new Scanner(System.in);
		int bookId = scanner1.nextInt();
		Boolean bookfound = false;
		for (int j = 0; j < books.length; j++) {
			if (bookId == books[j].getBookId()) {
				bookfound = true;
				System.out.println("Book found");
				System.out.println(books[j].toString());
				break;
			}
		}
		if (bookfound == false) {
			System.out.println("Sorry book not found with the given id!");
		}
	}

	// add books by user
	public static void addBooks(String username, User[] users, Book[] books) {
		for (int i = 0; i < users.length; i++) {
			if (username == null) {
				System.out.println("You have to login first to add books to your list");
				break;
			} else if (username.equals(users[i].getUserName())) {
				System.out.println("press 1 to add book to favourite books,or press 2 to add books to completed books");
				Scanner scanner1 = new Scanner(System.in);
				int addBookOption = scanner1.nextInt();
				if (addBookOption == 1) {
					Book[] favbooks = users[i].getFavouriteBooks();
					Scanner scanner = new Scanner(System.in);
					System.out.println("Please enter bookid");
					int bookid = scanner1.nextInt();
					Boolean bookfound = false;
					for (int j = 0; j < books.length; j++) {
						int favbooks_len;
						if (bookid == books[j].getBookId()) {
							bookfound = true;
							//if user already has favourite book list,add entered book to the list,else create new fav array
							if (favbooks == null) {
								favbooks_len = 0;
							} else {
								favbooks_len = favbooks.length;
							}
							//add entered book to the fav book array
							Book[] newfavBook = new Book[favbooks_len + 1];
							if (favbooks != null) {
								for (int k = 0; k < favbooks.length; k++) {
									newfavBook[k] = favbooks[k];
								}
							}
							newfavBook[favbooks_len] = books[j];
							users[i].setFavouriteBooks(newfavBook);
							System.out.println("book added to favourites");
							break;
						}
						if (bookfound = false) {
							System.out.println("No such book available with this book id,enter correct bookid");
						}

					}

				}
				if (addBookOption == 2) {
					Book[] completedbooks = users[i].getCompletedBooks();
					Scanner scanner = new Scanner(System.in);
					System.out.println("Please enter bookid");
					int bookid = scanner1.nextInt();
					Boolean bookfound = false;
					for (int j = 0; j < books.length; j++) {
						int completedbooks_len;
						if (bookid == books[j].getBookId()) {
							bookfound = true;
							if (completedbooks == null) {
								completedbooks_len = 0;
							} else {
								completedbooks_len = completedbooks.length;
							}
							Book[] newcompletedBook = new Book[completedbooks_len + 1];
							if (completedbooks != null) {
								for (int k = 0; k < completedbooks.length; k++) {
									newcompletedBook[k] = completedbooks[k];
								}
							}
							newcompletedBook[completedbooks_len] = books[j];
							users[i].setCompletedBooks(newcompletedBook);
							System.out.println("book added to completed");
							break;
						}
						if (bookfound = false) {
							System.out.println("No such book available with this book id,enter correct bookid");
						}

					}
				}
			}
		}
	}

	// admin menu
	public static Book[] admin(User[] users, Book[] books) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter your username");
		String username = scanner.nextLine();
		System.out.println("Please enter password");
		String password = scanner.nextLine();
		for (int i = 0; i < users.length; i++) {
			if (username.equals(users[i].getUserName()) && password.equals(users[i].getPassword())
					&& users[i].getUserType() == "admin") {
				System.out.println("You have logged in successfully as admin");
				System.out.println("press 1 to add book to new books,or press 2 to see all users");
				Scanner scanner1 = new Scanner(System.in);
				int adminOption = scanner1.nextInt();
				if (adminOption == 1) {
					Book book = new Book();
					System.out.println("Please enter bookname");
					String bookname = scanner.nextLine();
					book.setBookName(bookname);
					;
					System.out.println("Please book id");
					int bookid = scanner.nextInt();
					book.setBookId(bookid);
					System.out.println("Please enter author name");
					String author = scanner.nextLine();
					book.setAuthorName(author);
					System.out.println("Please enter description");
					String description = scanner.nextLine();
					book.setDescription(description);
					System.out.println("Book added successfully");
					System.out.println(book.toString());
					books = addBookToArray(books, book, books.length);

				}

				if (adminOption == 2) {
					for (int j = 0; j < users.length; j++) {
						System.out.println(users[j].toString());
					}
				}

			}
		}

		return books;
	}

	static Book[] addBookToArray(Book[] books, Book book, int length) {
		Book[] newarr = new Book[length + 1];
		for (int i = 0; i < length; i++) {
			newarr[i] = books[i];
		}
		newarr[length] = book;
		return newarr;

	}
}
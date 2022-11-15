package entity;

import java.util.Arrays;

public class User {
	private String userName;
	private String emailId;
	private String password;
	private Book[] newBooks;
	private Book[] favouriteBooks;
	private Book[] completedBooks;
	private String userType;
	
	//constructor 	
	public User(String userName, String emailId, String password, Book[] newBooks1,
			Book[] favourite1, Book[] completed1,String userType) {
		super();
		this.userName = userName;
		this.emailId = emailId;
		this.password = password;
		this.newBooks = newBooks1;
		this.favouriteBooks = favourite1;
		this.completedBooks = completed1;
		this.userType=userType;
	}

	
	public User() {
		// TODO Auto-generated constructor stub
	}


	//getters and setters	
    public String getUserName() {
		return userName;
	}	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Book[] getNewBooks() {
		return newBooks;
	}
	public void setNewBooks(Book[] newBooks) {
		this.newBooks = newBooks;
	}
	public Book[] getFavouriteBooks() {
		return favouriteBooks;
	}
	public void setFavouriteBooks(Book[] favouriteBooks) {
		this.favouriteBooks = favouriteBooks;
	}
	public Book[] getCompletedBooks() {
		return completedBooks;
	}
	public void setCompletedBooks(Book[] completedBooks) {
		this.completedBooks = completedBooks;
	}


	@Override
	public String toString() {
		return "User [userName=" + userName + ", emailId=" + emailId + ", password=" + password + ", newBooks="
				+ Arrays.toString(newBooks) + ", favouriteBooks=" + Arrays.toString(favouriteBooks)
				+ ", completedBooks=" + Arrays.toString(completedBooks) + ", userType=" + userType + "]";
	}

	

	

}

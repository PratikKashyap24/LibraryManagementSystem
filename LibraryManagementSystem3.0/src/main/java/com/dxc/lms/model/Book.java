package com.dxc.lms.model;

import java.time.LocalDate;

public class Book {
private int bookCode;
private String bookName;
private double price;
private LocalDate packageDate;

public Book() {
	//left_unimplemented
}

public Book(int bookCode, String bookName, double price, LocalDate packageDate) {
	super();
	this.bookCode = bookCode;
	this.bookName = bookName;
	this.price = price;
	this.packageDate = packageDate;
}

public int getBookCode() {
	return bookCode;
}

public void setBookCode(int bookCode) {
	this.bookCode = bookCode;
}

public String getBookName() {
	return bookName;
}

public void setBookName(String bookName) {
	this.bookName = bookName;
}

public double getPrice() {
	return price;
}

public void setPrice(double price) {
	this.price = price;
}

public LocalDate getPackageDate() {
	return packageDate;
}

public void setPackageDate(LocalDate packageDate) {
	this.packageDate = packageDate;
}



}

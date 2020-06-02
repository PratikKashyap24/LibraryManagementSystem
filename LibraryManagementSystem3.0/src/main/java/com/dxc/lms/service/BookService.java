package com.dxc.lms.service;

import java.util.List;

import com.dxc.lms.exception.LibraryException;
import com.dxc.lms.model.Book;

public interface BookService {
	void addBook(Book book) throws LibraryException;
	void deleteBook(int bookCode) throws LibraryException;
	List<Book>getAllBooks() throws LibraryException;
	Book getBookByBookCode(int bookCode) throws LibraryException;

}

package com.dxc.lms.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.dxc.lms.dao.BookDao;
import com.dxc.lms.dao.BookDaoJdbcImpl;
import com.dxc.lms.exception.LibraryException;
import com.dxc.lms.model.Book;

public class BookServiceImpl implements BookService {
	
private BookDao bookDao;

public BookServiceImpl() {
	this.bookDao=new BookDaoJdbcImpl();
}

private boolean isValidBookCode(int bookCode) throws LibraryException {
	
	return bookCode>0&&(bookDao.getBookByBookCode(bookCode)==null);
		
}

private boolean isValidBookName(String bookName) {
	return bookName!=null&&bookName.length()>5&&bookName.length()<20;
}

private boolean isValidPrice(double price) {
	
	return price>0;
	
} 
private boolean isvalidDate(LocalDate packageDate) {
	
	LocalDate today=LocalDate.now();
	return !packageDate.isAfter(today);
	
	
}

	private boolean isValidBook(Book book) throws LibraryException {
		
		boolean isValid=true;
		if(book==null) {
			isValid=false;
			throw new LibraryException("Book cannot be null !");
			
		}
		List<String> errMsgs=new ArrayList<String>();
		if(!isValidBookCode(book.getBookCode())) {
			errMsgs.add("Err: Bookcode cannot be zero or negative and can not be repetative");
		}
		if(!isValidBookName(book.getBookName())) {
			errMsgs.add("Err: Book Name can not be blank, and must be of 5 to 20 chars in length.");
		}
		if(!isValidPrice(book.getPrice())) {
			errMsgs.add("Err: Price can be zero or negative.");
			
		}
		if(!isvalidDate(book.getPackageDate())) {
			
			errMsgs.add("Err: Package Date can not be a future date.");
		}
		if(errMsgs.size()>0) {
			isValid=false;
			throw new LibraryException(errMsgs.toString());
		}
		
		return isValid;
		
		
	}
	
	
	
	public void addBook(Book book) throws LibraryException {
          if(isValidBook(book)) {
        	  bookDao.addBook(book);
          }
		
	}

	public void deleteBook(int bookCode) throws LibraryException {
       bookDao.deleteBook(bookCode);
		
		
	}

	public List<Book> getAllBooks() throws LibraryException {
		return bookDao.getAllBooks();
	}

	public Book getBookByBookCode(int bookCode) throws LibraryException {
		return bookDao.getBookByBookCode(bookCode);
	}

}

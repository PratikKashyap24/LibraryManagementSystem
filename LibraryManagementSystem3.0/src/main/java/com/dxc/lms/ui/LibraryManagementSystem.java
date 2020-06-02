package com.dxc.lms.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import com.dxc.lms.exception.LibraryException;
import com.dxc.lms.model.Book;
import com.dxc.lms.service.BookService;
import com.dxc.lms.service.BookServiceImpl;

public class LibraryManagementSystem {

	private static Scanner kbScanner;
	private static BookService bookService;
	private static final String DATE_FORMAT="dd-MM-yyyy";
	
	
	
	
	private static void doAddBook() {
		Book book=new Book();
		System.out.println("BOOK Code>>");
		while(!kbScanner.hasNextInt()) {
			System.out.println("A non fraction number expected");
			System.out.println("Book Code>>");
		}
		book.setBookCode(kbScanner.nextInt());
		System.out.print("BookNAme>> ");
		 book.setBookName(kbScanner.next());
		
		
		System.out.println("Price>>");
		while(!kbScanner.hasNextDouble()) {
			System.out.println("A fractional number expected ");
			System.out.println("Price>>");
		}
		book.setPrice(kbScanner.nextDouble());
		System.out.print("PackageDate(" + DATE_FORMAT + ")>> ");
		String pdStr = kbScanner.next();
		book.setPackageDate(LocalDate.parse(pdStr, DateTimeFormatter.ofPattern(DATE_FORMAT)));
		
		try {
			bookService.addBook(book);
			System.out.println("Books saved");
		} catch (LibraryException e) {
			System.out.println(e.getMessage());
		}
		
		
	}
	
	private static void doDisplayBooks() {
		
		try {
			List<Book> bookList=bookService.getAllBooks();
			if(bookList==null||bookList.size()==0) {
				System.out.println("No books record yet! try adding few!");
			}
			else {
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_FORMAT);
				
				System.out.println("bookCode\tbookName\tbookPrice\tpackageDate");
				System.out.println("===============================================================");
				for(Book book:bookList) {
					System.out.println(book.getBookCode()+"\t"+book.getBookName()+"\t"+book.getPrice()+"\t"+book.getPackageDate().format(dtf));
				}
				
				
				
			}
			
			
			
		} catch (LibraryException e) {
			System.out.println(e.getMessage());
		}
		
		
		
		
	}
	
	private static void doDeleteBook() {
		System.out.println("BookCode>>");
		while(!kbScanner.hasNextInt()) {
			System.out.println("A non fraction number is expected !");
			System.out.println("BookCode>>");
				
		}
		int bookCode=kbScanner.nextInt();
		
		try {
			bookService.deleteBook(bookCode);
			System.out.println("Book record got deleted !");
			
			
			
		} catch (LibraryException e) {
			System.out.println(e.getMessage());
		}
		
	}
	public static void main(String[] args) {
		
		kbScanner=new Scanner(System.in);
		
		bookService=new BookServiceImpl();
		 Menu menu=null;
		 while(menu !=Menu.QUIT) {
			 
			 System.out.println("choice \t menu");
			 for(Menu m:Menu.values()) {
				 
				 System.out.println((m.ordinal()+1)+"\t"+m);
			 }
			 
			 System.out.println("Choice>>");
			 int choice=kbScanner.nextInt();
			 int ordinal=choice-1;
			 if (ordinal >= 0 && ordinal < menu.values().length) {
					menu = Menu.values()[ordinal];
				} else {
					System.out.println("Invalid Choice");
					continue;
				}
			 switch (menu) {
				case ADD:
					doAddBook();
					break;
				case DELETE:
					doDeleteBook();
					break;
				case DISPLAY:
					doDisplayBooks();
					break;
				case QUIT:
					System.out.println("Application Closed");
					break;
				default:
					System.out.println("Invalid Chpice");
					break;
			 
			 
			 
			 
			 
			 
		 }
		
		
		
		
	}
	
	
	
	kbScanner.close();
}
}
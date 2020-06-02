package com.dxc.lms.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.dxc.lms.exception.LibraryException;
import com.dxc.lms.model.Book;

public class BookDaoJdbcImpl implements BookDao {
          
static {
	
try {
	Class.forName("com.mysql.jdbc.Driver");
} catch (ClassNotFoundException exp) {
	System.out.println(exp.getMessage());
	System.exit(0);
}	
}
	
private static final Logger logger=Logger.getLogger("BookDao");
private static final String dbUrl="jdbc:mysql://localhost:3307/dxc_batch";
private static final String uName="root";
private static final String uPass="admin";

private static final String INS_BOOK_QRY="insert into book_1(book_Code,book_Name,book_Price,book_PackageDate) values(?,?,?,?)";
private static final String DEL_BOOK_QRY="delete from book_1 where book_Code=?";
private static final String SEE_ALL_BOOKS="select book_Code,book_Name,book_Price,book_PackageDate from book_1";
private static final String SEE_BY_BCODE="select book_Code,book_Name,book_Price,book_PackageDate from book_1 where book_Code=?";


	
	

	public void addBook(Book book) throws LibraryException {

		if(book!=null) {
			
			try {
				Connection conn=DriverManager.getConnection(dbUrl,uName,uPass);
				PreparedStatement pinsert=conn.prepareStatement(INS_BOOK_QRY);
				
				pinsert.setInt(1, book.getBookCode());
				pinsert.setString(2, book.getBookName());
				pinsert.setDouble(3, book.getPrice());
				pinsert.setDate(4, Date.valueOf(book.getPackageDate()));
                  
				pinsert.executeUpdate();
				logger.info("Book added sucessfully !");
				
			} catch (SQLException e) {
				logger.error(e.toString());
             
				throw new LibraryException("Soory ! Add operation failed!");
				
				
			}
			
			
		}
		
		
		
	}

	public void deleteBook(int bookCode) throws LibraryException {
		try {
			Connection conn=DriverManager.getConnection(dbUrl,uName,uPass);
		    PreparedStatement pdel=conn.prepareStatement(DEL_BOOK_QRY);
		    pdel.setInt(1, bookCode);
		    pdel.executeUpdate();
		    
			
			logger.info("Delete operation sucessful !");
			
			
		} catch (SQLException exp) {
			    logger.error(exp.toString());
               throw new LibraryException("Delete operation failed !");
               
			
		}
        		
		

	}

	public List<Book> getAllBooks() throws LibraryException {
		List<Book> books=new ArrayList<Book>();
		 
		try {
			Connection conn=DriverManager.getConnection(dbUrl,uName,uPass);
			PreparedStatement pall=conn.prepareStatement(SEE_ALL_BOOKS);
			
			ResultSet rs=pall.executeQuery();
			
			while(rs.next()) {
				Book book=new Book();
				book.setBookCode(rs.getInt(1));
				book.setBookName(rs.getString(2));
				book.setPrice(rs.getDouble(3));
				book.setPackageDate(rs.getDate(4).toLocalDate());
				
				books.add(book);
				
			}
	
	      logger.info("Retrival operation sucessful !");		
			
			
			
			
			
			
		} catch (SQLException e) {
			logger.error(e.toString());
			
			throw new LibraryException("Could not retrieve data !");
		}
		
		
		
		
		
		
		
		return books;
	}

	public Book getBookByBookCode(int bookCode) throws LibraryException {
		Book book=null;
		try {
			Connection conn=DriverManager.getConnection(dbUrl,uName,uPass);
			PreparedStatement pcode=conn.prepareStatement(SEE_BY_BCODE);
			pcode.setInt(1,bookCode );
			ResultSet rs=pcode.executeQuery();
			
			if(rs.next()) {
				 book=new Book();
				book.setBookCode(rs.getInt(1));
				book.setBookName(rs.getString(2));
				book.setPrice(rs.getDouble(3));
				book.setPackageDate(rs.getDate(4).toLocalDate());
				
				}
			logger.info("Data retrival using book code Sucessful!");
			
			
			
		} catch (SQLException e) {
            logger.error(e.toString());
           throw new LibraryException("Sorry ! could not retieve data using book code");
		}
		
		
		
		
		return book;
		
	}

}

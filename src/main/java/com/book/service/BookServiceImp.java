package com.book.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.exception.ResourceNotFoundException;
import com.book.model.Book;



@Service
public class BookServiceImp implements IBookService {

	
	@Autowired
	IBookRepo bookRepo;

	@Override
	public Integer saveBook(Book book) {
		Book saveBook = bookRepo.save(book);
		return saveBook.getId();
	}

	@Override
	public List<Book> getAllBook() {
		return bookRepo.findAll();
	}

	@Override
	public List<Book> getBookByAuthor(String auther) {
		return bookRepo.findBookByAuthor(auther);
	}

	@Override
	public List<Book> getBookByCategory(String Category) {
		return bookRepo.findBookByCategory(Category);
	}

	@Override
	public List<Book> getBookByPrice(Integer Price) {
		return bookRepo.findBookByPrice(Price);
	}

	
	@Override
	public Book updateBook(Book book, Integer id) {
		Book existingBook= bookRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Book", "id", id));
		existingBook.setLogo(book.getLogo());
		existingBook.setTitle(book.getTitle());
		existingBook.setCategory(book.getCategory());
		existingBook.setPrice(book.getPrice());
		existingBook.setAuthor(book.getAuthor());
		existingBook.setPublisher(book.getPublisher());
		existingBook.setPublished_date(book.getPublished_date());
		existingBook.setChaptersOrContent(book.getChaptersOrContent());
		existingBook.setActive(book.getActive());
		existingBook.setReaderName(book.getReaderName());
		existingBook.setReaderEmailId(book.getReaderEmailId());
		Random rnd = new Random();
	    int number = rnd.nextInt(999999);
		existingBook.setPaymentNo(number);
		bookRepo.save(existingBook);	
		return existingBook;
		
	
	}

	
	@Override
	public void deleteBook(Integer Id) {
		bookRepo.deleteById(Id);
		
	}

	@Override
	public void deleteAllBook() {
		
		bookRepo.deleteAll();		
	}
}

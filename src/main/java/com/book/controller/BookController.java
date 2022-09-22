package com.book.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.book.model.Book;
import com.book.service.IBookService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class BookController {
	
	@Autowired
	IBookService bookService;
		
	@Autowired
	private RestTemplate restTemplate;
	
		

	@PostMapping("/saveBook")
	Integer AddBook(@RequestBody Book book)
	{
		Integer id =bookService.saveBook(book);
		System.out.println(id);
		return id;
	}

	@GetMapping("/allBook")
	List<Book> getAllBooks()
	{
		return bookService.getAllBook();
	}
	
	@GetMapping("/getBookByCategory/{Category}")
	public List<Book> getBookByCategory(@PathVariable String Category)
	{
		List<Book> books=bookService.getBookByCategory(Category);
		return books;
	}

	@GetMapping("/getBookByPrice/{Price}")
	public List<Book> getBookByPrice(@PathVariable Integer Price)
	{
		List<Book> books=bookService.getBookByPrice(Price);
		return books;
	}
	
	@GetMapping("/getBookByAuthor/{Author}")
	public List<Book> getBookByAuthor(@PathVariable String Author)
	{
		List<Book> books=bookService.getBookByAuthor(Author);
		return books;
	}

	@PutMapping("/updateBookById/{id}")
	public ResponseEntity<Book> updateBookById(@PathVariable("id") Integer id,@RequestBody Book book)
	{
		return new ResponseEntity<Book>(bookService.updateBook(book, id),HttpStatus.OK);
	}
	
	@DeleteMapping("/DeleteBookById/{id}")
	public ResponseEntity<Book> DeleteStudentById(@PathVariable Integer id)
	{
		System.out.println(id);
		ResponseEntity<Book> responseEntity=new ResponseEntity<>(HttpStatus.OK);
		try{
			bookService.deleteBook(id);
		}catch (Exception e) {
			e.printStackTrace();
			responseEntity =new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}

	@DeleteMapping("/DeleteAllBooks")
	public void deleteStudent()
	{
		bookService.deleteAllBook();
	}

	
}

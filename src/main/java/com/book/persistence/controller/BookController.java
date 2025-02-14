package com.book.persistence.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.book.persistence.controller.exception.BookIdMismatchException;
import com.book.persistence.controller.exception.BookNotFoundException;
import com.book.persistence.model.Book;
import com.book.persistence.repo.BookRepository;


@RestController
@RequestMapping("/api/books")
public class BookController {

	@Autowired
	private BookRepository bookRepository;
	
	
	@GetMapping
	public String test(){
		return "Hi there!,I am up!!";
	}
	
	@GetMapping("/all")
	public Iterable<Book> findAll(){
		return bookRepository.findAll();
	}
	
	@GetMapping("/title/{bookTitle}")
	public List<Book> findByTitle(@PathVariable String bookTitle) {
		return bookRepository.findByTitle(bookTitle);
	}
	
	@GetMapping("/{id}")
	public Book findOne(@PathVariable long id) {
		return bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Book create(@RequestBody Book book) {
		Book bk = bookRepository.save(book);
		return bk;
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
		bookRepository.deleteById(id);
	}
	
	public Book updateBook(@RequestBody Book book, @PathVariable long id) {
		if (book.getId() != id) {
			throw new BookIdMismatchException();
		}
		
		bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
		return bookRepository.save(book);
		
	}
	
	
	
	
}

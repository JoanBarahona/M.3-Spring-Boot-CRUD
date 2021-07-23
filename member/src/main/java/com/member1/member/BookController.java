package com.member1.member;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/book")

public class BookController {
	
	@Autowired
	BookRepository bookRepository;
	
	@RequestMapping("/allBooks")
	public String getAllBooks(Model boxToView) {
		
		boxToView.addAttribute("bookListfromControllerAndDB", bookRepository.findAll());
		
		return "books";
	}
	
	//delete
	
	@RequestMapping("/deleteBook")
	public String removeBook(int id, Model model) {
		
		// Sytem.out.println("inside removeBook" + id);
		Optional<Book> bookFound = findOneBookById(id);
		
		//System.out.println("find inside removeBook" + bookFound.get());
		
		if (bookFound.isPresent()) {
			
			bookRepository.deleteById(id);
			model.addAttribute("message", "done");
			model.addAttribute("bookDeleted", bookFound.get());
		}
		
		else {
			model.addAttribute("message", "error");
		}
		
		//System.out.println("finishing removeBook" + id);
		return "deletedbook.html";
	}
	
	@RequestMapping("/deleteAllBooks")
	public String deleteAllBooks () {
		
		bookRepository.deleteAll();
		
		return "redirect:/book/allBooks";
	}

	//add
	@RequestMapping("/newBook")
	public String newBook() {
		
		return "newbook.html";
	}
	
	@RequestMapping("/addBook")
	public String inserBook(Book book) {
		
		bookRepository.save(book);
		
		return "redirect:/book/allBooks";
	}
	
	//update
	
	@RequestMapping("/updateBook")
	public String updateBook(int id, Model model) {
		
		Optional<Book> bookFound = findOneBookById(id);
		
		if (bookFound.isPresent()) {
			
			model.addAttribute("bookfromController", bookFound.get());
			return "updatebook";
		}
		
		else
			return "notfound.html";
	}
	
	@PostMapping("/replaceBook/{idFromView}")
	public String replaceBook(@PathVariable("idFromView") int id, Book book) {
		
		Optional<Book>bookFound = findOneBookById(id);
		
		if (bookFound.isPresent()) {
			
			if (book.getAuthor() != null)
				 bookFound.get().setAuthor(book.getAuthor());
			if (book.getTitle() != null)
				 bookFound.get().setTitle(book.getTitle());
			if (book.getValue() != 0)
				 bookFound.get().setValue(book.getValue());
			
			
			
			
			bookRepository.save(bookFound.get());
			return "redirect:/book/allBooks";
			
			
		}else
			  return "notfound.html";
	}
	
	//detail
	
	@RequestMapping("/detailBook")
	public String detailBook(int id, Model model) {

		Optional<Book> bookFound = findOneBookById(id);

		if (bookFound.isPresent()) {

			model.addAttribute("bookfromController", bookFound.get());
			return "detailbook";
		}

		else
			return "notfound.html";
	}
	
	//---------*----SERVICE TO CONTROLLER-----*------//
	
	public Optional<Book> findOneBookById(int id) {
		
		//System.out.println("inside findBook" + id);
		Optional<Book> bookFound = bookRepository.findById(id);
		//System.out.println("finishing findBook" + id);
		//System.out.priontln("finishing findBook" + bookFound.get());
		return bookFound;
	}
}

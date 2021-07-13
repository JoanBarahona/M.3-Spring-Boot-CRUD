package com.member1.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

	@Controller
	@RequestMapping("/book")
	public class BookController {
		
		@Autowired
		BookRepository bookRepository;

		@RequestMapping("/allBooks")
		public String getAllBooks(Model boxToView) {
			
			boxToView.addAttribute("booksfromController", bookRepository.findAll() );
			
			return "books.html";
		}
		
		@RequestMapping("/newBook")
		public String newBook () {

			return "newbook.html";
		}

		@RequestMapping("/addBook")
		public String inserBook( @Validated Book book) {
			
			//System.out.println(expense);
			bookRepository.save(book);

			return "redirect:/book/allBooks";
		}

}

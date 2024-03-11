package com.example.ecom1.Controller;
// BookController.java - REST controller for managing books

import com.example.ecom1.Model.Book;
import com.example.ecom1.Service.BookService;
import com.example.ecom1.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    private MemberService memberService;

    @PostMapping("/add/book")
    public void addBook(@RequestBody Book book){
        bookService.addBook(book);
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }


}

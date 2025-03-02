package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.service.BookService;
import com.example.librarymanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;
    //public List<Book>getAllBooks
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }
    //public Book findBookById(Long id)
    @GetMapping("/{id}")
    public Book findBookById(@PathVariable Long id) {
        return bookService.findBookById(id);
    }
    //public Book createBook(Book book)
    @PostMapping
    public Book addbook(@RequestBody Book book) {
        return bookService.createBook(book);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        Book updatedBook = bookService.updateBook(id, book);
        if (updatedBook != null) {
            return ResponseEntity.ok(updatedBook);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    //public void deleteBook(Long id){
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
    }
    //public Book borrowBook(Long userId,Long bookId)
    @PostMapping("/{bookId}/borrow/{userId}")
    public ResponseEntity<Book> borrowBook(@PathVariable Long userId,@PathVariable Long bookId){
        Book borrowedBook=bookService.borrowBook(userId,bookId);
        if(borrowedBook!=null){
            return ResponseEntity.ok(borrowedBook);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }
    //public Book returnBook(Long bookId){
    @PostMapping("/{bookId}/return")
    public ResponseEntity<Book> returnBook(@PathVariable Long bookId){
        Book returnedBook=bookService.returnBook(bookId);
        if(returnedBook!=null){
            return ResponseEntity.ok(returnedBook);
        }else {
            return ResponseEntity.badRequest().build();
        }
    }
}

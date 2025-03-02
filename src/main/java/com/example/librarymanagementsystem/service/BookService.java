package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.model.User;
import com.example.librarymanagementsystem.repository.BookRepository;
import com.example.librarymanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Book>getAllBooks(){
        return bookRepository.findAll();
    }
    public Book findBookById(Long id){
        return bookRepository.findById(id).orElse(null);
    }
    public Book createBook(Book book){
        return bookRepository.save(book);
    }
    public Book updateBook(Long id, Book book) {
        Book existingBook = bookRepository.findById(id).orElse(null);
        if (existingBook != null) {
            existingBook.setTitle(book.getTitle());
            existingBook.setAuthor(book.getAuthor());
            return bookRepository.save(existingBook);
        }
        return null;
    }
    public void deleteBook(Long id){
        bookRepository.deleteById(id);
    }
    public Book borrowBook(Long userId,Long bookId){
        Book book = findBookById(bookId);
        User user=userRepository.findById(userId).orElse(null);
        if(user!= null && !book.isBorrowed() && book!=null){
            book.setBorrowedBy(user);
            book.setBorrowed(true);
            return bookRepository.save(book);
        }
            return null;
    }
    public Book returnBook(Long bookId){
        Book book = findBookById(bookId);
        if(book !=null && book.isBorrowed()){
            book.setBorrowedBy(null);
            book.setBorrowed(false);
            return bookRepository.save(book);
        }
        return null;
    }
}

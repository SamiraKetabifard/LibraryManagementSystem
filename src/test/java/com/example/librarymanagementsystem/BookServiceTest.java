package com.example.librarymanagementsystem;


import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.model.User;
import com.example.librarymanagementsystem.repository.BookRepository;
import com.example.librarymanagementsystem.repository.UserRepository;
import com.example.librarymanagementsystem.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private UserRepository userRepository;

    @Test
    void testGetAllBooks() {
        List<Book> books = List.of(new Book(1L, "Spring Boot", "John Doe", false, null));
        when(bookRepository.findAll()).thenReturn(books);

        List<Book> result = bookService.getAllBooks();

        assertEquals(1, result.size());
        assertEquals("Spring Boot", result.get(0).getTitle());
    }

    @Test
    void testFindBookById() {
        Book book = new Book(1L, "Spring Boot", "John Doe", false, null);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        Book result = bookService.findBookById(1L);

        assertNotNull(result);
        assertEquals("Spring Boot", result.getTitle());
    }

    @Test
    void testCreateBook() {
        Book book = new Book(1L, "Spring Boot", "John Doe", false, null);
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Book result = bookService.createBook(book);

        assertNotNull(result);
        assertEquals("Spring Boot", result.getTitle());
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void testUpdateBook() {
        Book existingBook = new Book(1L, "Old Title", "Old Author", false, null);
        Book updatedBook = new Book(1L, "New Title", "New Author", false, null);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(existingBook));
        when(bookRepository.save(any(Book.class))).thenReturn(updatedBook);

        Book result = bookService.updateBook(1L, updatedBook);

        assertNotNull(result);
        assertEquals("New Title", result.getTitle());
        assertEquals("New Author", result.getAuthor());
    }

    @Test
    void testDeleteBook() {
        doNothing().when(bookRepository).deleteById(1L);

        bookService.deleteBook(1L);

        verify(bookRepository, times(1)).deleteById(1L);
    }

    @Test
    void testBorrowBook() {
        User user = new User(1L, "Alice");
        Book book = new Book(1L, "Spring Boot", "John Doe", false, null);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Book result = bookService.borrowBook(1L, 1L);

        assertNotNull(result);
        assertEquals(user, result.getBorrowedBy());
        assertTrue(result.isBorrowed());
    }

    @Test
    void testReturnBook() {
        User user = new User(1L, "Alice");
        Book book = new Book(1L, "Spring Boot", "John Doe", true, user);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Book result = bookService.returnBook(1L);

        assertNotNull(result);
        assertNull(result.getBorrowedBy());
        assertFalse(result.isBorrowed());
    }
}

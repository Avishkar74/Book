package com.Avishkar.libraryManagementSystem.service;

import com.Avishkar.libraryManagementSystem.dto.BookRequest;
import com.Avishkar.libraryManagementSystem.entity.Book;
import com.Avishkar.libraryManagementSystem.repository.BookRepository;
import java.util.List;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @CacheEvict(value = {"booksAll", "booksSearch"}, allEntries = true)
    public Book addBook(BookRequest request) {
        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setIsbn(request.getIsbn());
        book.setPublisher(request.getPublisher());
        book.setTotalCopies(request.getTotalCopies());
        book.setAvailableCopies(request.getTotalCopies());
        return bookRepository.save(book);
    }

    @Cacheable(value = "booksAll")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Cacheable(value = "books", key = "#id")
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
    }

    @CachePut(value = "books", key = "#id")
    @CacheEvict(value = {"booksAll", "booksSearch"}, allEntries = true)
    public Book updateBook(Long id, BookRequest request) {
        Book existing = getBookById(id);
        existing.setTitle(request.getTitle());
        existing.setAuthor(request.getAuthor());
        existing.setIsbn(request.getIsbn());
        existing.setPublisher(request.getPublisher());
        existing.setTotalCopies(request.getTotalCopies());

        // Business rule: when book updated, reset availableCopies to totalCopies
        existing.setAvailableCopies(request.getTotalCopies());

        return bookRepository.save(existing);
    }

    @CacheEvict(value = {"books", "booksAll", "booksSearch"}, allEntries = true)
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        }
        bookRepository.deleteById(id);
    }

    @Cacheable(value = "booksSearch", key = "#title + '::' + #author")
    public List<Book> searchBooks(String title, String author) {
        boolean hasTitle = title != null && !title.isBlank();
        boolean hasAuthor = author != null && !author.isBlank();

        if (hasTitle && hasAuthor) {
            return bookRepository.findByTitleContainingIgnoreCaseAndAuthorContainingIgnoreCase(title, author);
        } else if (hasTitle) {
            return bookRepository.findByTitleContainingIgnoreCase(title);
        } else if (hasAuthor) {
            return bookRepository.findByAuthorContainingIgnoreCase(author);
        } else {
            return bookRepository.findAll();
        }
    }
}

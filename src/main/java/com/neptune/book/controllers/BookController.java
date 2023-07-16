package com.neptune.book.controllers;

import com.neptune.book.models.Book;
import com.neptune.book.models.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;

        this.bookRepository.saveAll(List.of(
                new Book("Mockingbird", "Harper Lee", "Fiction"),
                new Book("The Great Gatsby", "F. Scott Fitzgerald", "Fiction"),
                new Book("Pride and Prejudice", "Jane Austen", "Fiction")
        ));

    }

    @GetMapping("/")
    Iterable<Book> getBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    Optional<Book> getBookById(@PathVariable String id) {
        for (Book b : bookRepository.findAll()) {
            if (b.getId().equals(id)) {
                return Optional.of(b);
            }
        }
        return Optional.empty();
    }

    @GetMapping("/title/{title}")
    Optional<Book> getBookByTitle(@PathVariable String title) {
        for (Book b : bookRepository.findAll()) {
            if (b.getTitle().equals(title)) {
                return Optional.of(b);
            }
        }
        return Optional.empty();
    }

    @GetMapping("/author/{author}")
    Optional<Book> getBookByAuthor(@PathVariable String author) {
        for (Book b : bookRepository.findAll()) {
            if (b.getAuthor().equals(author)) {
                return Optional.of(b);
            }
        }
        return Optional.empty();
    }

    @GetMapping("/genre/{genre}")
    Optional<Book> getBookByGenre(@PathVariable String genre) {
        for (Book b : bookRepository.findAll()) {
            if (b.getGenre().equals(genre)) {
                return Optional.of(b);
            }
        }
        return Optional.empty();
    }

    @PostMapping("/")
    Book postBook(@RequestBody Book book) {
        bookRepository.save(book);
        return book;
    }

    @PutMapping("/{id}")
    ResponseEntity<Book> putBook(@PathVariable String id, @RequestBody Book book) {
        // OK - Return HTTP STATUS 200
        return (!bookRepository.existsById(id))
                ? new ResponseEntity<>(bookRepository.save(book), HttpStatus.CREATED)
                : new ResponseEntity<>(bookRepository.save(book), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteBook(@PathVariable String id) {
        bookRepository.deleteById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}

package com.neptune.book.controllers;

import com.neptune.book.models.Book;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    List<Book> bookList = new ArrayList<>();
    public BookController() {
        bookList.addAll(List.of(
                new Book("Mockingbird", "Harper Lee", "Fiction"),
                new Book("The Great Gatsby", "F. Scott Fitzgerald", "Fiction"),
                new Book("Pride and Prejudice", "Jane Austen", "Fiction")
        ));
    }

    @GetMapping("/books")
    Iterable<Book> getBooks() {
        return bookList;
    }

    @GetMapping("/books/{id}")
    Optional<Book> getBookById(@PathVariable String id) {
        for (Book b : bookList) {
            if (b.getId().equals(id)) {
                return Optional.of(b);
            }
        }
        return Optional.empty();
    }

    @GetMapping("/books/title/{title}")
    Optional<Book> getBookByTitle(@PathVariable String title) {
        for (Book b : bookList) {
            if (b.getTitle().equals(title)) {
                return Optional.of(b);
            }
        }
        return Optional.empty();
    }

    @GetMapping("/books/author/{author}")
    Optional<Book> getBookByAuthor(@PathVariable String author) {
        for (Book b : bookList) {
            if (b.getAuthor().equals(author)) {
                return Optional.of(b);
            }
        }
        return Optional.empty();
    }

    @GetMapping("/books/genre/{genre}")
    Optional<Book> getBookByGenre(@PathVariable String genre) {
        for (Book b : bookList) {
            if (b.getGenre().equals(genre)) {
                return Optional.of(b);
            }
        }
        return Optional.empty();
    }

    @PostMapping("/books")
    Book postBook(@RequestBody Book book) {
        bookList.add(book);
        return book;
    }

    @PutMapping("/books/{id}")
    Book putBook(@PathVariable String id, @RequestBody Book book) {
        int bookIdx = -1;
        for (Book b: bookList) {
            if (b.getId().equals(id)) {
                bookIdx = bookList.indexOf(b);
                bookList.set(bookIdx, book);
            }
        }
        // if there is no id in the store this return will create new book object
        return (bookIdx == -1) ? postBook(book) : book;
    }

    @DeleteMapping("/books/{id}")
    void deleteBook(@PathVariable String id) {
        // Remove with Lambda Expression
        bookList.removeIf(book -> book.getId().equals(id));


        // Remove with For Each
        /*
        int bookIdx = -1;
        for (Book b : bookList) {
            if (b.getId().equals(id)) {
                bookIdx = bookList.indexOf(c);
                bookList.remove(bookIdx);
            }
        }
         */
    }
}

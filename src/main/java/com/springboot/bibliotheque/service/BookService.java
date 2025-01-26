package com.springboot.bibliotheque.service;

import com.springboot.bibliotheque.entity.Book;
import com.springboot.bibliotheque.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@Validated
public class BookService {
    public final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void addBook(Book book){
        bookRepository.save(book);
    }

    public void updateBook(Long id, Book book){
        Optional<Book> optionalBook = bookRepository.findById(id);

        if (optionalBook.isPresent()) {

            Book bookUpdate = optionalBook.get();
            bookUpdate.setTitle(book.getTitle());
            bookUpdate.setAuthor(book.getAuthor());

            bookRepository.save(bookUpdate);
        }

    }

    public void deleteBook(Long id){
        Optional<Book> existingBook = bookRepository.findById(id);
        existingBook.ifPresent(bookRepository::delete);
    }

    public List<Book> findBooklist(boolean isAvailable){
        return bookRepository.findByIsAvailable(isAvailable);
    }

    public Book getBook(Long id){
        Optional<Book> book = bookRepository.findById(id);
        return  book.get();
    }

}

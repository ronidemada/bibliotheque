package com.springboot.bibliotheque.controller;

import com.springboot.bibliotheque.entity.Book;
import com.springboot.bibliotheque.exception.BookNotFoundException;
import com.springboot.bibliotheque.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    public final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/")
    public ResponseEntity<String> createBook(@Valid @RequestBody Book book, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(bindingResult.getFieldError().getDefaultMessage());
        }
        bookService.addBook(book);
        return ResponseEntity.ok("created book");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBook(@PathVariable Long id, @Valid @RequestBody Book book, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(bindingResult.getFieldError().getDefaultMessage());
        }
        bookService.updateBook(id, book);
        return ResponseEntity.ok("updated book");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id){
        try {
            bookService.deleteBook(id);
            return ResponseEntity.ok("deleted book");
        }catch (Exception exception){
            throw new BookNotFoundException(id);
        }
    }

    @GetMapping("/bookList")
    public ResponseEntity<List<Book>> getAllBook(@RequestParam boolean isAvailable){
        return ResponseEntity.ok(bookService.findBooklist(isAvailable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(Long id){
        try {
            return ResponseEntity.ok(bookService.getBook(id));
        }catch (Exception exception){
            throw new BookNotFoundException(id);
        }
    }
}

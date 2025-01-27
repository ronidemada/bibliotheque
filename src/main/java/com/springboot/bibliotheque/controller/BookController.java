package com.springboot.bibliotheque.controller;

import com.springboot.bibliotheque.entity.Book;
import com.springboot.bibliotheque.entity.User;
import com.springboot.bibliotheque.exception.BookNotFoundException;
import com.springboot.bibliotheque.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/book")
public class BookController {

    public final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("")
    public ResponseEntity<String> createBook(@Valid @RequestBody Book book, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        bookService.addBook(book);
        return ResponseEntity.ok("created book");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateBook(@PathVariable Long id, @Valid @RequestBody Book book, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
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

    @GetMapping("/booklist")
    public ResponseEntity<List<Book>> getAllBook(@RequestParam boolean available){
        return ResponseEntity.ok(bookService.findBooklist(available));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id){
        try {
            return ResponseEntity.ok(bookService.getBook(id));
        }catch (Exception exception){
            throw new BookNotFoundException(id);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Book>> getAllBook(){
        return ResponseEntity.ok(bookService.getAllBook());
    }
}

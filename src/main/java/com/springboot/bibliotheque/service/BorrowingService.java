package com.springboot.bibliotheque.service;

import com.springboot.bibliotheque.entity.Book;
import com.springboot.bibliotheque.entity.Borrowing;
import com.springboot.bibliotheque.entity.User;
import com.springboot.bibliotheque.repository.BookRepository;
import com.springboot.bibliotheque.repository.BorrowingRepository;
import com.springboot.bibliotheque.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BorrowingService {

    public final BorrowingRepository borrowingRepository;
    public final UserRepository userRepository;
    public final BookRepository bookRepository;

    public BorrowingService(BorrowingRepository borrowingRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.borrowingRepository = borrowingRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public void borrow(Long userId, Long bookId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Borrowing borrowing = new Borrowing();
        borrowing.setUser(user);

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if(!book.isAvailable()){
            throw new RuntimeException("BookUnavailableException : Book already borrow");
        }

        borrowing.setBook(book);
        borrowing.setBorrowDate(LocalDate.now());
        book.setAvailable(false);
        borrowing.setReturnDate(null);
        borrowingRepository.save(borrowing);
    }

    public void returnBorrow(Long borrowingId) {
        Borrowing borrowing = borrowingRepository.findById(borrowingId)
                .orElseThrow(() -> new RuntimeException("Borrowing not found"));
        borrowing.setReturnDate(LocalDate.now());
        Book book = bookRepository.findById(borrowing.getBook().getId())
                .orElseThrow(() -> new RuntimeException("BookId in returnBorrow not found"));
        book.setAvailable(true);
        borrowingRepository.save(borrowing);
    }
}

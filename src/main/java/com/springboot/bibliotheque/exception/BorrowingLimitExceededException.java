package com.springboot.bibliotheque.exception;

public class BorrowingLimitExceededException extends RuntimeException{
    public BorrowingLimitExceededException() {
        super ("Limit borrow : User have already 3 books !");
    }
}

package com.bookkeeping.borrow

import com.bookkeeping.book.Book
import com.bookkeeping.security.User

import java.time.LocalDateTime

class Borrow {

    String transactionId

    LocalDateTime dateBorrowed
    LocalDateTime dateReturned

    Book book
    User borrower
    User processor

    Status status
    Type type

    LocalDateTime dateCreated
    LocalDateTime lastUpdated

    static mapping = {
        status enumType: 'string'
        type enumType: 'string'
        transactionId index: 'transaction_Idx'
    }

    static constraints = {
        transactionId nullable: false
        dateBorrowed nullable: false
        dateReturned nullable: true
        book nullable: false
        borrower nullable: false
        processor nullable: false
        status nullable: false
        type nullable: false
    }

    enum Status {
        OUT("Out", "is-warning"),
        RETURNED("Returned", "is-success")

        String text
        String color

        Status(String text, String color) {
            this.text = text
            this.color = color
        }
    }

    enum Type {
        SELF("Self checkout"),
        LIBRARY("Library")

        String text

        Type(String text) {
            this.text = text
        }
    }
}

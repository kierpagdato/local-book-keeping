package com.bookkeeping

class Borrow {


    Date dateBorrowed
    Date dateReturned

    Book book
    User borrower
    User processor

    static constraints = {
        dateBorrowed nullable: false
    }
}

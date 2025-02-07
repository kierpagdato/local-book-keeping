package com.bookkeeping

import com.bookkeeping.security.User

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

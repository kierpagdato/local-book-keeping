package com.bookkeeping.borrow

import com.bookkeeping.book.Book
import com.bookkeeping.book.BookDaoService
import com.bookkeeping.security.User
import grails.gorm.transactions.Transactional

import java.time.LocalDateTime

@Transactional
class BorrowService {

    BookDaoService bookDaoService
    BorrowDaoService borrowDaoService

    Borrow selfService(Book book, User user) {

        Borrow borrow = new Borrow(book: book, borrower: user, processor: user, dateBorrowed: LocalDateTime.now(),
                status: Borrow.Status.OUT, type: Borrow.Type.SELF)
        borrowDaoService.save(borrow)

        book.status = Book.Status.OUT
        bookDaoService.save(book)

        return borrow
    }
}

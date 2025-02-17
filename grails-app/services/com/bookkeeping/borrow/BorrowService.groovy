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

    String selfService(List<Book> bookList, User user, boolean isSelf) {

        Set<Borrow> borrowList = new HashSet<>()

        String transactionId = UUID.randomUUID().toString()

        for(Book book : bookList) {
            borrowList.add(new Borrow(transactionId: transactionId, book: book,
                    borrower: user, processor: user, dateBorrowed: LocalDateTime.now(),
                    status: Borrow.Status.OUT, type: isSelf? Borrow.Type.SELF : Borrow.Type.LIBRARY))
            book.status = Book.Status.OUT
        }

        borrowDaoService.saveAll(borrowList)

        bookDaoService.saveAll(bookList)

        return transactionId
    }

    void returnBorrow(String transactionId, User user) {
        List<Borrow> borrowList = borrowDaoService.listByTransactionId(transactionId)
        for(Borrow b : borrowList) {
            b.dateReturned = LocalDateTime.now()
            b.processor = user
            b.status = Borrow.Status.RETURNED

            b.book.status = Book.Status.SHELVED
        }

        borrowDaoService.saveAll(borrowList)

    }
}

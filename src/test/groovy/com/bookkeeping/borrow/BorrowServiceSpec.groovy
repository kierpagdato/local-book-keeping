package com.bookkeeping.borrow

import com.bookkeeping.book.Book
import com.bookkeeping.book.BookDaoService
import com.bookkeeping.security.User
import grails.testing.gorm.DataTest
import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

import java.time.LocalDateTime

class BorrowServiceSpec extends Specification implements ServiceUnitTest<BorrowService>, DataTest {

    Class<?>[] getDomainClassesToMock() {
        return [User, Book] as Class[]
    }

    def setup() {
        service.bookDaoService = Mock(BookDaoService)
        service.borrowDaoService = Mock(BorrowDaoService)
    }

    def cleanup() {
    }

    void "test self service"() {
        given:"Book list and user"
            List<Book> bookList = [
                    new Book(title: 'Sesame Street', author: 'John Doe', isbn: '111111111',
                            status: Book.Status.SHELVED, description: 'Sesame street book'),
                    new Book(title: 'Sesame Street S2', author: 'John Doe', isbn: '111111112',
                            status: Book.Status.SHELVED, description: 'Sesame street book season 2'),
            ]
            User user = new User(username: 'johndoe', password: '123123',
                    email: 'johndoe@gmail.com', firstName: 'John', lastName: 'Doe')

        when:"Execute return"
            String transactionId = service.selfService(bookList, user, false)

        then:"Method services"
            1 * service.borrowDaoService.saveAll(_)
            1 * service.bookDaoService.saveAll(_)
            transactionId
    }

    void "test return"() {
        given:"Transaction ID and user"
            String transactionId = "123456789"
            User user = new User(username: 'johndoe', password: '123123',
                    email: 'johndoe@gmail.com', firstName: 'John', lastName: 'Doe')
        when:"Execute return"
            service.returnBorrow(transactionId, user)

        then:"Method services"
            1 * service.borrowDaoService.listByTransactionId(_) >> [
                    new Borrow(transactionId: '111', dateBorrowed: LocalDateTime.now(),
                            book: new Book(title: 'Sesame Street', author: 'John Doe', isbn: '111111111',
                                    status: Book.Status.OUT, description: 'Sesame street book'),
                            borrower: new User(username: 'johndoe', password: '123123',
                                    email: 'johndoe@gmail.com', firstName: 'John', lastName: 'Doe'),
                            processor: new User(username: 'johndoe', password: '123123',
                                    email: 'johndoe@gmail.com', firstName: 'John', lastName: 'Doe'),
                            status: Borrow.Status.OUT,
                            type: Borrow.Type.SELF
                    )
            ]

            1 * service.borrowDaoService.saveAll(_)
    }
}

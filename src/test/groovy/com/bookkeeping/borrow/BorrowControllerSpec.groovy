package com.bookkeeping.borrow

import com.bookkeeping.book.Book
import com.bookkeeping.book.BookDaoService
import com.bookkeeping.config.UserConfigService
import com.bookkeeping.my_user.MyUserDetails
import com.bookkeeping.security.User
import grails.plugin.springsecurity.SpringSecurityService
import grails.testing.gorm.DomainUnitTest
import grails.testing.web.controllers.ControllerUnitTest
import org.springframework.security.core.authority.SimpleGrantedAuthority
import spock.lang.Specification

import java.time.LocalDateTime

class BorrowControllerSpec extends Specification implements ControllerUnitTest<BorrowController>, DomainUnitTest<Borrow> {

    def setup() {
        User user = new User(username: 'johndoe', password: '123123',
                email: 'johndoe@gmail.com', firstName: 'John', lastName: 'Doe',
                enabled: true, accountExpired: false,
                passwordExpired: false, accountLocked: false,)
        user.id = 1

        List<SimpleGrantedAuthority> authorities = [
                new SimpleGrantedAuthority("ROLE_USER")
        ]

        controller.borrowDaoService = Mock(BorrowDaoService)
        controller.borrowService = Mock(BorrowService)
        controller.bookDaoService = Mock(BookDaoService)
        controller.springSecurityService = Mock(SpringSecurityService) {
            currentUser >> user
            principal >> new MyUserDetails(user.username, user.password, user.enabled,
                    !user.accountExpired, !user.passwordExpired,
                    !user.accountLocked, authorities, user.id,
                    user.email, user.firstName, user.lastName)
            }
        controller.userConfigService = Mock(UserConfigService) {
            userBorrowLimit >> 5
        }
    }

    void "Test the get receipts"() {
        given: "Receipt lists"

            List<Borrow> borrowList = [
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

        when: "Call the get receipts"
            request.method = 'GET'
            controller.receipt('111')

        then: "A redirect is issued to the show action"
            1 * controller.borrowDaoService.listByTransactionId(_) >> borrowList
            model.list.size() == 1
    }

    void "Test the get basket"() {
        when: "Call the get basket"
            request.method = 'GET'
            controller.basket()

        then: "Render basket view"
            1 * controller.bookDaoService.listByIds(_) >> [
                    new Book(title: 'Sesame Street', author: 'John Doe', isbn: '111111111',
                            status: Book.Status.SHELVED, description: 'Sesame street book'),
                    new Book(title: 'Sesame Street S2', author: 'John Doe', isbn: '111111112',
                            status: Book.Status.SHELVED, description: 'Sesame street book season 2'),
            ]

            model.list.size() == 2
            model.userList.size() == 1
            view == '/borrow/basket'
    }

    void "Test add to basket"() {
        given: "Selected books"
            params.selected = (String[]) ['1', '2', '3']

        when: "Add to session basket"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            controller.addToBasket()

        then: "Redirected back to book list"
            response.redirectedUrl == '/'
    }

    void "Test clear basket"() {
        when: "clear session basket"
            request.method = 'POST'
            controller.clearBasket()

        then: "Redirected back to book list"
            response.redirectedUrl == '/'
    }

    void "Test checkout"() {
        given: "Set session"
            BorrowBasket basket = new BorrowBasket()
            basket.addToBasket((String[])["1", "2"])

            controller.session[BorrowBasket.SESSION_KEY] = basket

            params.user = "1"

        when: "Call check out"
            request.method = 'POST'
            controller.checkOut()

        then: "Redirected back to book list"
            1 * controller.borrowDaoService.countUserBorrow(_) >> 1
            1 * controller.bookDaoService.listByIdsAndStatus(_, _) >> [
                    new Book(title: 'Sesame Street', author: 'John Doe', isbn: '111111111',
                            status: Book.Status.SHELVED, description: 'Sesame street book'),
                    new Book(title: 'Sesame Street S2', author: 'John Doe', isbn: '111111112',
                            status: Book.Status.SHELVED, description: 'Sesame street book season 2'),
            ]
            1 * controller.borrowService.selfService(_, _, _) >> "123456789"
            response.redirectedUrl == '/borrow/receipt/123456789'
    }

    void "Test checkout empty book list"() {
        given: "Set session"
            BorrowBasket basket = new BorrowBasket()

            controller.session[BorrowBasket.SESSION_KEY] = basket

            params.user = "1"

        when: "Call check out"
            request.method = 'POST'
            controller.checkOut()

        then: "Redirected back to book list"
            response.redirectedUrl == '/'
    }

    void "Test checkout exceeding borrowed books"() {
        given: "Set session"
            BorrowBasket basket = new BorrowBasket()
            basket.addToBasket((String[])["1", "2"])
            controller.session[BorrowBasket.SESSION_KEY] = basket

            params.user = "1"

        when: "Call check out"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            controller.checkOut()

        then: "Redirected back to borrow list"
            1 * controller.borrowDaoService.countUserBorrow(_) >> 5
            response.redirectedUrl == '/borrow/index'
    }

    void "Test return books"() {
        when: "Add to session basket"
            request.method = 'POST'
            controller.returnBorrow("11111")

        then: "Redirected back to book list"
            1 * controller.borrowService.returnBorrow(_, _)
            response.redirectedUrl == '/'
    }

}

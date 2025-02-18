package com.bookkeeping.borrow


import com.bookkeeping.book.Book
import com.bookkeeping.book.BookDaoService
import com.bookkeeping.config.UserConfigService
import com.bookkeeping.my_user.MyUserDetails
import com.bookkeeping.security.User
import com.bookkeeping.security.UserDaoService
import com.bookkeeping.utils.SessionUtils
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import org.apache.commons.collections.CollectionUtils

import static org.springframework.http.HttpStatus.BAD_REQUEST
import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND

@Secured(['IS_AUTHENTICATED_FULLY'])
class BorrowController {

    UserDaoService userDaoService
    BookDaoService bookDaoService
    BorrowDaoService borrowDaoService
    BorrowService borrowService
    UserConfigService userConfigService

    SpringSecurityService springSecurityService

    static allowedMethods = [selfCheckout: "POST", addToBasket: "POST", update: "PUT", delete: "DELETE"]

    def index() {

        println "borrow index start " + System.currentTimeMillis()
        params.max = params?.max?: 5
        params.offset = params?.offset?: 0

        MyUserDetails myUserDetails = (MyUserDetails) springSecurityService.principal

        List<Borrow> borrowList = []
        Long count = 0
        if(myUserDetails.hasAuthority("ROLE_LIBRARIAN")) {
            borrowList = borrowDaoService.listDistinct(params, null)
            count = borrowDaoService.countDistinct(null)
        } else {
            borrowList = borrowDaoService.listDistinct(params, myUserDetails.id)
            count = borrowDaoService.countDistinct(myUserDetails.id)
        }

        println "borrow index end " + System.currentTimeMillis()
        [list: borrowList,
        count: count]
    }

    def receipt(String id) {
        List<Borrow> borrowList = borrowDaoService.listByTransactionId(id)
        [list: borrowList]
    }

    def basket() {

        MyUserDetails myUserDetails = (MyUserDetails) springSecurityService.principal

        List<User> userList = [springSecurityService.currentUser]

        if(myUserDetails.hasAuthority("ROLE_LIBRARIAN")) {
            userList = User.list()
        }

        BorrowBasket basket = SessionUtils.getBorrowBasket(session);
        List<Book> bookList = bookDaoService.listByIds(basket.bookIds)
        [list: bookList,
        userList: userList]
    }

    def addToBasket() {

        boolean isAdded = false

        if(params?.selected) {
            SessionUtils.getBorrowBasket(session)?.addToBasket(params.selected)
            isAdded = true
        }

        request.withFormat {
            form multipartForm {
                flash.message = isAdded? 'Books added to basket.' : ''
                redirect controller: 'book', action: 'index', params: params?.selected
            }
            '*' { respond user, [status: CREATED] }
        }
    }

    def checkOut() {

        BorrowBasket basket = SessionUtils.getBorrowBasket(session)

        //Return to book list if basket is empty
        if(CollectionUtils.isEmpty(basket.bookIds)) {
            redirect controller: 'book', action: 'index'
            return
        }

        MyUserDetails myUserDetails = (MyUserDetails) springSecurityService.principal
        User selectedUser = springSecurityService.currentUser as User

        boolean isCurrentId = isCurrentUserId(selectedUser.id, params.user)

        //Use ROLE_LIBRARIAN == false as validation for self checkout.
        //User can be assigned both LIBRARIAN and USER
        //It's safer to check the user doesn't have ROLE_LIBRARIAN
        //ROLE_USER cannot checkOut for other user
        if(!myUserDetails.hasAuthority("ROLE_LIBRARIAN") && !isCurrentId) {
            badRequest()
            return
        }

        if(!isCurrentId) {
            selectedUser = userDaoService.get(params.user)
        }

        //Check user borrow limit
        //Max value is set in config
        int userBorrowCount = borrowDaoService.countUserBorrow(selectedUser.id)
        if(userBorrowCount + basket.bookIds.size() > userConfigService.userBorrowLimit) {
            badRequestUserLimit()
            return
        }

        List<Book> bookList = bookDaoService.listByIdsAndStatus(basket.bookIds, Book.Status.SHELVED)

        String transactionId
        if(CollectionUtils.isNotEmpty(bookList)) {
            transactionId = borrowService.selfService(bookList, selectedUser, isCurrentId)
            basket.bookIds.clear()
        }

        request.withFormat {
            form multipartForm {
                flash.message = 'Check out successful.'
                if(transactionId) {
                    redirect controller: 'borrow', action: 'receipt', id: transactionId
                } else {
                    redirect controller: 'book', action: 'index'
                }
            }
            '*' { respond user, [status: CREATED] }
        }
    }

    def clearBasket() {

        BorrowBasket basket = SessionUtils.getBorrowBasket(session)

        basket.bookIds.clear()

        request.withFormat {
            form multipartForm {
                flash.message = 'Basket cleared.'
                redirect controller: 'book', action: 'index'
            }
            '*' { respond user, [status: CREATED] }
        }
    }

    @Secured('ROLE_LIBRARIAN')
    def returnBorrow(String id) {

        User selectedUser = springSecurityService.currentUser as User
        borrowService.returnBorrow(id, selectedUser)

        request.withFormat {
            form multipartForm {
                flash.message = 'Returned.'
                redirect controller: 'book', action: 'index'
            }
            '*' { respond user, [status: OK] }
        }
    }

    protected void badRequestUserLimit() {
        request.withFormat {
            form multipartForm {
                flash.message = 'Borrow limit exceeded. '
                redirect controller: 'borrow', action: "index", method: "GET"
            }
            '*'{ render status: BAD_REQUEST }
        }
    }

    protected void badRequest() {
        request.withFormat {
            '*'{ render status: BAD_REQUEST }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'Book'), params.id])
                redirect controller: 'book', action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    private boolean isCurrentUserId(Long currentId, String paramId) {
        return currentId == paramId as Long
    }
}

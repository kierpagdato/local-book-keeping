package com.bookkeeping.borrow


import com.bookkeeping.book.Book
import com.bookkeeping.book.BookDaoService
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
    BorrowService borrowService

    SpringSecurityService springSecurityService

    static allowedMethods = [selfCheckout: "POST", addToBasket: "POST", update: "PUT", delete: "DELETE"]

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


        List<Book> bookList = bookDaoService.listByIdsAndStatus(basket.bookIds, Book.Status.SHELVED)

        if(CollectionUtils.isNotEmpty(bookList)) {
            borrowService.selfService(bookList, selectedUser, isCurrentId)
            basket.bookIds.clear()
        }


        request.withFormat {
            form multipartForm {
                flash.message = 'Check out successful.'
                redirect controller: 'book', action: 'index'
            }
            '*' { respond user, [status: CREATED] }
        }
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

package com.bookkeeping.borrow

import com.bookkeeping.book.Book
import com.bookkeeping.book.BookDaoService
import com.bookkeeping.config.UserConfigService
import com.bookkeeping.my_user.MyUserDetails
import com.bookkeeping.security.User
import com.bookkeeping.security.UserDaoService
import com.bookkeeping.utils.SessionUtils
import grails.converters.JSON
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import grails.validation.Validateable
import org.apache.commons.collections.CollectionUtils

import static org.springframework.http.HttpStatus.BAD_REQUEST
import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.OK

@Secured(['IS_AUTHENTICATED_FULLY'])
class BorrowApiController {

    UserDaoService userDaoService
    BookDaoService bookDaoService
    BorrowDaoService borrowDaoService
    BorrowService borrowService
    UserConfigService userConfigService

    SpringSecurityService springSecurityService

    def addToBasket(AddToBasketCommand command) {

        if(command?.selected) {
            SessionUtils.getBorrowBasket(session)?.addToBasket(command.selected)
        }

        Map responseBody = [size: SessionUtils.getBorrowBasket(session).bookIds.size()]

        render (responseBody as JSON)
    }

    def checkOut(CheckOutCommand command) {

        BorrowBasket basket = SessionUtils.getBorrowBasket(session)

        //Return to book list if basket is empty
        if(CollectionUtils.isEmpty(basket.bookIds)) {
            //Just return 200 right away
            render status: OK
            return
        }

        MyUserDetails myUserDetails = (MyUserDetails) springSecurityService.principal
        User selectedUser = springSecurityService.currentUser as User

        boolean isCurrentId = isCurrentUserId(selectedUser.id, command.user)

        //Use ROLE_LIBRARIAN == false as validation for self checkout.
        //User can be assigned both LIBRARIAN and USER
        //It's safer to check the user doesn't have ROLE_LIBRARIAN
        //ROLE_USER cannot checkOut for other user
        if(!myUserDetails.hasAuthority("ROLE_LIBRARIAN") && !isCurrentId) {
            badRequest()
            return
        }

        if(!isCurrentId) {
            selectedUser = userDaoService.get(command.user)
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
                    render ([transactionId: transactionId] as JSON)
                } else {
                    redirect controller: 'book', action: 'index'
                }
            }
            '*' { respond selectedUser, [status: CREATED] }
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

    private boolean isCurrentUserId(Long currentId, String paramId) {
        return currentId == paramId as Long
    }
}

class AddToBasketCommand implements Validateable {

    String[] selected

}

class CheckOutCommand implements Validateable {

    String user

}

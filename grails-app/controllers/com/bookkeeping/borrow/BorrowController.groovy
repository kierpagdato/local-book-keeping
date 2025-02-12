package com.bookkeeping.borrow


import com.bookkeeping.book.Book
import com.bookkeeping.book.BookDaoService
import com.bookkeeping.security.User
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.BAD_REQUEST
import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND

class BorrowController {

    BookDaoService bookDaoService
    BorrowService borrowService

    SpringSecurityService springSecurityService

    static allowedMethods = [selfCheckout: "POST", update: "PUT", delete: "DELETE"]

    @Secured(['IS_AUTHENTICATED_FULLY'])
    def selfCheckout(Long id) {

        Book book = bookDaoService.get(id);
        User secUser = springSecurityService.currentUser as User

        if(book == null) {
            notFound()
            return
        }

        if(book.status != Book.Status.SHELVED) {
            badRequest()
            return
        }

        borrowService.selfService(book, secUser)

        request.withFormat {
            form multipartForm {
                flash.message = 'Check out successful.'
                redirect controller: 'book', action: 'show', id: id
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
}

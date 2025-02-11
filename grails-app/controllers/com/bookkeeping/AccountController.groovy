package com.bookkeeping

import com.bookkeeping.dao.UserDaoService
import com.bookkeeping.security.User
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.OK

class AccountController {

    UserDaoService userDaoService

    SpringSecurityService springSecurityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


    @Secured(['IS_AUTHENTICATED_FULLY'])
    def edit() {
        User user = springSecurityService.currentUser as User
        respond user
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])
    def update(User user) {
        User secUser = springSecurityService.currentUser as User

        if (user == null || secUser.id != user.id) {
            notFound()
            return
        }

        try {
            userDaoService.save(user)
            springSecurityService.reauthenticate(user.username)
        } catch (ValidationException e) {
            respond user.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), user.username])
                redirect action: "edit", method: "GET"
            }
            '*'{ respond user, [status: OK] }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
                redirect action: "edit", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

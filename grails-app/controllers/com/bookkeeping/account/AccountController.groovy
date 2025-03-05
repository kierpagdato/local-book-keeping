package com.bookkeeping.account

import com.bookkeeping.security.UserDaoService
import com.bookkeeping.security.UserRoleDaoService
import com.bookkeeping.security.User
import com.bookkeeping.utils.SessionUtils
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.BAD_REQUEST
import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.OK

class AccountController {

    UserDaoService userDaoService
    UserRoleDaoService userRoleDaoService

    SpringSecurityService springSecurityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
    def register() {
        respond new User(params)
    }

    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
    def save(User user) {

        if(!user.validate()) {
            log.debug("Saving user payload is invalid ${user.id}.")
            render view: 'register', model: [user: user]
            return
        }

        try {
            userDaoService.saveUser(user)
        } catch (ValidationException e) {
            log.error("Validation exception saving user ${user.id}.", e)
            respond user.errors, view:'register'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), 'info'])
                redirect uri: '/login/auth'
            }
            '*' { respond user, [status: CREATED] }
        }
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])
    def loginSuccess() {

        //Will create new object if borrow basket is null
        SessionUtils.getBorrowBasket(session)

        redirect uri: "/"
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])
    def edit() {
        User user = springSecurityService.currentUser as User
        respond user
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])
    def update(User user) {
        User secUser = springSecurityService.currentUser as User

        if (user == null) {
            log.debug("Updating user payload empty.")
            notFound()
            return
        }

        if(secUser.id != user.id) {
            log.debug("Request invalid. Cannot update other user entity sessionuser.id = ${secUser.id} - user.id = ${user.id}.")
            badRequest()
            return
        }

        if(!user.validate()) {
            log.debug("Updating user payload is invalid ${user.id}.")
            render view: 'edit', model: [user: user]
            return
        }

        try {
            userDaoService.save(user)
            springSecurityService.reauthenticate(user.username)
        } catch (ValidationException e) {
            log.error("Validation exception updating user ${user.id}.", e)
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

    @Transactional
    @Secured(['IS_AUTHENTICATED_FULLY'])
    def delete(Long id) {

        User secUser = springSecurityService.currentUser as User

        if (id == null) {
            log.debug("Cannot delete empty id.")
            notFound()
            return
        }

        if (id != secUser.id) {
            log.debug("Request invalid. Cannot delete other user entity sessionuser.id = ${secUser.id} - user.id = ${id}.")
            badRequest()
            return
        }

        userRoleDaoService.removeRoles(secUser)
        userDaoService.delete(id)

        session.invalidate()
        springSecurityService.reauthenticate(secUser.username)

        redirect uri: '/'

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

    protected void badRequest() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.bad.request.message')
                redirect action: "index", method: "GET"
            }
            '*'{ render status: BAD_REQUEST }
        }
    }
}

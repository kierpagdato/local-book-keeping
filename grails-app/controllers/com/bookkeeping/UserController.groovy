package com.bookkeeping

import com.bookkeeping.dao.UserDaoService
import com.bookkeeping.security.Role
import com.bookkeeping.security.User
import com.bookkeeping.security.UserRole
import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class UserController {

    UserDaoService userDaoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond userDaoService.list(params), model:[userCount: userDaoService.count()]
    }

    def show(Long id) {
        respond userDaoService.get(id)
    }

    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
    def register() {
        respond new User(params)
    }

    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
    def saveUser(User user) {

        if(!user.validate()) {
            render(view: 'register', model: [user: user])
            return
        }

        try {
            userDaoService.saveUser(user)
        } catch (ValidationException e) {
            respond user.errors, view:'register'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User')])
                redirect uri: '/login/auth'
            }
            '*' { respond user, [status: CREATED] }
        }
    }

    def save(User user) {
        if (user == null) {
            notFound()
            return
        }

        try {
            userDaoService.save(user)
        } catch (ValidationException e) {
            respond user.errors, view:'register'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), user.id])
                redirect user
            }
            '*' { respond user, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond userDaoService.get(id)
    }

    def update(User user) {
        if (user == null) {
            notFound()
            return
        }

        try {
            userDaoService.save(user)
        } catch (ValidationException e) {
            respond user.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), user.id])
                redirect user
            }
            '*'{ respond user, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        userDaoService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

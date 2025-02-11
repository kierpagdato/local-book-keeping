package com.bookkeeping

import com.bookkeeping.dao.UserDaoService
import com.bookkeeping.security.User
import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class UserController {

    UserDaoService userDaoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured('ROLE_LIBRARIAN')
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)

        List<User> userList = userDaoService.list(params)
        [list: userList,
         count: userList.getTotalCount()]
    }

    @Secured('ROLE_LIBRARIAN')
    def show(Long id) {
        respond userDaoService.get(id)
    }

    @Secured('ROLE_LIBRARIAN')
    def create() {
        respond new User(params)
    }

    @Secured('ROLE_LIBRARIAN')
    def save(User user) {

        println "save ${user}"
        if (user == null) {
            notFound()
            return
        }

        if(!user.validate()) {
            render view: 'create', model: [user: user]
            return
        }

        println "here"

        try {
            userDaoService.save(user)
            userDaoService.setRoles(user, params.getList('roles'))
        } catch (ValidationException e) {
            respond user.errors, view:'create'
            return
        }

        redirect action:"index", params: [sort: "dateCreated", order: "desc"]
    }

    @Secured('ROLE_LIBRARIAN')
    def edit(Long id) {
        respond userDaoService.get(id)
    }

    @Secured(['ROLE_LIBRARIAN'])
    def update(User user) {

        if (user == null) {
            notFound()
            return
        }

        if(!user.validate()) {
            render view: 'edit', model: [user: user]
            return
        }

        try {
            userDaoService.save(user)
            userDaoService.setRoles(user, params.getList('roles'))
        } catch (ValidationException e) {
            respond user.errors, view:'edit'
            return
        }

        redirect action:"index", params: [sort: "lastUpdated", order: "desc"]
    }

    @Secured('ROLE_LIBRARIAN')
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

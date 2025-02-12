package com.bookkeeping.user

import com.bookkeeping.security.UserDaoService
import com.bookkeeping.security.UserRoleDaoService
import com.bookkeeping.security.User
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

@Secured('ROLE_LIBRARIAN')
class UserController {

    UserDaoService userDaoService
    UserRoleDaoService userRoleDaoService

    SpringSecurityService springSecurityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)

        List<User> userList = userDaoService.list(params)
        [list: userList,
         count: userList.getTotalCount()]
    }

    def show(Long id) {
        respond userDaoService.get(id)
    }

    def create() {
        respond new User(params)
    }

    def save(User user) {

        if (user == null) {
            notFound()
            return
        }

        if(!user.validate()) {
            render view: 'create', model: [user: user]
            return
        }

        try {
            userDaoService.save(user)
            userRoleDaoService.setRoles(user, params.getList('roles'))
        } catch (ValidationException e) {
            respond user.errors, view:'create'
            return
        }

        redirect action:"index", params: [sort: "dateCreated", order: "desc"]
    }

    def edit(Long id) {
        respond userDaoService.get(id)
    }

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
            userRoleDaoService.setRoles(user, params.getList('roles'))
        } catch (ValidationException e) {
            respond user.errors, view:'edit'
            return
        }

        redirect action:"index", params: [sort: "lastUpdated", order: "desc"]
    }

    @Transactional
    def delete(Long id) {

        User secUser = springSecurityService.currentUser as User

        if (id == null) {
            notFound()
            return
        }

        if (id == secUser.id) {
            badRequest()
            return
        }

        userRoleDaoService.removeRoles(userDaoService.get(id))
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

    protected void badRequest() {
        request.withFormat {
            '*'{ render status: BAD_REQUEST }
        }
    }
}

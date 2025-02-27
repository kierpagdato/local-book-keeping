package com.bookkeeping.account

import com.bookkeeping.book.Book
import com.bookkeeping.book.BookController
import com.bookkeeping.book.BookDaoService
import com.bookkeeping.security.User
import com.bookkeeping.security.UserDaoService
import com.bookkeeping.security.UserRoleDaoService
import grails.plugin.springsecurity.SpringSecurityService
import grails.testing.gorm.DomainUnitTest
import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification

class AccountControllerSpec extends Specification implements ControllerUnitTest<AccountController>, DomainUnitTest<User> {

    def setup() {
        User user = new User(username: 'johndoe', password: '123123',
                email: 'johndoe@gmail.com', firstName: 'John', lastName: 'Doe')
        user.id = 1
        controller.userDaoService = Mock(UserDaoService)
        controller.userRoleDaoService = Mock(UserRoleDaoService)
        controller.springSecurityService = Mock(SpringSecurityService) {
            currentUser >> user
        }
    }

    void "Test the register user"() {
        given:
            User user = new User(username: 'johndoe', password: '123123',
                    email: 'johndoe@gmail.com', firstName: 'John', lastName: 'Doe')

        when:"The save action is executed with a valid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            controller.save(user)

        then:"A redirect is issued to the show action"
            1 * controller.userDaoService.saveUser(_)
            response.status == 302
            response.redirectedUrl == '/login/auth'
    }

    void "Test the register action with invalid user model - no username"() {
        given:
            User user = new User(password: '123123',
                    email: 'johndoe@gmail.com', firstName: 'John', lastName: 'Doe')

        when:"Save is called for a domain instance that has invalid model"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            controller.save(user)

        then:"Redirected back to the form"
            model.user
            view == '/account/register'
    }

    void "Test the update user"() {
        given:
            User user = new User(username: 'johndoe', password: '123123',
                    email: 'johndoe@gmail.com', firstName: 'John', lastName: 'Doe')
            user.id = 1

        when:"The update action is executed with a valid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(user)

        then:"A redirect is issued to the show action"
            1 * controller.userDaoService.save(_)
            1 * controller.springSecurityService.reauthenticate(_)
            response.status == 302
            response.redirectedUrl == '/account/edit'
    }

    void "Test the update different user"() {
        given:
            User user = new User(username: 'emilydoe', password: '123123',
                    email: 'emilydoe@gmail.com', firstName: 'Emily', lastName: 'Doe')
            user.id = 2

        when:"The update action is executed with a valid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(user)

        then:"A redirect is issued to the show action"
            response.status == 302
            response.redirectedUrl == '/account/index'
    }

    void "Test the delete user"() {

        when:"The domain instance is passed to the delete action"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(1)

        then:"The user is redirected to user index"
            1 * controller.userRoleDaoService.removeRoles(_)
            1 * controller.userDaoService.delete(_)
            1 * controller.springSecurityService.reauthenticate(_)
            response.status == 302
            response.redirectedUrl == '/'
    }

    void "Test the delete different user"() {

        when:"The domain instance is passed to the delete action"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(2)

        then:"The user is redirected to user index"
            response.status == 302
            response.redirectedUrl == '/account/index'
    }
}

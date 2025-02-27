package com.bookkeeping.user

import com.bookkeeping.security.User
import com.bookkeeping.security.UserDaoService
import com.bookkeeping.security.UserRoleDaoService
import grails.plugin.springsecurity.SpringSecurityService
import grails.testing.gorm.DomainUnitTest
import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.*

class UserControllerSpec extends Specification implements ControllerUnitTest<UserController>, DomainUnitTest<User> {

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

    void "Test the save user"() {
        given:
            User user = new User(username: 'johndoe', password: '123123',
                    email: 'johndoe@gmail.com', firstName: 'John', lastName: 'Doe')

        when:"The save action is executed with a valid instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'POST'
        params.roles = ['ROLE_USER'] as List
        controller.save(user)

        then:"A redirect is issued to the show action"
            1 * controller.userDaoService.save(_)
            1 * controller.userRoleDaoService.setRoles(_, _)
            response.status == 302
            response.redirectedUrl == '/user/index?sort=dateCreated&order=desc'
    }

    void "Test the save action with invalid user model - no username"() {
        given:
            User user = new User(password: '123123',
                    email: 'johndoe@gmail.com', firstName: 'John', lastName: 'Doe')

        when:"Save is called for a domain instance that has invalid model"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            controller.save(user)

        then:"Redirected back to the form"
            model.user
            view == '/user/create'
    }

    void "Test the update user"() {
        given:
            User user = new User(username: 'johndoe', password: '123123',
                    email: 'johndoe@gmail.com', firstName: 'John', lastName: 'Doe')

        when:"The update action is executed with a valid instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        params.roles = ['ROLE_USER'] as List
        controller.update(user)

        then:"A redirect is issued to the show action"
            1 * controller.userDaoService.save(_)
            1 * controller.userRoleDaoService.setRoles(_, _)
            response.status == 302
            response.redirectedUrl == '/user/index?sort=lastUpdated&order=desc'
    }

    void "Test the update action with invalid user model - no password"() {
        given:
            User user = new User(username: 'johndoe',
                    email: 'johndoe@gmail.com', firstName: 'John', lastName: 'Doe')

        when:"Update is called for a domain instance that has invalid model"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(user)

        then:"Redirected back to the form"
            model.user
            view == '/user/edit'
    }

    void "Test the delete user"() {

        when:"The domain instance is passed to the delete action"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(2)

        then:"The user is redirected to user index"
            1 * controller.userDaoService.delete(_)
            1 * controller.userRoleDaoService.removeRoles(_)
            response.status == 302
            response.redirectedUrl == '/user/index'
    }

    void "Test the delete current user"() {

        when:"The domain instance is passed to the delete action"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(1)

        then:"The user is redirected to user index"
            response.status == 400
    }

}







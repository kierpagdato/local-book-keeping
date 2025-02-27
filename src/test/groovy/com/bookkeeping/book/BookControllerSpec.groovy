package com.bookkeeping.book

import grails.testing.gorm.DomainUnitTest
import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification

class BookControllerSpec extends Specification implements ControllerUnitTest<BookController>, DomainUnitTest<Book> {

    def setup() {
        controller.bookDaoService = Mock(BookDaoService)
    }

    def cleanup() {
    }

    void "Test the save book"() {
        given:
            Book book = new Book(title: 'Sesame Street', author: 'John Doe', isbn: '111111111',
            status: Book.Status.SHELVED, description: 'Sesame street book')

        when: "The save action is executed"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            controller.save(book)

        then:"The model is correct"
            1 * controller.bookDaoService.save(_)
            response.status == 302
            response.redirectedUrl == '/?sort=dateCreated&order=desc'
    }

    void "Test the save book with invalid book model - no title property"() {
        given:
            Book book = new Book(author: 'John Doe', isbn: '111111111',
            status: Book.Status.SHELVED, description: 'Sesame street book')

        when: "The save action is executed with invalid book model"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            controller.save(book)

        then:"Check view with invalid model"
            model.book
            view == '/book/create'
    }

    void "Test the update book"() {
        given:
            Book book = new Book(title: 'Sesame Street', author: 'John Doe', isbn: '111111111',
                    status: Book.Status.SHELVED, description: 'Sesame street book')

        when: "The update action is executed"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(book)

        then:"The model is correct"
            1 * controller.bookDaoService.save(_)
            response.status == 302
    }

    void "Test the update book with invalid book model - no author property"() {
        given:
        Book book = new Book(title: 'Sesame Street', isbn: '111111111',
                status: Book.Status.SHELVED, description: 'Sesame street book')

        when: "The update action is executed with invalid book model"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(book)

        then:"Check view with invalid model"
            model.book
            view == '/book/edit'
    }

    void "Test the delete book"() {

        when:"The domain instance is passed to the delete action"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(2)

        then:"The user is redirected to home index"
            response.redirectedUrl == '/'
    }
}

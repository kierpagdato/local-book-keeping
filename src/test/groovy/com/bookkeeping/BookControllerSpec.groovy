package com.bookkeeping

import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification

class BookControllerSpec extends Specification implements ControllerUnitTest<BookController> {

    def setup() {
    }

    def cleanup() {
    }

    void "Test the index action returns the correct model"() {
        given:
        controller.bookService = Mock(IBookDaoService) {
            list() >> [new Book(title: 'Sesame Street', author: 'John Doe')]
        }

        when: "The index action is executed"
        controller.index()

        then:"The model is correct"
        model.bookList.size() == 1
        model.bookList[0].title == 'Sesame Street'
        model.bookList[0].author == 'John Doe'
    }

    void "Test the delete action with an instance"() {
        given:
        controller.bookService = Mock(bookService) {
            1 * delete(2)
        }

        when:"The domain instance is passed to the delete action"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'DELETE'
        controller.delete(2)

        then:"The user is redirected to index"
        response.redirectedUrl == '/book/index'
    }
}

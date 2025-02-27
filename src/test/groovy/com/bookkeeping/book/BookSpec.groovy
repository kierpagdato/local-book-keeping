package com.bookkeeping.book


import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification
import spock.lang.Unroll

class BookSpec extends Specification implements DomainUnitTest<Book> {

    def setup() {
    }

    def cleanup() {
    }

    @Unroll
    void "test nullable"() {
        given:
            Book book = new Book()
            book.setProperty(propertyName, null)
            book.validate([propertyName])
        expect:
            error == book?.errors[propertyName]?.code
        where:
            propertyName    || error
            "title"         || "nullable"
            "author"        || "nullable"
            "isbn"          || "nullable"
            "status"        || "nullable"
            "description"   || "nullable"

    }
}

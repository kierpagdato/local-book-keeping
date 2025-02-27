package com.bookkeeping.borrow

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification
import spock.lang.Unroll

class BorrowSpec extends Specification implements DomainUnitTest<Borrow> {

    def setup() {
    }

    def cleanup() {
    }

    @Unroll
    void "test nullable"() {
        given:
            Borrow borrow = new Borrow()
            borrow.setProperty(propertyName, null)
            borrow.validate([propertyName])
        expect:
            error == borrow?.errors[propertyName]?.code
        where:

            propertyName        || error
            "transactionId"     || "nullable"
            "dateBorrowed"      || "nullable"
            "dateReturned"      || null
            "book"              || "nullable"
            "borrower"          || "nullable"
            "processor"         || "nullable"
            "status"            || "nullable"
            "type"              || "nullable"
    }
}

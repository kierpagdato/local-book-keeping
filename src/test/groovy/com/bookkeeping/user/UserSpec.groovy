package com.bookkeeping.user

import com.bookkeeping.security.User
import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification
import spock.lang.Unroll

class UserSpec extends Specification implements DomainUnitTest<User> {

    def setup() {
    }

    def cleanup() {
    }

    @Unroll
    void "test nullable"() {
        given:
            User user = new User()
            user.setProperty(propertyName, null)
            user.validate([propertyName])
        expect:
            error == user?.errors[propertyName]?.code
        where:
            propertyName    || error
            "username"      || "nullable"
            "password"      || "nullable"
            "firstName"     || "nullable"
            "lastName"      || "nullable"
            "email"         || "nullable"
    }
}

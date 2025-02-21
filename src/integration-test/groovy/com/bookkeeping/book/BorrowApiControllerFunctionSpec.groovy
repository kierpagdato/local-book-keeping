package com.bookkeeping.book

import com.bookkeeping.BaseFunctionalSpec
import grails.testing.mixin.integration.Integration
import grails.testing.spock.OnceBefore
import org.springframework.http.HttpMethod
import spock.lang.Shared

@Integration
class BorrowApiControllerFunctionSpec extends BaseFunctionalSpec {

    @Shared
    private String librarianJSessionId
    @Shared
    private String userJSessionId

    @OnceBefore
    void init() {

        this.librarianJSessionId = loginForSessionId('librarian', '123123')
        println("--- Librarian JSessionID setup - $librarianJSessionId")

        this.userJSessionId = loginForSessionId('johndoe', '123123')
        println("--- User JSessionID setup - $userJSessionId")
    }

    def "test add to basket ROLE_LIBRARIAN"() {
        given: "Request from ROLE_LIBRARIAN"
            def request = [
                    headers : [
                            "Cookie"  : librarianJSessionId
                    ],
                    body : [
                            "selected" : ["1", "2"]
                    ]
            ]

        when: "API /../addToBasket"
            def response = apiResponse(HttpMethod.POST, "/api/borrow/v1/addToBasket", request)

        then: "Success response"
            response.status == 200
        and: "Check body.size"
            response.body == "{\"size\":2}"
    }

    def "test add to basket ROLE_USER"() {
        given: "Request from ROLE_USER"
            def request = [
                    headers : [
                            "Cookie"  : userJSessionId
                    ],
                    body : [
                            "selected" : ["23", "24", "25"]
                    ]
            ]

        when: "API /../addToBasket"
            def response = apiResponse(HttpMethod.POST, "/api/borrow/v1/addToBasket", request)

        then: "Success response"
            response.status == 200
        and: "Check body.size"
            response.body == "{\"size\":3}"
    }
}
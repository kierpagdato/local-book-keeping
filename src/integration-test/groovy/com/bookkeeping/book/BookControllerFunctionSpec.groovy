package com.bookkeeping.book

import com.bookkeeping.BaseFunctionalSpec
import grails.testing.mixin.integration.Integration
import grails.testing.spock.OnceBefore
import org.springframework.http.HttpMethod

@Integration
class BookControllerFunctionSpec extends BaseFunctionalSpec {

    private String librarianJSessionId
    private String userJSessionId

    @OnceBefore
    void init() {

        this.librarianJSessionId = loginForSessionId('librarian', '123123')
        println("--- Librarian JSessionID setup - $librarianJSessionId")

        this.userJSessionId = loginForSessionId('johndoe', '123123')
        println("--- User JSessionID setup - $userJSessionId")
    }

    def "test user with ROLE_LIBRARIAN can add to basket"() {
        given: "Request from ROLE_LIBRARIAN"
            def request = [
                    headers : [
                            "Cookie"  : librarianJSessionId
                    ]
            ]

        when: "API /../addToBasket"
            def response = apiResponse(HttpMethod.POST, "/api/borrow/v1/addToBasket", request)

        then: "Always success response"
            response.status == 200
    }
}

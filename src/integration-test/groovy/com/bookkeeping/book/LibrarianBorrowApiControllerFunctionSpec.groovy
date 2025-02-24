package com.bookkeeping.book

import com.bookkeeping.BaseFunctionalSpec
import grails.testing.mixin.integration.Integration
import grails.testing.spock.OnceBefore
import org.springframework.http.HttpMethod
import spock.lang.Shared

@Integration
class LibrarianBorrowApiControllerFunctionSpec extends BaseFunctionalSpec {

    @Shared
    protected String jSessionId

    @OnceBefore
    void init() {

        this.jSessionId = loginForSessionId('librarian', '123123')
        println("--- Librarian JSessionID setup - $jSessionId")
    }

    def "test add to basket"() {
        given: "Request from librarian"

            def request = [
                    headers : [
                            "Cookie"  : jSessionId
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

    def "test check out SELF_SERVICE"() {
        given: "Request from librarian"

            def request = [
                    headers : [
                            "Cookie"  : jSessionId
                    ],
                    body : [
                            "user" : "1"
                    ]
            ]

        when: "API /../checkout"
            def response = apiResponse(HttpMethod.POST, "/api/borrow/v1/checkOut", request)
        then: "Created response"
            response.status == 201
    }

    def "test check out LIBRARY"() {
        given: "Request from librarian"

            def request = [
                    headers : [
                            "Cookie"  : jSessionId
                    ],
                    body : [
                            "selected" : ["26"]
                    ]
            ]

            //add more books
            apiResponse(HttpMethod.POST, "/api/borrow/v1/addToBasket", request)

            request = [
                    headers : [
                            "Cookie"  : jSessionId
                    ],
                    body : [
                            //checkout other user's basket
                            "user" : "2"
                    ]
            ]

        when: "API /../checkout"
            def response = apiResponse(HttpMethod.POST, "/api/borrow/v1/checkOut", request)
        then: "Created response"
            response.status == 201
    }

    def "test check out limit"() {
        given: "Request from librarian"

            def request = [
                    headers : [
                            "Cookie"  : jSessionId
                    ],
                    body : [
                            "selected" : ["26", "27", "28", "29", "30", "31"]
                    ]
            ]

            //add more books
            apiResponse(HttpMethod.POST, "/api/borrow/v1/addToBasket", request)

            request = [
                    headers : [
                            "Cookie"  : jSessionId
                    ],
                    body : [
                            "user" : "1"
                    ]
            ]

        when: "API /../checkout"
            def response = apiResponse(HttpMethod.POST, "/api/borrow/v1/checkOut", request)
        then: "Bad request response"
            response.status == 400
    }

}

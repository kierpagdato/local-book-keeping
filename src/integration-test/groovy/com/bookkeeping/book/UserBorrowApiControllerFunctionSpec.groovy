package com.bookkeeping.book

import com.bookkeeping.BaseFunctionalSpec
import grails.testing.mixin.integration.Integration
import grails.testing.spock.OnceBefore
import org.springframework.http.HttpMethod
import spock.lang.Shared

@Integration
class UserBorrowApiControllerFunctionSpec extends BaseFunctionalSpec {

    @Shared
    protected String jSessionId

    @OnceBefore
    void init() {

        this.jSessionId = loginForSessionId('johndoe', '123123')
        println("--- User JSessionID setup - $jSessionId")
    }

    def "test add to basket"() {
        given: "Request from user"
            def request = [
                    headers : [
                            "Cookie"  : jSessionId
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

    def "test check out SELF_SERVICE"() {
        given: "Request from user"

            def request = [
                    headers : [
                            "Cookie"  : jSessionId
                    ],
                    body : [
                            "user" : "2"
                    ]
            ]

        when: "API /../checkout"
            def response = apiResponse(HttpMethod.POST, "/api/borrow/v1/checkOut", request)
        then: "Created response"
            response.status == 201
    }

    def "test check out limit"() {
        given: "Request from user"

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
                                "user" : "2"
                        ]
                ]

        when: "API /../checkout"
            def response = apiResponse(HttpMethod.POST, "/api/borrow/v1/checkOut", request)
        then: "Bad request response"
            response.status == 400
    }

//    ROLE_USER can only do SELF_SERVICE checkout
//    meaning only current user ID will be accepted for checkout
    def "test check out other user from ROLE_USER"() {
        given: "Request from user"

            def request = [
                    headers : [
                            "Cookie"  : jSessionId
                    ],
                    body : [
                            "selected" : ["26", "27", "28", "29", "30", "31"]
                    ]
            ]

//            add books first to basket
            apiResponse(HttpMethod.POST, "/api/borrow/v1/addToBasket", request)

            request = [
                        headers : [
                                "Cookie"  : jSessionId
                        ],
                        body : [
                                //johndoe user id == 2
                                //user id == 3 is another user
                                "user" : "3"
                        ]
                ]

        when: "API /../checkout"
            def response = apiResponse(HttpMethod.POST, "/api/borrow/v1/checkOut", request)
        then: "Bad request response"
            response.status == 400
    }
}

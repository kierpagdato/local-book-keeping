package com.bookkeeping

import io.ansr.functional.test.ODRFunctionalSpec

class BookControllerFunctionSpec extends ODRFunctionalSpec {

    def "test API response"() {
        given: "An API request"
        def response = apiResponse(HttpMethod.GET, "/api/borrow/v1/addToBasket")

        expect: "Response is as expected"
        response.status == 200
    }
}

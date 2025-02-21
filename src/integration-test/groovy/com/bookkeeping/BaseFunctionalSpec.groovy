package com.bookkeeping

import io.ansr.functional.test.ODRFunctionalSpec
import org.springframework.http.HttpMethod
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap

class BaseFunctionalSpec extends ODRFunctionalSpec {

    def loginForSessionId(String username, String password) {

        def apiPath = "/login/authenticate"

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("username", username);
        formData.add("password", password);

        def request = [
                headers : [
                        "Content-Type" : "application/x-www-form-urlencoded"
                ],
                body : formData
        ]

        def response = apiResponse(HttpMethod.POST, apiPath, request)

        return response.headers.get("Set-Cookie").get(0).split(";")[0]
    }

}

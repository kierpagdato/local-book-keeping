package bookkeeping

class UrlMappings {

    static mappings = {

        post "/api/borrow/v1/addToBasket" (controller: 'borrowApi', action: 'addToBasket')
        post "/api/borrow/v1/checkOut" (controller: 'borrowApi', action: 'checkOut')

        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

//        "/"(view:"/index")
        "/"(controller: 'book', action: 'index')
        "500"(view:'/error')
        "400"(view:'/badRequest')
        "404"(view:'/notFound')
    }
}

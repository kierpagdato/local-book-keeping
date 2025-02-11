package bookkeeping

class UrlMappings {

    static mappings = {
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

package com.bookkeeping

class BookController {

    def bookService

    def index() {
        if(params.max == null)
            params.max = 5

        [list: bookService.list(params), count: bookService.count()]
    }

    def show(Long id) {
        respond bookService.get(id)
    }

    def create() {
        respond new Book(params)
    }

    def save(Book book) {
        bookService.save(book)
        redirect action:"index", method:"GET"
    }

    def delete(Long id) {
        bookService.delete(id)
        redirect action:"index", method:"GET"
    }
}

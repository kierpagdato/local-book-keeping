package com.bookkeeping

import com.bookkeeping.dao.BookDaoService
import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.NOT_FOUND

class BookController {

    BookDaoService bookDaoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured('permitAll')
    def index() {
        params.max = params?.max ?: 10
        List<Book> bookList = bookDaoService.list(params)
        [list: bookList,
         count: bookList.getTotalCount()]
    }

    @Secured('permitAll')
    def show(Long id) {
        respond bookDaoService.get(id)
    }

    @Secured('ROLE_LIBRARIAN')
    def create() {
        respond new Book(params)
    }

    @Secured('ROLE_LIBRARIAN')
    def save(Book book) {

        if(!book.validate()) {
            render(view: 'create', model: [book: book])
            return
        }

        for(int i = 0; i < book.quantity; i++) {
            book.id = null
            bookDaoService.save(new Book(book.properties))
        }

        redirect action:"index", params: [sort: "dateCreated", order: "desc"]
    }

    @Secured('ROLE_LIBRARIAN')
    def edit(Long id) {
        respond bookDaoService.get(id)
    }

    @Secured('ROLE_LIBRARIAN')
    def update(Book book) {

        if(!book.validate()) {
            render view: 'edit', model: [book: book]
            return
        }

        bookDaoService.save(book)

        redirect action:"index", params: [sort: "lastUpdated", order: "desc"]
    }

    @Secured('ROLE_LIBRARIAN')
    def delete(Long id) {
        bookDaoService.delete(id)
        redirect action:"index"
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'Book'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

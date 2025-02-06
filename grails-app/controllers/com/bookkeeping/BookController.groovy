package com.bookkeeping

import com.bookkeeping.dao.BookDaoService
import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.OK

class BookController {

    BookDaoService bookDaoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index() {
        params.max = params?.max ?: 5
        List<Book> bookList =bookDaoService.list(params);
        [list: bookList,
         count: bookList.getTotalCount(),
         fieldProperties: ['title', 'author', 'isbn', 'status', 'dateCreated', 'lastUpdated']]
    }

    def show(Long id) {
        respond bookDaoService.get(id)
    }

    def create() {
        respond new Book(params)
    }

    def save(Book book) {

        if(!book.validate()) {
            render(view: 'create', model: [book: book])
            return
        }

        bookDaoService.save(book)

        redirect action:"index", params: [sort: "dateCreated", order: "desc"]
    }

    def edit(Long id) {
        respond bookDaoService.get(id)
    }

    def update(Book book) {

        if(!book.validate()) {
            render view: 'edit', model: [book: book]
            return
        }

        bookDaoService.save(book)

        redirect action:"index", params: [sort: "lastUpdated", order: "desc"]
    }

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

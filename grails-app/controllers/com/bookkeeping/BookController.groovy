package com.bookkeeping

import com.bookkeeping.dao.BookDaoService

class BookController {

    BookDaoService bookDaoService

    def index() {
        params.max = params?.max ?: 5
        List<Book> bookList =bookDaoService.list(params);
        [list: bookList,
         count: bookList.getTotalCount(),
         fieldProperties: ['title', 'author', 'status', 'quantity', 'dateCreated', 'lastUpdated']]
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

        book.status = Book.Status.In

        bookDaoService.save(book)

        redirect action:"index", method:"GET"
    }

    def delete(Long id) {
        bookDaoService.delete(id)
        redirect action:"index", method:"GET"
    }
}

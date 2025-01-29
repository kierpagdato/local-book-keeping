package com.bookkeeping

import grails.gorm.transactions.Transactional

@Transactional
class BookService {

    def get(id) {
        Book.get(id)
    }

    def list(params) {
        Book.list(params)
    }

    def count() {
        Book.count()
    }

    def save(book) {
        book.save()
    }

    def delete(id) {
        Book.get(id).delete()
    }

}

package com.bookkeeping

class User {

    String name

    Date dateCreated
    Date lastUpdated

    static hasMany = [books: Book]

    static constraints = {
    }

    @Override
    String toString() {
        name
    }
}

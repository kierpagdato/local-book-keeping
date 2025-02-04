package com.bookkeeping

class Book {

    String title
    String author

    Status status

    int quantity

    String description

    Date dateCreated
    Date lastUpdated

    static mapping = {
        status enumType: 'string'
        description type: 'text'
    }

    static constraints = {
        title nullable: false, blank: false
        author nullable: false, blank: false
        status nullable: false
        quantity min: 1
    }

    @Override
    String toString() {
        title
    }


    enum Status {
        Out, In
    }
}

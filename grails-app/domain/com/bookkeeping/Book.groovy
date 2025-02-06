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
        title maxSize: 50, nullable: false, blank: false
        author maxSize: 50, nullable: false, blank: false
        status nullable: false
        quantity min: 1
        description maxSize: 1000, nullable: false, blank: false
    }

    @Override
    String toString() {
        "${title} : ${author} : ${quantity} : ${description}"
    }

    enum Status {
        Out, In
    }
}

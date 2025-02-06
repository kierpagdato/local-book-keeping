package com.bookkeeping

class Book {

    String title
    String author

    Status status

    int copy

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
        copy min: 1
        description maxSize: 1000, nullable: false, blank: false
    }

    @Override
    String toString() {
        "${title} : ${author} : ${copy} : ${description}"
    }

    enum Status {
        OUT("Out"), SHELVED("Shelved")

        String text

        Status(String text) {
            this.text = text;
        }
    }
}

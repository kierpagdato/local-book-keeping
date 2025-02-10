package com.bookkeeping

class Book {


    String title
    String author

    String isbn

    Status status


    String description

    Date dateCreated
    Date lastUpdated

    int quantity

    static hasMany = [borrow: Borrow]

    static mapping = {
        status enumType: 'string'
        description type: 'text'
    }

    static transients = ['quantity']

    static constraints = {
        title maxSize: 50, nullable: false, blank: false
        author maxSize: 50, nullable: false, blank: false
        isbn maxSize: 50, nullable: false, blank: false
        status nullable: false
        description maxSize: 1000, nullable: false, blank: false
        quantity bindable: true, minSize: 1
    }

    @Override
    String toString() {
        "${id} : ${title} : ${author} : ${isbn} : ${description}"
    }

    enum Status {
        COMING("Coming"),
        SHELVED("Shelved"),
        OUT("Checked out"),
        ARCHIVED("Archived")

        String text

        Status(String text) {
            this.text = text;
        }
    }
}

package com.bookkeeping.book

import com.bookkeeping.borrow.Borrow
import groovy.transform.ToString

import java.time.LocalDateTime

@ToString(includeNames=true, includePackage=false)
class Book {


    String title
    String author

    String isbn

    Status status


    String description

    LocalDateTime dateCreated
    LocalDateTime lastUpdated

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

    enum Status {
        COMING("Coming", "is-info"),
        SHELVED("Shelved", "is-link"),
        OUT("Checked out", "is-warning"),
        ARCHIVED("Archived", "is-danger")

        String text
        String color

        Status(String text, String color) {
            this.text = text
            this.color = color
        }
    }
}

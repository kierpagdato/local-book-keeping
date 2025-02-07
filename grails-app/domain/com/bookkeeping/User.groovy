package com.bookkeeping

class User {

    String name

    Role role

    Date dateJoined

    Date dateCreated
    Date lastUpdated

    static hasMany = [borrow: Borrow, processedBorrow: Borrow]

    static mappedBy = [borrow: "borrower", processedBorrow: "processor"]

    static mapping = {
        role enumType: 'string'
    }

    static constraints = {
        name maxSize: 50, nullable: false, blank: false
        role nullable: false
        dateJoined nullable: false
    }

    @Override
    String toString() {
        "${name} : ${role.text} : ${dateJoined}"
    }

    enum Role {
        USER("User"), LIBRARIAN("Librarian")

        String text

        Role(String text) {
            this.text = text;
        }
    }
}

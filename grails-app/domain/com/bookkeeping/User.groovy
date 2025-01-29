package com.bookkeeping

class User {

    String name

    Date dateCreated
    Date lastUpdated

    static constraints = {
    }

    @Override
    String toString() {
        name
    }
}

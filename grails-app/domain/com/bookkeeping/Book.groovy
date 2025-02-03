package com.bookkeeping

import com.bookkeeping.enums.BookStatus

class Book {

    String title

    BookStatus status

    Date dateCreated
    Date lastUpdated

    static belongsTo = [author: User]

    static mapping = {
        status enumType: 'string'
    }

    @Override
    String toString() {
        title
    }

}

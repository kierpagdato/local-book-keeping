package com.bookkeeping.enums

enum BookStatus {

    OUT("Out"),
    IN("In")

    String text

    private BookStatus(String text) {
        this.text = text
    }
}

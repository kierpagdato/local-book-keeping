package com.bookkeeping.borrow

import groovy.transform.ToString

@ToString(includeNames=true, includePackage=false)
class BorrowBasket {

    public static final String SESSION_KEY = "borrowBasket";

    Set<String> bookIds = new HashSet<>()

    void addToBasket(String id) {
        bookIds.add(id)
    }

    void addToBasket(String[] ids){
        bookIds.addAll(ids as List)
    }

}

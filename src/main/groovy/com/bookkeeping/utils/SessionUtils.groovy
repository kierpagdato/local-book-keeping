package com.bookkeeping.utils;

import com.bookkeeping.borrow.BorrowBasket;

import javax.servlet.http.HttpSession;

class SessionUtils {

    static BorrowBasket getBorrowBasket(HttpSession session) {
        Object obj = session.getAttribute(BorrowBasket.SESSION_KEY)

        if(!obj) {
            BorrowBasket basket = new BorrowBasket()
            session.setAttribute(BorrowBasket.SESSION_KEY, basket)
            return basket
        }

        return (BorrowBasket) obj
    }
}

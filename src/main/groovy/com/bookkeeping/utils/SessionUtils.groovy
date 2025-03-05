package com.bookkeeping.utils;

import com.bookkeeping.borrow.BorrowBasket
import groovy.util.logging.Slf4j;

import javax.servlet.http.HttpSession;

@Slf4j
class SessionUtils {

    static BorrowBasket getBorrowBasket(HttpSession session) {
        Object obj = session.getAttribute(BorrowBasket.SESSION_KEY)

        if(!obj) {
            log.debug("Creating user basket.")
            BorrowBasket basket = new BorrowBasket()
            session.setAttribute(BorrowBasket.SESSION_KEY, basket)
            return basket
        }

        return (BorrowBasket) obj
    }
}

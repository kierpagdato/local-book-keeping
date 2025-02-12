package com.bookkeeping.borrow

import grails.gorm.services.Service
import grails.gorm.transactions.Transactional

interface IBorrowDaoService {

    Borrow get(Serializable id)

    Long count()

    Borrow save(Borrow borrow)

    void delete(Serializable id)

}


@Transactional
@Service(Borrow)
abstract class BorrowDaoService implements IBorrowDaoService {

}

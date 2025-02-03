package com.bookkeeping

import grails.gorm.services.Service
import grails.gorm.transactions.Transactional


interface IBookDaoService {

    Book get(Serializable id)

    List<Book> list(params)

    Long count()

    Book save(book)

    void delete(id)

}


@Transactional
@Service(Book)
abstract class BookDaoService implements IBookDaoService {

}

package com.bookkeeping.dao

import com.bookkeeping.Book
import grails.gorm.services.Service
import grails.gorm.transactions.Transactional


interface IBookDaoService {

    Book get(Serializable id)

    Long count()

    void delete(id)

}


@Transactional
@Service(Book)
abstract class BookDaoService implements IBookDaoService {

    def list(params) {
        return Book.createCriteria().list(params) {
            if(params?.keyword?.trim()) {
                ilike("title", "%" + params.keyword + "%")
            }
        }
    }

    Book save(book) {
        return book.save()
    }
}

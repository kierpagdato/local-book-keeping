package com.bookkeeping.book


import grails.gorm.services.Service
import grails.gorm.transactions.Transactional
import grails.web.servlet.mvc.GrailsParameterMap


interface IBookDaoService {

    Book get(Serializable id)

    Long count()

    Book save(Book book)

    void delete(Serializable id)

}

@Transactional
@Service(Book)
abstract class BookDaoService implements IBookDaoService {

    List<Book> list(GrailsParameterMap params) {
        return Book.createCriteria().list(params) {
            if(params?.keyword?.trim()) {
                ilike("title", "%" + params.keyword + "%")
            }
        }
    }

}

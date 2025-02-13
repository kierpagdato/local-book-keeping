package com.bookkeeping.book

import com.bookkeeping.book.Book.Status
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

    List<Serializable> saveAll(Collection<Book> bookList) {
        Book.saveAll(bookList)
    }

    List<Book> listByIdsAndStatus(Set<String> ids, Status status) {
        return Book.findAllByIdInListAndStatus(ids, status)
    }

    List<Book> listByIds(Set<String> ids) {
        return Book.findAllByIdInList(ids)
    }

    List<Book> list(GrailsParameterMap params) {
        return Book.createCriteria().list(params) {
            if(params?.keyword?.trim()) {
                ilike("title", "%" + params.keyword + "%")
            }
        }
    }

}

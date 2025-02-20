//package com.bookkeeping
//
//import com.bookkeeping.book.Book
//import com.bookkeeping.book.IBookDaoService
//import grails.gorm.transactions.Rollback
//import grails.testing.mixin.integration.Integration
//import org.hibernate.SessionFactory
//import spock.lang.Specification
//
//@Integration
//@Rollback
//class BookIntegrationSpec extends Specification {
//
//    IBookDaoService bookService
//    SessionFactory sessionFactory
//
//    private Long setupData() {
//        new Book(title: 'Sesame street', authorId: 1).save(flush: true, failOnError: true)
//        new Book(title: 'Night Agent', authorId: 2).save(flush: true, failOnError: true)
//        Book book = new Book(title: 'Night Agent 2', authorId: 3).save(flush: true, failOnError: true)
//        book.id
//    }
//
//    void "test get"() {
//        Long id = setupData()
//
//        expect:
//        Book book = bookService.get(id)
//        book.title == 'Night Agent 2'
//        book.authorId == 3
//    }
//
//    void "test list"() {
//        setupData()
//
//        when:
//        List<Book> bookList = bookService.list()
//
//        then:
//        bookList.size() == 3
//        bookList[0].authorId == 1
//        bookList[1].authorId == 2
//        bookList[2].authorId == 3
//    }
//
//
//    void "test delete"() {
//        Long id = setupData()
//
//        expect:
//        bookService.list().size() == 3
//
//        when:
//        bookService.delete(id)
//        sessionFactory.currentSession.flush()
//
//        then:
//        bookService.list().size() == 2
//    }
//
//    void "test save"() {
//        when:
//        Book book = new Book(title: 'Beyblade', authorId: 4)
//        bookService.save(book)
//
//        then:
//        book.id != null
//    }
//}

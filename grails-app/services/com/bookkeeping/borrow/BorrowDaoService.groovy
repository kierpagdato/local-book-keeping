package com.bookkeeping.borrow

import grails.gorm.PagedResultList
import grails.gorm.services.Service
import grails.gorm.transactions.Transactional
import grails.web.servlet.mvc.GrailsParameterMap
import org.hibernate.criterion.CriteriaSpecification

interface IBorrowDaoService {

    Borrow get(Serializable id)

    Long count()

    Borrow save(Borrow borrow)

    void delete(Serializable id)

}


@Transactional
@Service(Borrow)
abstract class BorrowDaoService implements IBorrowDaoService {

    List<Serializable> saveAll(Collection<Borrow> borrowList) {
        Borrow.saveAll(borrowList)
    }

    List<Borrow> listByTransactionId(String transactionId) {
        return Borrow.findAllByTransactionId(transactionId)
    }

    List<Borrow> listDistinct(GrailsParameterMap params) {
        return Borrow.createCriteria().list {
            resultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
            projections {
                groupProperty 'transactionId'
                min ('id', 'id')
                min ('transactionId', 'transactionId')
                min ('dateBorrowed','dateBorrowed')
                min ('dateReturned','dateReturned')
                min ('type','type')
                min ('status','status')
            }

            maxResults params.int('max')
            firstResult params.int('offset')
        }
    }

    Long countDistinct() {
        return Borrow.createCriteria().get {
            projections {
                countDistinct 'transactionId'
            }

        }
    }

}

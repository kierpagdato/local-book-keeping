package com.bookkeeping.borrow

import grails.gorm.services.Service
import grails.gorm.transactions.Transactional
import grails.web.servlet.mvc.GrailsParameterMap
import org.hibernate.criterion.CriteriaSpecification

import java.time.LocalDateTime

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

    List<Borrow> listDistinct(GrailsParameterMap params, Long userId) {
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

                if(!userId) {
                    min('borrower', 'borrower')
                }
            }
            findUserReceiptsClosure().call(delegate, userId)

            maxResults params.int('max')
            firstResult params.int('offset')

            if(params.sort && params.order) {
                order params.sort, params.order
            }
        }
    }

    Long countDistinct(Long userId) {
        return Borrow.createCriteria().get {
            projections {
                countDistinct 'transactionId'
            }

            findUserReceiptsClosure().call(delegate, userId)
        }
    }

    protected Closure findUserReceiptsClosure() {
        return { delegate, Long userId ->
            delegate.with {
                if (userId){
                    eq('borrower.id', userId)
                }
            }
        }
    }

}

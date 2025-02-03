package com.bookkeeping

import grails.gorm.services.Service
import grails.gorm.transactions.Transactional

interface IUserDaoService {

    User get(Serializable id)

    List<User> list(Map args)

    Long count()

    void delete(Serializable id)

    User save(User user)

}

@Transactional
@Service(User)
abstract class UserDaoService implements IUserDaoService {

}

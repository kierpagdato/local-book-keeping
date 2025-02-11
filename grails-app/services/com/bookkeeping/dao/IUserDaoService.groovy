package com.bookkeeping.dao

import com.bookkeeping.security.Role
import com.bookkeeping.security.User
import com.bookkeeping.security.UserRole
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

    User saveUser(User user) {
        User savedUser = save(user)

        Role role = Role.findByAuthority('ROLE_USER')

        UserRole.create(savedUser, role)

        return savedUser
    }

}

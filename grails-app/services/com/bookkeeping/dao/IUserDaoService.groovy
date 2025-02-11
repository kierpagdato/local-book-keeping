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

    User setRoles(User currentUser, List<String> roles) {

        log.debug "Setting new roles ${roles}"

        List<Role> oldRoles = currentUser.authorities as List
        List<Role> newRoles = Role.getAll(roles)

        List<Role> toRemove = oldRoles - newRoles
        List<Role> toAdd = newRoles - oldRoles

        for(Role r in toRemove) {
            boolean  res = UserRole.remove(currentUser, r)
            log.trace "Role removed ${r.text} result ${res}"
        }

        for(Role r in toAdd) {
            boolean  res = UserRole.create(currentUser, r)
            log.trace "Role added ${r.text} result ${res}"
        }

        return currentUser
    }
}

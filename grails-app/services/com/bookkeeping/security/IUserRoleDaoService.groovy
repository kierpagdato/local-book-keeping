package com.bookkeeping.security


import grails.gorm.services.Service
import grails.gorm.transactions.Transactional

interface IUserRoleDaoService {

    UserRole get(Serializable id)

    List<UserRole> list(Map args)

    Long count()

    void delete(Serializable id)

    void deleteByUserId(Serializable id)

    UserRole save(UserRole userRole)

}

@Transactional
@Service(UserRole)
abstract class UserRoleDaoService implements IUserRoleDaoService {

    void removeRoles(User user) {
        UserRole.removeAll(user)
    }

    User setRoles(User currentUser, List<String> roles) {

        log.debug "Setting new roles ${roles}"

        List<Role> oldRoles = currentUser.authorities as List
        List<Role> newRoles = Role.getAll(roles)

        List<Role> toRemove = oldRoles - newRoles
        List<Role> toAdd = newRoles - oldRoles

        for(Role r in toRemove) {
            boolean  res = UserRole.remove(currentUser, r)
            log.trace "Old role removed ${r.text} result ${res}"
        }

        for(Role r in toAdd) {
            boolean  res = UserRole.create(currentUser, r)
            log.trace "New role added ${r.text} result ${res}"
        }

        return currentUser
    }
}

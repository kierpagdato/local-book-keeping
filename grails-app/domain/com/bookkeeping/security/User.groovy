package com.bookkeeping.security

import com.bookkeeping.Borrow
import grails.compiler.GrailsCompileStatic
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@GrailsCompileStatic
@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class User implements Serializable {

    private static final long serialVersionUID = 1

    String username
    String password
    boolean enabled = true

    String email
    String firstName
    String lastName

    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

    Date dateCreated
    Date lastUpdated

    static hasMany = [borrow: Borrow, processedBorrow: Borrow]

    static mappedBy = [borrow: "borrower", processedBorrow: "processor"]

    static mapping = {
        password column: '`password`'
    }

    static constraints = {
        username blank: false, unique: true
        password blank: false, password: true
        firstName maxSize: 50, nullable: false, blank: false
        lastName maxSize: 50, nullable: false, blank: false
        email maxSize: 50, nullable: false, blank: false, email: true
    }

    Set<Role> getAuthorities() {
        (UserRole.findAllByUser(this) as List<UserRole>)*.role as Set<Role>
    }

}

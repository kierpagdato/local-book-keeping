package com.bookkeeping.my_user

import grails.plugin.springsecurity.userdetails.GrailsUser
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

class MyUserDetails extends GrailsUser {

    final String email
    final String firstName
    final String lastName

    MyUserDetails(String username, String password,
                  boolean enabled, boolean accountNonExpired,
                  boolean credentialsNonExpired, boolean accountNonLocked,
                  Collection<GrantedAuthority> authorities, long id,
                  String email, String firstName, String lastName) {

        super(username, password, enabled, accountNonExpired,
                credentialsNonExpired, accountNonLocked, authorities, id)

        this.email = email
        this.firstName = firstName
        this.lastName = lastName
    }

    boolean hasAuthority(String authority) {
        Set<SimpleGrantedAuthority> authorities = getAuthorities()

        for (SimpleGrantedAuthority grantedAuthority : authorities) {
            if (authority.equals(grantedAuthority.getAuthority())) {
                return true
            }
        }

        return false
    }
}

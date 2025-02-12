package com.bookkeeping.models

import grails.plugin.springsecurity.userdetails.GrailsUser
import org.springframework.security.core.GrantedAuthority
//todo the packaging should be by domain not kind: com.booking.my_user.MyUserDetails|MyUserDetailsService
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
}

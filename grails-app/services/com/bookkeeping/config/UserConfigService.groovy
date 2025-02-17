package com.bookkeeping.config

import grails.config.Config
import grails.core.support.GrailsConfigurationAware

class UserConfigService implements GrailsConfigurationAware {

    int userBorrowLimit

    @Override
    void setConfiguration(Config config) {
        this.userBorrowLimit = config.getRequiredProperty('bookkeeping.user.borrow-limit', Integer)
    }
}

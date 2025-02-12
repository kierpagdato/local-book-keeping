package com.bookkeeping.config

import grails.config.Config
import grails.core.support.GrailsConfigurationAware

//todo what is this for?
class InputConfigService implements GrailsConfigurationAware {

    Set<String> textareaFields

    @Override
    void setConfiguration(Config config) {
        this.textareaFields = config.getRequiredProperty('bookkeeping.fields.input.textarea', HashSet)
    }
}

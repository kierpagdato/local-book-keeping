package com.bookkeeping.components

import com.bookkeeping.config.InputConfigService
import com.bookkeeping.utils.MapUtils

/**
 * Stopped using fields plugin
 */
@Deprecated
class CustomInputTagLib {

    static namespace = "cin"

    InputConfigService inputConfigService

    Closure input = { attrsMap ->

        def writer = out

        boolean required = attrsMap["required"]?: false
        attrsMap.remove("required")

        if(attrsMap.name as String in inputConfigService.textareaFields) {

            def textValue = attrsMap.value
            attrsMap.remove("value")

            attrsMap << [rows: "3"]
            attrsMap << [class: "textarea " + attrsMap.class]

            writer << "<textarea ${MapUtils.mapToHtmlAttr(attrsMap)} ${required? 'required' : ''}>${textValue?: ''}</textarea>"
        } else {

            attrsMap << [type: "text"]
            attrsMap << [class: "input " + attrsMap.class]

            writer << "<input ${MapUtils.mapToHtmlAttr(attrsMap)} ${required? 'required' : ''}/>"
        }


    }

}

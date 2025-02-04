package com.bookkeeping.components

import grails.util.TypeConvertingMap
import org.grails.taglib.TagLibraryLookup
import org.grails.taglib.TagOutput
import org.grails.taglib.encoder.OutputContextLookupHelper

class BulmaTagLib {

    static namespace = "bl"

    TagLibraryLookup gspTagLibraryLookup

    Closure paginate = { attrsMap ->

        TypeConvertingMap attrs = (TypeConvertingMap) attrsMap

        def writer = out;

        def total = attrs.int('total') ?: 0
        def offset = params.int('offset') ?: 0
        def max = params.int('max')
        def maxSteps = (attrs.int('maxsteps') ?: 10)

        //if params offset and max are empty, use attribute values or else use default values
        if (!offset)
            offset = (attrs.int('offset') ?: 0)
        if (!max)
            max = (attrs.int('max') ?: 10)

        Map linkParams = [:]

        if (attrs.params instanceof Map)
            linkParams.putAll((Map)attrs.params)
        linkParams.offset = offset - max
        linkParams.max = max

        if (params.sort)
            linkParams.sort = params.sort
        if (params.order)
            linkParams.order = params.order

        Map linkTagAttrs = [:]
        def action
        if (attrs.containsKey('mapping')) {
            linkTagAttrs.mapping = attrs.mapping
            action = attrs.action
        } else {
            action = attrs.action ?: params.action
        }

        if (action)
            linkTagAttrs.action = action
        if (attrs.controller)
            linkTagAttrs.controller = attrs.controller
        if (attrs.id != null)
            linkTagAttrs.id = attrs.id

        linkTagAttrs.params = linkParams

        // determine paging variables
        def steps = maxSteps > 0
        int currentStep = ((offset / max) as int) + 1
        int firstStep = 1
        int lastStep = Math.round(Math.ceil(total / max)) as int

        writer << '<nav class="pagination is-centered" role="navigation" aria-label="pagination">'
        // display previous link when not on firststep unless omitPrev is true
        if (currentStep > firstStep && !attrs.boolean('omitPrev')) {
            linkTagAttrs.put('class', 'pagination-previous')
            linkParams.offset = offset - max
            writer << callLink((Map)linkTagAttrs.clone()) {
                (attrs.prev ?:  'Previous')
            }
        }

        // display next link when not on laststep unless omitNext is true
        if (currentStep < lastStep && !attrs.boolean('omitNext')) {
            linkTagAttrs.put('class', 'pagination-next')
            linkParams.offset = offset + max
            writer << callLink((Map)linkTagAttrs.clone()) {
                (attrs.next ?: 'Next page')
            }
        }

        // display steps when steps are enabled and laststep is not firststep
        if (steps && lastStep > firstStep) {
            writer << '<ul class="pagination-list">'
            linkTagAttrs.put('class', 'pagination-link')

            // determine begin and endStep paging variables
            int beginStep = currentStep - (Math.round(maxSteps / 2.0d) as int) + (maxSteps % 2)
            int endStep = currentStep + (Math.round(maxSteps / 2.0d) as int) - 1

            if (beginStep < firstStep) {
                beginStep = firstStep
                endStep = maxSteps
            }
            if (endStep > lastStep) {
                beginStep = lastStep - maxSteps + 1
                if (beginStep < firstStep) {
                    beginStep = firstStep
                }
                endStep = lastStep
            }

            // display firststep link when beginStep is not firststep
            if (beginStep > firstStep && !attrs.boolean('omitFirst')) {
                linkParams.offset = 0
                writer << '<li>'
                writer << callLink((Map)linkTagAttrs.clone()) {firstStep.toString()}
                writer << '</li>'
            }
            //show a gap if beginStep isn't immediately after firststep, and if were not omitting first or rev
            if (beginStep > firstStep + 1 && (!attrs.boolean('omitFirst') || !attrs.boolean('omitPrev')) ) {
                writer << '<li><span class="pagination-ellipsis">&hellip;</span></li>'
            }

            // display paginate steps
            (beginStep..endStep).each { int i ->
                if (currentStep == i) {
                    writer << "<li><a class=\"pagination-link is-current\">${i}</a></li>"
                } else {
                    linkParams.offset = (i - 1) * max
                    writer << '<li>'
                    writer << callLink((Map)linkTagAttrs.clone()) {i.toString()}
                    writer << '</li>'
                }
            }

            //show a gap if beginStep isn't immediately before firststep, and if were not omitting first or rev
            if (endStep+1 < lastStep && (!attrs.boolean('omitLast') || !attrs.boolean('omitNext'))) {
                writer << '<li><span class="pagination-ellipsis">&hellip;</span></li>'
            }
            // display laststep link when endStep is not laststep
            if (endStep < lastStep && !attrs.boolean('omitLast')) {
                linkParams.offset = (lastStep - 1) * max
                writer << '<li>'
                writer << callLink((Map)linkTagAttrs.clone()) { lastStep.toString() }
                writer << '</li>'
            }
            writer << '</ul>'
        }

        writer << '</nav>'
    }

    private callLink(Map attrs, Object body) {
        TagOutput.captureTagOutput(gspTagLibraryLookup, 'g', 'link', attrs, body, OutputContextLookupHelper.lookupOutputContext())
    }
}

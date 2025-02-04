package com.bookkeeping.utils

class MapUtils {

    static String mapToHtmlAttr(Map map) {
        def sb = ''

        for (item in map) {
            if(item.value != null)
                sb <<= item.key + '="' + item.value + '" '

        }
        return sb
    }
}

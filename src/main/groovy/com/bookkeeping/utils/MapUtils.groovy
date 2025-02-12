package com.bookkeeping.utils

class MapUtils {

    static String mapToHtmlAttr(Map map) {
        String sb = ''

        for (item in map) {
            if(item.value != null)
                sb <<= item.key + '="' + item.value + '" '

        }
        return sb
    }
}

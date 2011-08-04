package com.intelligrape.linksharing

class LinkResource extends Resource {
    String url;

    static constraints = {
        url(url: true, blank: false)
    }
    static mapping = {
        tablePerHierarchy false
        table "URLResources"
    }

    String toString() {
        return "${heading}"
    }
}

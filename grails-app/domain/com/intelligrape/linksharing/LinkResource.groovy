package com.intelligrape.linksharing

class LinkResource extends Resource {
    String url;

    static mapping = {
        tablePerHierarchy false
        table "URLResources"


    }


    static constraints = {

         url(url: true)

    }






}

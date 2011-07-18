package com.intelligrape.linksharing

class DocumentResource extends Resource {
    String uuid;
    String name;


    static mapping = {
        tablePerHierarchy false
        table 'DocResource'

    }
    static constraints = {


    }
}

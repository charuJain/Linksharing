package com.intelligrape.linksharing

class DocumentResource extends Resource {
    String uuid;
    String name;
    byte[] uploadedFile;

    static transients = ['uploadedFile']
    static mapping = {
        tablePerHierarchy false
        table 'DocResource'

    }
    static constraints = {
    }
}

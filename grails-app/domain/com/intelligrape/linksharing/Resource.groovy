package com.intelligrape.linksharing

class Resource {

    Topic topic;
    User createdBy;
    Date dateCreated;
    String heading;
     String summary;


    static mapping = {
        tablePerHierarchy false
       summary type: 'text'

    }
    static belongsTo = [Topic]

    //static hasMany = [userResources:UserResource]
    static constraints = {
        header(blank: false)

    }
}

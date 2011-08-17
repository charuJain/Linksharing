package com.intelligrape.linksharing

class Resource {

    Topic topic;
    User createdBy;
    Date dateCreated;
    String heading;
    String summary;

    static belongsTo = [Topic]

    static constraints = {
        heading(blank: false)
        summary(blank: false)
    }

     static mapping = {
        tablePerHierarchy false
        summary type: 'text'
    }

    String toString(){
            return "${heading}"
        }

     def afterInsert = {
        User user = this.createdBy
        UserResource userResource = new UserResource(resource: this, user: user)
        user.addToUserResources(userResource)
    }

}

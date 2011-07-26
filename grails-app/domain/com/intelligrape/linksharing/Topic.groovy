package com.intelligrape.linksharing

class Topic {

    String name;
    boolean isPrivate = false;



   // Date CreatedOn;

    //List<Invitation>invitations=[]
    //List<Resource>resources=[]

    static belongsTo = [createdBy:User]

    static hasMany = [userTopics: UserTopic, invitations: Invitation, resources: Resource]
    //,resources:Resource,invitations:Invitation
    static constraints = {
        name(unique: true,blank: false)


    }
}

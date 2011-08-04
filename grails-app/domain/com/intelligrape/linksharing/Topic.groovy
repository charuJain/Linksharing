package com.intelligrape.linksharing

class Topic {

    String name;
    boolean isPrivate = false;
    Date dateCreated;

    static belongsTo = [createdBy: User]

    static hasMany = [userTopics: UserTopic, invitations: Invitation, resources: Resource]

    static constraints = {
        name(unique: true, blank: false)
    }

    def afterInsert = {
        User user = this.createdBy
        UserTopic userTopic = new UserTopic(topic: this, user: user)
        user.addToUserTopics(userTopic)
        this.addToUserTopics(userTopic)
    }


}

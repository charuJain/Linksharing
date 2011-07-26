package com.intelligrape.linksharing

class UserTopic {

    static belongsTo = [user: User, topic: Topic]
    static constraints = {
    }
}

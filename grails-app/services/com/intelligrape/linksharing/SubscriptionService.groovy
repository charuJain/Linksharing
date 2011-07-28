package com.intelligrape.linksharing

class SubscriptionService {

    static transactional = true

    def subscribeTopic(User user, Topic topic) {
        UserTopic userTopic = new UserTopic(user: user, topic: topic)

        println user
        println topic
        user.addToUserTopics(userTopic)
        topic.addToUserTopics(userTopic)
        topic.resources.each {resource ->
        UserResource userResource = new UserResource(user: user, resource: resource, isRead: false)
        user.addToUserResources(userResource)
        }

        //Populate UserResource table with Resources belonging to the subscribed topic


    }
}

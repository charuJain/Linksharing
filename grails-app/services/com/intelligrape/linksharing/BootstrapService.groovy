package com.intelligrape.linksharing

class BootstrapService {

    static transactional = true

    def subscriptionService

    void addUser() {
        User user1 = new User(username: "admin", password: "password", confirmPassword: "password", name: "Gaurav",                             \
                                             phoneNumber: 123456789, address: "new delhi", email: "admin@intelligrape.com", age: 34)
        User user2 = new User(username: "test2", password: "password", confirmPassword: "password", name: "Charu",                             \
                                             phoneNumber: 123456789, address: "new delhi", email: "charu@intelligrape.com", age: 24)
        User user3 = new User(username: "test3", password: "password", confirmPassword: "password", name: "Charu",                             \
                                             phoneNumber: 123456789, address: "new delhi", email: "333@intelligrape.com", age: 45)
        User user4 = new User(username: "test4", password: "password", confirmPassword: "password", name: "Charu",                             \
                                             phoneNumber: 123456789, address: "new delhi", email: "444@intelligrape.com", age: 35)
        User user5 = new User(username: "test5", password: "password", confirmPassword: "password", name: "Charu",                             \
                                             phoneNumber: 123456789, address: "new delhi", email: "555@intelligrape.com", age: 45)
        User user6 = new User(username: "test6", password: "password", confirmPassword: "password", name: "Charu",                             \
                                             phoneNumber: 123456789, address: "new delhi", email: "666@intelligrape.com", age: 52)




        if (user1.validate()) {
            user1.save(flush: true, validate: false)

        }
        else {
            user1.errors.allErrors.each {
                println it
            }
        }
        user2.save(flush: true)
        user2.errors.allErrors.each {
            println it
        }
        user3.save(flush: true)
        user3.errors.allErrors.each {
            println it
        }
        user4.save(flush: true)
        user4.errors.allErrors.each {
            println it
        }
        user5.save(flush: true)
        user5.errors.allErrors.each {
            println it
        }
        user6.save(flush: true)
        user6.errors.allErrors.each {
            println it



        }
         addTopic(user2)
    }

    void addTopic(User user) {
        ('A'..'Z').each {
            new Topic(isPrivate: false, createdBy: user, name: "${it}").save(flush: true, failOnError: true)

        }

    }

    void subscribedTopics() {
        (3..6).each { num ->
            ('A'..'B').each { topicName ->
                subscriptionService.subscribeTopic(User.findByUsername("test${num}"), Topic.findByName("${topicName}"))
            }
        }
    }

//    Topic topic1 = new Topic(isPrivate: false, createdBy: User.findByUsername('test6'), name: "Grails")
    //        Topic topic2 = new Topic(isPrivate: false, createdBy: User.findByUsername('test2'), name: "ADA")
    //        Topic topic3 = new Topic(isPrivate: false, createdBy: User.findByUsername('test3'), name: "Groovy")
    //        Topic topic4 = new Topic(isPrivate: false, createdBy: User.findByUsername('test4'), name: "Maths")
    //        Topic topic5 = new Topic(isPrivate: false, createdBy: User.findByUsername('test5'), name: "C++")
    //
    //        topic1.save(flush: true)
    //        topic1.errors.allErrors.each {
    //            println it
    //        }
    //        topic2.save(flush: true)
    //        topic2.errors.allErrors.each {
    //            println it
    //        }
    //
    //        topic3.save(flush: true)
    //        topic3.errors.allErrors.each {
    //            println it
    //        }
    //        topic4.save(flush: true)
    //        topic4.errors.allErrors.each {
    //            println it
    //        }
    //
    //        topic5.save(flush: true)
    //        topic5.errors.allErrors.each {
    //            println it
    //        }
    //
    //
    //
    //
    //        UserTopic userTopic6 = new UserTopic(user: User.findByUsername('test2'), topic: topic1)
    //        userTopic6.save()
    //        UserTopic userTopic2 = new UserTopic(user: User.findByUsername('test3'), topic: topic5)
    //        userTopic2.save()
    //        UserTopic userTopic3 = new UserTopic(user: User.findByUsername('test2'), topic: topic4)
    //        userTopic3.save()
    //        UserTopic userTopic4 = new UserTopic(user: User.findByUsername('test3'), topic: topic2)
    //        userTopic4.save()
    //        UserTopic userTopic5 = new UserTopic(user: User.findByUsername('test4'), topic: topic2)
    //        userTopic5.save()
    //        UserTopic userTopic7 = new UserTopic(user: User.findByUsername('test4'), topic: topic3)
    //        userTopic7.save()
    //        UserTopic userTopic8 = new UserTopic(user: User.findByUsername('test5'), topic: topic3)
    //        userTopic8.save()
    //        UserTopic userTopic9 = new UserTopic(user: User.findByUsername('test5'), topic: topic5)
    //        userTopic9.save()
    //         UserTopic userTopic10 = new UserTopic(user: User.findByUsername('test2'), topic: topic5)
    //        userTopic10.save()
    //
    //        User.findByUsername('test2').addToUserTopics(new UserTopic(topic: topic3))
    //        User.findByUsername('test2').addToUserTopics(new UserTopic(topic: topic5))


    void createResources() {
        Topic.list().each {Topic topic ->
            (1..10).each {
                topic.addToResources(new LinkResource(topic: topic, createdBy: User.get(2), heading: "Grails Heading",                         \
                                     summary: "Grails summary", url: "http://www.google.com"))
            }
        }
        println "created resource"
    }

    void createDocumentResource() {
        (1..5).each {
            Topic.list().each {Topic topic ->
                DocumentResource documentResource = new DocumentResource(topic: topic, createdBy: User.get(3), heading: "New Document",
                        summary: "User 3 Document", name: "New Resource ${it}", uuid: UUID.randomUUID().toString())

                documentResource.save(flush: true)
                topic.addToResources(documentResource)
            }
        }

        println "Created Document resource"
    }

    void createRead() {

        (1..10).each {
            UserResource userResource = new UserResource(user: User.get(2), resource: Resource.get(it), isRead: true)
            userResource.save(flush: true)
        }
        println "resources added to read resource"
    }

    void markUnread() {
        (1..3).each {
            UserResource userResource = UserResource.get(it)
            userResource.isRead = false
        }
        println "Marked unread"
    }

    void printUnread() {
        UserResource.findAllByIsReadAndUser(false, User.get(2)).each {
            println it.resource;
        }
        println "Marked read"
    }


}

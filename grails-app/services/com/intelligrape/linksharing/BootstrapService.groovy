package com.intelligrape.linksharing

class BootstrapService {

    static transactional = true

    def subscriptionService

    void addUser() {
        User user1 = new User(username: "admin", password: "password", confirmPassword: "password", name: "Gaurav",                              \
                                              phoneNumber: 123456789, address: "new delhi", email: "admin@intelligrape.com", age: 34, isAdmin: true)
        User user2 = new User(username: "test2", password: "password", confirmPassword: "password", name: "Charu",                              \
                                              phoneNumber: 123456789, address: "new delhi", email: "charu@intelligrape.com", age: 24)
        User user3 = new User(username: "test3", password: "password", confirmPassword: "password", name: "Charu",                              \
                                              phoneNumber: 123456789, address: "new delhi", email: "333@intelligrape.com", age: 45)
        User user4 = new User(username: "test4", password: "password", confirmPassword: "password", name: "Charu",                              \
                                              phoneNumber: 123456789, address: "new delhi", email: "444@intelligrape.com", age: 35)
        User user5 = new User(username: "test5", password: "password", confirmPassword: "password", name: "Charu",                              \
                                              phoneNumber: 123456789, address: "new delhi", email: "555@intelligrape.com", age: 45)
        User user6 = new User(username: "test6", password: "password", confirmPassword: "password", name: "Charu",                              \
                                              phoneNumber: 123456789, address: "new delhi", email: "666@intelligrape.com", age: 52)




        if (user1.validate()) {
            user1.save(flush: true, validate: false)

        }
        else {
            user1.errors.allErrors.each {
            }
        }
        user2.save(flush: true)
        user2.errors.allErrors.each {
        }
        user3.save(flush: true)
        user3.errors.allErrors.each {
        }
        user4.save(flush: true)
        user4.errors.allErrors.each {
        }
        user5.save(flush: true)
        user5.errors.allErrors.each {
        }
        user6.save(flush: true)
        user6.errors.allErrors.each {


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


    void createResources() {
        Topic.list().each {Topic topic ->
            (1..10).each {
                topic.addToResources(new LinkResource(topic: topic, createdBy: User.get(2), heading: "Grails Heading",                          \
                                      summary: "Grails summary", url: "http://www.google.com"))

            }
        }
    }

    void createDocumentResource() {
        Topic.list().each {Topic topic ->
            (1..5).each {
                topic.addToResources(new DocumentResource(topic: topic, createdBy: User.get(2), heading: "New Document",
                        summary: "User 3 Document", name: "New Resource ${it}", uuid: UUID.randomUUID().toString()))
            }
        }

    }

    void createRead() {
        (1..10).each {
            UserResource userResource = new UserResource(user: User.get(2), resource: Resource.get(it), isRead: true)
            userResource.save(flush: true)
        }
    }

    void markUnread() {
        (1..3).each {
            UserResource userResource = UserResource.get(it)
            userResource.isRead = false
        }
    }

    void printUnread() {
        UserResource.findAllByIsReadAndUser(false, User.get(2)).each {
        }
    }
}

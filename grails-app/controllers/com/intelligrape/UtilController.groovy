package com.intelligrape

import com.intelligrape.linksharing.User

import com.intelligrape.linksharing.Topic
import com.intelligrape.linksharing.User
import com.intelligrape.linksharing.UserTopic
import com.intelligrape.linksharing.LinkResource
import com.intelligrape.linksharing.UserResource
import com.intelligrape.linksharing.Resource
import linksharing.CharuJob
//import linksharing.TopicTests

class UtilController {


    def index = {

        User user1 = new User(username: "test122", password: "password", confirmPassword: "password", name: "Gaurav",
                phoneNumber: 123456789, address: "new delhi", email: "111@xyz.com", age: 24)
        User user2 = new User(username: "test222", password: "password", confirmPassword: "password", name: "Charu",
                phoneNumber: 123456789, address: "new delhi", email: "2222@def.com", age: 45)
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
        Topic topic1 = new Topic(isPrivate: false, createdBy: user1, name: "Grails")
        Topic topic2 = new Topic(isPrivate: false, createdBy: user2, name: "ADA")
        topic1.save(flush: true)
        topic1.errors.allErrors.each {
            println it
        }
        topic2.save(flush: true)
        topic2.errors.allErrors.each {
            println it
        }

        UserTopic userTopic = new UserTopic(user: user1, topic: topic1)
        userTopic.save()
        UserTopic userTopic1 = new UserTopic(user: user2, topic: topic2)
        userTopic1.save()

        render "The object has been added"
//
        //
    }


    def createResources = {
        Topic.list().each {Topic topic ->
            (1..10).each {
                topic.addToResources(new LinkResource(topic: topic, createdBy: User.get(1), heading: "Grails Heading",
                        summary: "Grails summary", url: "http://www.google.com"))
            }
        }
        render "success"
    }

    def createRead = {
        User user = User.get(1)

        (1..10).each {
            user.addToUserResources(new UserResource(resource: Resource.get(it), isRead: true))
        }
        render "resources added to read resource"
    }

    def markUnread = {
        (1..3).each {
            UserResource userResource = UserResource.get(it)
            userResource.isRead = false
        }
    }


    def printUnread = {                  //    def createRead = {
        User user = User.get(1)

        (1..10).each {
            user.addToUserResources(new UserResource(resource: Resource.get(it), isRead: true))
        }
        render "resources added to read resource"

        UserResource.findAllByIsReadAndUser(false, User.get(1)).each {UserResource userResource ->
            println userResource.resource
        }
    }


    def triggerjob ={
        CharuJob.triggerNow()
       render "job triggered"

    }



    def sendmail={

   sendMail {
  to "gauravs@intelligrape.com"
  subject "Hello John"
  html '<b>Hello</b> World'
}

    }

}

//
//
//    def create = {
//        println 'created objects'
//
//        User user = new User(name: 'charu', username: 'charu', password: 'charu', confirmPassword: 'charu', address: 'hno44', email: 'abcdeeeeeee@gmail.com', age: 66);
//        user.save(flush: true)
//        printErrors(user)
//
//        Topic topic = new Topic(name: 'english', createdBy: user)
//        topic.save(flush: true)
//        printErrors(topic)
//
//        User user1 = new User(name: 'akshat', username: 'akshat', password: 'jainakshat', confirmPassword: 'jainakshat', address: 'hno446', email: 'eee@gmail.com', age: 64);
//        user1.save(flush: true)
//        printErrors(user1)
//
//        Topic topic1 = new Topic(name: 'HIndi', createdBy:user1)
//        topic1.save(flush: true)
//        printErrors(topic1)
//
//        UserTopic userTopic = new UserTopic(topic: topic1, user: user1)
//        userTopic.save()
//        printErrors(userTopic)
//        render "success"
//    }
//
//
//    def test = {
//        User user = new User(name: 'xyz', username: 'charu', password: 'charu', confirmPassword: 'charu', address: 'hno44', email: 'xat@gmail.com', age: 66);
//        user.save(flush: true)
//        Topic topic1 = new Topic(name: 'hindi', createdBy: user)
//        topic1.save(flush: true)
//        if (topic1.validate()) {
//            topic1.save(flush: true)
//
//        }
//        else {
//            topic1.errors.allErrors.each {
//                println it
//            }
//        }
//    }
//
//   void printErrors(Object object){
//       object.errors.allErrors.each{
//           println it
//       }
//   }
//
//
//
//
//    def add={
//
//      Topic.list().each { Topic topic
//          topic.addToResources(new LinkResource(user:User.get(1),header:'grails',url:'http://www.gmail.com'))
//
//      }
//         Topic topic;
//    User createdBy;
//    Date dateCreated;
//    String header
//    }


//package com.intelligrape.linksharing

//class UtilController {
//
//    def index = {
//        User user1 = new User(username: "test122", password: "password", confirmPassword: "password", name: "Gaurav",   \
//                   phoneNumber: 123456789, address: "new delhi", email: "111@xyz.com")
//                User user2 = new User(username: "test222", password: "password", confirmPassword: "password", name: "Charu",   \
//                   phoneNumber: 123456789, address: "new delhi", email: "2222@def.com")
//        if (user1.validate()) {
//            user1.save(flush: true, validate: false)
//
//        }
//        else {
//            user1.errors.allErrors.each {
//                println it
//            }
//        }
//        user2.save(flush: true)
//        user2.errors.allErrors.each {
//            println it
//        }
//        Topic topic1 = new Topic(isPrivate: false, createdBy: user2, name: "Grails")
//        Topic topic2 = new Topic(isPrivate: false, createdBy: user2, name: "ADA")
//                topic1.save(flush: true)
//                topic1.errors.allErrors.each {
//                    println it
//                 }
//                topic2.save(flush: true)
//                topic2.errors.allErrors.each {
//                    println it
//                }
//
//        UserTopic userTopic = new UserTopic(user: user2, topic: topic1)
//        userTopic.save()
//        render "The object has been added"
//
//    }

//    def createResources = {
//        Topic.list().each{Topic topic->
//            (1..10).each{
//            topic.addToResources(new LinkResource(topic: topic, createdBy: User.get(1), heading: "Grails Heading", \
//         summary: "Grails summary", url: "http://www.google.com"))
//        }
//        }
//  }
//    def createRead = {
//        User user =User.get(1)
//
//        (1..10).each{
//            user.addToResources(new UserResource(resource: Resource.get(it), isRead: true))
//        }
//        render "resources added to read resource"
//    }
//    def markUnread = {
//        (1..3).each{
//            UserResource userResource = UserResource.get(it)
//            userResource.isRead=false
//        }
//    }
//    def printUnread = {                  //    def createRead = {
//        User user =User.get(1)
//
//        (1..10).each{
//            user.addToResources(new UserResource(resource: Resource.get(it), isRead: true))
//        }
//        render "resources added to read resource"
//    }
//        UserResource.findAllByIsReadAndUser(false, User.get(1)).each{
//            println it.resource;
//        }
//    }
//}
//
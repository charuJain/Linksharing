package com.intelligrape.linksharing

import com.sun.org.apache.xalan.internal.xsltc.compiler.Template
import org.springframework.ui.Model

class LinkSharingTagLib {

    static namespace = "ls"

    def myDateFormat = { attrs ->

        Date myDate = attrs['date']
        out << myDate.format('MMM /dd/yyy')
    }




    def states = { attrs, body ->
        List statelist = ["delhi", "haryana", 'guj', 'gujrat']
        out << g.render(template: "/user/states", model: [statelist: statelist])


    }

    def isSubscribed = {attrs, body ->
        Integer topicCount = UserTopic.countByUserAndTopic(User.get(session.currentUser), attrs['topic'])
        if (!topicCount) {
            out << body()


        }
    }
    def topics = {attrs, body ->

        out << g.render(template: "/topic/topiclist", model: [topicInstanceList: Topic.list(params), topicInstanceTotal: Topic.count()])


    }
}

//    def highestSubscribedPublicTopic = {
//
//        def userTopics = UserTopic.createCriteria().list() {
//            projections {
//                groupProperty("topic")
//                count("user")
//            }
//            'topic' {
//                eq('isPrivate', false);
//            }
//
//        } as List
//    userTopics=userTopics.sort{it.last()}.reverse()
//        out << userTopics
//
//           }

//
//    def mostReadItems={
//
//
//        def userResources = UserResource.createCriteria().list() {
//            projections {
//              groupProperty("resource")
//              count("user")
//            }
//             eq("isRead",true)
//          userResources  = userResources.sort{it.last()}.reverse()
//
//            out<< userResources
//
//        }
//    }
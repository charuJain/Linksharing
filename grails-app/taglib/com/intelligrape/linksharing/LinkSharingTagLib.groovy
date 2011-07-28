package com.intelligrape.linksharing

import com.sun.org.apache.xalan.internal.xsltc.compiler.Template
import org.springframework.ui.Model

class LinkSharingTagLib {

    static namespace = "ls"

    def myDateFormat = { attrs ->
        Date myDate = attrs['date']
        out << myDate.format('MMM ,dd/yyyy')
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


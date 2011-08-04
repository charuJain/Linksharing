package com.intelligrape.linksharing

class MostSubscribedService {

    def highestSubscribedTopic(Integer max, Integer offset) {
        offset = offset ?: 0
        max = Math.min(max ? max : 10, 100)
        List<UserTopic> userTopics = UserTopic.createCriteria().list() {
            projections {
                groupProperty("topic")
                count("user")
            }
            'topic' {
                eq('isPrivate', false);
            }
            maxResults(max)
            firstResult(offset)
        }
        userTopics = userTopics ? userTopics.sort {it.last()}.reverse() : []
        return userTopics

    }

    def topicListTotal() {
        Integer mostSubscribedTopicsTotal = UserTopic.createCriteria().get {
            projections {
                countDistinct("topic")
            }
            topic {
                eq('isPrivate', false)
            }
        }
        return mostSubscribedTopicsTotal
    }


    def mostReadResources(Integer max, Integer offset) {
        offset = offset ?: 0
        max = Math.min(max ? max : 10, 100)
        List<UserResource> userResources = UserResource.createCriteria().list() {
            projections {
                groupProperty("resource")
                count("user")
            }
            eq("isRead", true)
            maxResults(max)
            firstResult(offset)
        }
        userResources = userResources.sort {it.last()}.reverse()
        return userResources
    }

    def resourceListTotal() {
        Integer mostReadResourcesTotal = UserResource.createCriteria().get() {
            projections {
                countDistinct("resource")
            }
            eq("isRead", true)
        }
        return mostReadResourcesTotal
    }

}
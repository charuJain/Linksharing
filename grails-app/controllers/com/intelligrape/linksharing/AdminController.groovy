package com.intelligrape.linksharing

class AdminController {

    def stats = {
        [usercount: User.count(), topiccount: Topic.count(), invitationcount: Invitation.count()]
    }

    def userInformationPopulate = {
        params.max = Math.min(params.int('max') ?: 5, 100)
        List<User> userList = User.list(params)
        Integer userTotal = User.count()
        render(template: "userInformationTable", model: [users: userList, userTotal: userTotal])
    }

    def topicInformationPopulate = {
        params.max = Math.min(params.int('max') ?: 5, 100)
        List<Topic> topicList = Topic.list(params)
        Integer topicTotal = Topic.count()
        render(template: "topicInformationPopulate", model: [topics: topicList, topicTotal: topicTotal])
    }

    def resourceInformationPopulate = {
        params.max = Math.min(params.int('max') ?: 5, 100)
        List<Resource> resourceList = Resource.list(params)
        Integer resourceTotal = Resource.count()
        render(template: "resourceInformationTable", model: [resources: resourceList, resourceTotal: resourceTotal])
    }
}

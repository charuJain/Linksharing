package com.intelligrape.linksharing

class UserTopicController {

    def subscriptionService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def subscribe = {
        User user = User.get(session.currentUser)
        Topic topic = Topic.get(params.id)
        UserTopic userTopic = new UserTopic(user: user, topic: topic)
        user.addToUserTopics(userTopic)
        topic.addToUserTopics(userTopic)
        redirect(controller: "user", action: "dashboard")
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [userTopicInstanceList: UserTopic.list(params), userTopicInstanceTotal: UserTopic.count()]
    }

    def create = {
        UserTopic userTopicInstance = new UserTopic()
        userTopicInstance.properties = params
        return [userTopicInstance: userTopicInstance]
    }

    def save = {
        if (subscriptionService.subscribeTopic(User.get(params.user.id), Topic.get(params.topic.id))) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'userTopic.label', default: 'UserTopic'), params.topic.id])}"
            redirect(action: "show", id: params.topic.id)
        }
        else {
            // render(view: "create", model: [userTopicInstance: userTopicInstance])
            redirect(controller: 'topic', action: 'list', params: ['searchText': params.searchText])

        }
    }

    def show = {
        UserTopic userTopicInstance = UserTopic.get(params.id)
        if (userTopicInstance) {
            User user = User.get(session.currentUser)
            [userTopicInstance: userTopicInstance, user: user]

        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'userTopic.label', default: 'UserTopic'), params.id])}"
            redirect(action: "list")

        }
    }

    def edit = {
        UserTopic userTopicInstance = UserTopic.get(params.id)
        if (userTopicInstance) {
            render(view: 'edit', model: [userTopicInstance: userTopicInstance])
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'userTopic.label', default: 'UserTopic'), params.id])}"
            redirect(action: "list")

        }
    }

    def update = {
        UserTopic userTopicInstance = UserTopic.get(params.id)
        if (userTopicInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (userTopicInstance.version > version) {

                    userTopicInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'userTopic.label', default: 'UserTopic')] as Object[], "Another user has updated this UserTopic while you were editing")
                    render(view: "edit", model: [userTopicInstance: userTopicInstance])
                    return
                }
            }
            userTopicInstance.properties = params
            if (!userTopicInstance.hasErrors() && userTopicInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'userTopic.label', default: 'UserTopic'), userTopicInstance.id])}"
                redirect(action: "show", id: userTopicInstance.id)
            }
            else {
                render(view: "edit", model: [userTopicInstance: userTopicInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'userTopic.label', default: 'UserTopic'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        UserTopic userTopicInstance = UserTopic.get(params.id)
        if (userTopicInstance) {
            try {
                userTopicInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'userTopic.label', default: 'UserTopic'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'userTopic.label', default: 'UserTopic'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'userTopic.label', default: 'UserTopic'), params.id])}"
            redirect(action: "list")
        }
    }
}

package com.intelligrape.linksharing

import grails.converters.JSON

class UserController {

    def mostSubscribedService
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    def dashboard = {
        //params.max = 2
        User user = User.get(session.currentUser)
        List<UserTopic> topics = UserTopic.findAllByUser(user, params)
        Integer topicCount = UserTopic.countByUser(user)
//        List<UserTopic> userTopics = mostSubscribedService.highestSubscribedTopic()
        List<UserResource> userResources = mostSubscribedService.mostReadResources()
        [user1: user, topics: topics, topicCount: topicCount, userResources: userResources]
    }

    def resourcePopulate = {
        params.max = Math.min(params.int('max') ?: 5, 100)
        User user = User.get(session.currentUser)
        List<UserResource> resources = UserResource.findAllByUserAndIsRead(user, false, params);
        Integer resourceTotal = UserResource.countByUserAndIsRead(user, false)
        render(template: "resourceTable", model: [resources: resources, resourceTotal: resourceTotal])
    }

    def highestSubscribedTopics = {
        List<UserTopic> userTopics = mostSubscribedService.highestSubscribedTopic(params.max ? params.int('max') : 10, params.offset ? params.int('offset') : 0)
        Integer mostSubscribedTopicTotal = mostSubscribedService.topicListTotal()
        render(template: "highestSubscribedTopicTable", model: [userTopics: userTopics, mostSubscribedTopicTotal: mostSubscribedTopicTotal])
    }


    def logout = {
        session.invalidate()
        redirect(action: "loginHandler")

    }

    def loginHandler = {
        User user = User.findByUsernameAndPassword(params.username, params.password)
        if (user) {
            session['currentUser'] = user.id
            if (user.username == 'admin') {
                redirect(controller: 'admin', action: 'stats')
            }
            else {
                redirect(controller: 'user', action: 'dashboard')
            }
        }
        else {
            flash.message = "user not found or invalid password"
            render(view: 'login')
        }
    }

    def registerHandler = {
        if (request.method == 'POST') {
            User user = new User(params);
            if (user.save()) {
                flash.message = "you are successfully registered LOg in now!"
                render(view: 'login')
            }
            else {
                render(view: 'register', model: [userInstance: user])

            }
        } else {
            flash.message = null
            render(view: 'register')
        }
    }
    def verifyUser = {
        println params
        User user = User.findByUsernameAndPassword(params.username, params.password)
        if (user) {
            session['currentUser'] = user
            render 'logged in successfuly'
        }
        else {
            render 'not found'
        }
    }


    def index = {
        redirect(action: "loginHandler")
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [userInstanceList: User.list(params), userInstanceTotal: User.count()]
    }

    def create = {
        User userInstance = new User()
        userInstance.properties = params
        return [userInstance: userInstance]
    }

    def save = {
        User userInstance = new User(params)
        if (userInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])}"
            redirect(action: "show", id: userInstance.id)

        }
        else {
            render(view: "create", model: [userInstance: userInstance])
        }
    }

    def show = {
        User userInstance = User.get(params.id)
        if (userInstance) {
            render(view: 'show', model: [userInstance: userInstance])
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
            redirect(action: "list")
        }
    }

    def edit = {

        User userInstance = User.get(params.id)
        println "2 ${User.list()*.id}-----${params.id}"
        if (userInstance) {
            render(view: 'edit', model: [userInstance: userInstance])

        }
        else {

            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])}"

            redirect(action: "list")

        }
    }

    def update = {
        User userInstance = User.get(params.id)
        if (userInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (userInstance.version > version) {

                    userInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'user.label', default: 'User')] as Object[], "Another user has updated this User while you were editing")
                    render(view: "edit", model: [userInstance: userInstance])
                    return
                }
            }
            userInstance.properties = params
            if (!userInstance.hasErrors() && userInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])}"
                redirect(action: "show", id: userInstance.id)
            }
            else {
                render(view: "edit", model: [userInstance: userInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        User userInstance = User.get(params.id)
        if (userInstance) {
            try {
                userInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
            redirect(action: "list")
        }
    }


}
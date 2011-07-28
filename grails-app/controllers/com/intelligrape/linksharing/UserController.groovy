package com.intelligrape.linksharing

import grails.converters.JSON

//import org.xhtmlrenderer.css.parser.property.PrimitivePropertyBuilders.PaddingRight

class UserController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

   def alreadyExisting={
     User user=User.findByEmail(params.email)
     if(user){
        render false
     }
     else
     render true

   }

    def dateof = {

        println params
        log.info(params)
        String message = ""
        User user = User.findByUsernameAndPassword(params.username, params.password)
        if (user) {
            message = "you exist"
        }
        else
            message = "you are not a valid user"
            render([message: message] as JSON)
        }


    def dashboard = {
        params.max = 2
        User user = User.get(session.currentUser)
        List<UserResource> resources = user.userResources as List;
        //List<UserTopic> topics = user.userTopics as List;
        List<UserTopic> topics = UserTopic.findAllByUser(user, params)
        Integer topicCount = UserTopic.countByUser(user)
        def userTopics = UserTopic.createCriteria().list() {
            projections {
                groupProperty("topic")
                count("user")
            }
            'topic' {
                eq('isPrivate', false);
            }
        } as List
        userTopics = userTopics.sort {it.last()}.reverse()
        def userResources = UserResource.createCriteria().list() {
            projections {
                groupProperty("resource")
                count("user")
            }
            eq("isRead", true)
        }
        userResources = userResources.sort {it.last()}.reverse()
        [user1: user, resources: resources.findAll {!it.isRead}, topics: topics, topicCount: topicCount, userTopics: userTopics, userResources: userResources]
        //render(view: 'dashboard', model: [user1: user])
    }

    def logout = {
        session.invalidate()
        redirect(action: "loginHandler")
    }

    def loginHandler = {

        println "2"
        User user = User.findByUsernameAndPassword(params.username, params.password)

        println "8"
        if (user) {
            println "3"
            session['currentUser'] = user.id

            if (user.username == 'admin') {
                println "4"
                redirect(controller: 'admin', action: 'stats')
            }
            else {

                println "5"
                redirect(controller: 'user', action: 'dashboard')
            }
        }
        else {
            println "6"
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
        def userInstance = new User()
        userInstance.properties = params
        return [userInstance: userInstance]
    }

    def save = {
        def userInstance = new User(params)
        if (userInstance.save(flush: true))


        {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])}"
            redirect(action: "show", id: userInstance.id)


        }
        else {
            render(view: "create", model: [userInstance: userInstance])
        }
    }

    def show = {
        def userInstance = User.get(params.id)
        if (userInstance) {
            render(view: 'show', model: [userInstance: userInstance])
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
            redirect(action: "list")
        }
    }

    def edit = {

        def userInstance = User.get(params.id)
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
        def userInstance = User.get(params.id)
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
        def userInstance = User.get(params.id)
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
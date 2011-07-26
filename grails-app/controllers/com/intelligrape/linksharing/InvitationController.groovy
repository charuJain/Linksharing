package com.intelligrape.linksharing

class InvitationController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def handler = {

        Topic topicToSend = Topic.get(params.id)
        User sender = User.get(session.currentUser.toLong())
        println params.name
        println topicToSend
        [from: sender, topic: topicToSend]

        //render("invitations sent")

    }

    def index = {
        redirect(action: "click", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [invitationInstanceList: Invitation.list(params), invitationInstanceTotal: Invitation.count()]
    }

    def create = {
        def invitationInstance = new Invitation()
        invitationInstance.properties = params
        return [invitationInstance: invitationInstance]
    }

    def save = {
        def invitationInstance = new Invitation(params)
        if (invitationInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'invitation.label', default: 'Invitation'), invitationInstance.id])}"
            redirect(action: "show", id: invitationInstance.id)
        }
        else {
            render(view: "create", model: [invitationInstance: invitationInstance])
        }
    }

    def show = {
        def invitationInstance = Invitation.get(params.id)
        if (!invitationInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'invitation.label', default: 'Invitation'), params.id])}"
            redirect(action: "list")
        }
        else {
            [invitationInstance: invitationInstance]
        }
    }

    def edit = {
        def invitationInstance = Invitation.get(params.id)
        if (!invitationInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'invitation.label', default: 'Invitation'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [invitationInstance: invitationInstance]
        }
    }

    def update = {
        def invitationInstance = Invitation.get(params.id)
        if (invitationInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (invitationInstance.version > version) {

                    invitationInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'invitation.label', default: 'Invitation')] as Object[], "Another user has updated this Invitation while you were editing")
                    render(view: "edit", model: [invitationInstance: invitationInstance])
                    return
                }
            }
            invitationInstance.properties = params
            if (!invitationInstance.hasErrors() && invitationInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'invitation.label', default: 'Invitation'), invitationInstance.id])}"
                redirect(action: "show", id: invitationInstance.id)
            }
            else {
                render(view: "edit", model: [invitationInstance: invitationInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'invitation.label', default: 'Invitation'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def invitationInstance = Invitation.get(params.id)
        if (invitationInstance) {
            try {
                invitationInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'invitation.label', default: 'Invitation'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'invitation.label', default: 'Invitation'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'invitation.label', default: 'Invitation'), params.id])}"
            redirect(action: "list")
        }
    }

    def sendHandler = {SendCommand cmd ->


        if (cmd.hasErrors()) {
            cmd.errors.allErrors.each {
                println it
            }
        }
        else {


            render "Invitation successfully Added"
            cmd.sendTos.each {
                println ">>>>>>>>>>>>>>> ${it}"


                def invitation = new Invitation();


                println "2"
                invitation.sendFrom = User.get(cmd.sendFrom)
                println "3"
                invitation.sendTo = User.findByEmail(it);
                println "4"
                invitation.topic = Topic.get(cmd.topic)
                println "5"
                invitation.save(flush: true)


                println ">>>>>>>>>>>>>>>>>>${invitation.sendFrom.email}"
                if (invitation) {           println "6"
                                                    sendMail {
                                                        println "7"
                                                        to invitation.sendTo?.email
                                                        println "8"
                                                        subject "Hello ${invitation.sendTo?.name}"
                                                        println "9"
                                                        html "You have got an invitation for the Topic ${invitation.topic?.name} from ${invitation.sendFrom?.name}."
                                                         println "10"

                                                    }

                      println "11"
                    render "invitates sent"
                     println "12"

                                                }


            }
        }


    }


}
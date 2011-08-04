package com.intelligrape.linksharing

class InvitationController {


    def mailingService;
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def handler = {
        Topic topicToSend = Topic.get(params.id)
        User sender = User.get(session.currentUser.toLong())
        println params.name
        println topicToSend
        [from: sender, topic: topicToSend]
    }

    def index = {
        redirect(action: "click", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [invitationInstanceList: Invitation.list(params), invitationInstanceTotal: Invitation.count()]
    }

    def create = {
        Invitation invitationInstance = new Invitation()
        invitationInstance.properties = params
        return [invitationInstance: invitationInstance]
    }

    def save = {
        Invitation invitationInstance = new Invitation(params)
        if (invitationInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'invitation.label', default: 'Invitation'), invitationInstance.id])}"
            redirect(action: "show", id: invitationInstance.id)
        }
        else {
            render(view: "create", model: [invitationInstance: invitationInstance])
        }
    }

    def show = {
        Invitation invitationInstance = Invitation.get(params.id)
        if (invitationInstance) {
               [invitationInstance: invitationInstance]
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'invitation.label', default: 'Invitation'), params.id])}"
            redirect(action: "list")

        }
    }

    def edit = {
        Invitation invitationInstance = Invitation.get(params.id)
        if (invitationInstance) {
            return [invitationInstance: invitationInstance]
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'invitation.label', default: 'Invitation'), params.id])}"
            redirect(action: "list")

        }
    }

    def update = {
        Invitation invitationInstance = Invitation.get(params.id)
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
        Invitation invitationInstance = Invitation.get(params.id)
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

    def sendHandler = {SendEmailCO cmd ->
        if (cmd.hasErrors()) {
            cmd.errors.allErrors.each {

            }
        }
        else {
            mailingService.sendInvitation(cmd.sendTos.grep{it}, cmd.sendFrom, cmd.topic)
        }
    }


}


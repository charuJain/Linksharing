package com.intelligrape.linksharing

import org.codehaus.groovy.grails.commons.ConfigurationHolder

class DocumentResourceController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def download = {
        DocumentResource documentResource = DocumentResource.get(params.id)
        String filePath1 = ConfigurationHolder.config.path + documentResource.uuid + "-" + documentResource.name
        File file = new File("${filePath1}")
        response.setContentType("application/octet-stream")
        response.setHeader("Content-disposition", "filename=${documentResource.name}")

        response.outputStream << file.bytes
        render(view: "show")
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        User user = User.get(session.currentUser)

        [documentResourceInstanceList: DocumentResource.list(params), documentResourceInstanceTotal: DocumentResource.count(),user:user]
    }

    def create = {
        DocumentResource documentResourceInstance = new DocumentResource()
        documentResourceInstance.properties = params
        User user = User.get(session.currentUser)

        println documentResourceInstance.topic.name;
        return [documentResourceInstance: documentResourceInstance, user: user]
    }

    def save = {
        DocumentResource documentResourceInstance = new DocumentResource(params)
        documentResourceInstance.uuid = UUID.randomUUID().toString()
        documentResourceInstance.name = params.uploadedFile.getOriginalFilename().replaceAll(" ", "_")
        String filePath = ConfigurationHolder.config.path + documentResourceInstance.uuid + "-" + documentResourceInstance.name
        new File(filePath).withObjectOutputStream {out ->
            out.write documentResourceInstance.uploadedFile
        }

        if (documentResourceInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'documentResource.label', default: 'DocumentResource'), documentResourceInstance.id])}"
            redirect(action: "show", id: documentResourceInstance.id)
        }
        else {
            render(view: "create", model: [documentResourceInstance: documentResourceInstance])
        }
    }

    def show = {
        DocumentResource documentResourceInstance = DocumentResource.get(params.id)
        if (documentResourceInstance) {
            User user = User.get(session.currentUser)
            [documentResourceInstance: documentResourceInstance, user: user]

        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'documentResource.label', default: 'DocumentResource'), params.id])}"
            redirect(action: "list")

        }
    }

    def edit = {
        DocumentResource documentResourceInstance = DocumentResource.get(params.id)
        User user=User.get(session.currentUser)
        if (documentResourceInstance) {
            return [documentResourceInstance: documentResourceInstance,user:user]
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'documentResource.label', default: 'DocumentResource'), params.id])}"
            redirect(action: "list")

        }
    }

    def update = {
        DocumentResource documentResourceInstance = DocumentResource.get(params.id)
        if (documentResourceInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (documentResourceInstance.version > version) {

                    documentResourceInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'documentResource.label', default: 'DocumentResource')] as Object[], "Another user has updated this DocumentResource while you were editing")
                    render(view: "edit", model: [documentResourceInstance: documentResourceInstance])
                    return
                }
            }
            documentResourceInstance.properties = params
            if (!documentResourceInstance.hasErrors() && documentResourceInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'documentResource.label', default: 'DocumentResource'), documentResourceInstance.id])}"
                redirect(action: "show", id: documentResourceInstance.id)
            }
            else {
                render(view: "edit", model: [documentResourceInstance: documentResourceInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'documentResource.label', default: 'DocumentResource'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        DocumentResource documentResourceInstance = DocumentResource.get(params.id)
        if (documentResourceInstance) {
            try {
                documentResourceInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'documentResource.label', default: 'DocumentResource'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'documentResource.label', default: 'DocumentResource'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'documentResource.label', default: 'DocumentResource'), params.id])}"
            redirect(action: "list")
        }
    }
}

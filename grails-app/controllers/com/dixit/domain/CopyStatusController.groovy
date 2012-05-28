package com.dixit.domain

import org.springframework.dao.DataIntegrityViolationException

class CopyStatusController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [copyStatusInstanceList: CopyStatus.list(params), copyStatusInstanceTotal: CopyStatus.count()]
    }

    def create() {
        [copyStatusInstance: new CopyStatus(params)]
    }

    def save() {
        def copyStatusInstance = new CopyStatus(params)
        if (!copyStatusInstance.save(flush: true)) {
            render(view: "create", model: [copyStatusInstance: copyStatusInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'copyStatus.label', default: 'CopyStatus'), copyStatusInstance.id])
        redirect(action: "show", id: copyStatusInstance.id)
    }

    def show() {
        def copyStatusInstance = CopyStatus.get(params.id)
        if (!copyStatusInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'copyStatus.label', default: 'CopyStatus'), params.id])
            redirect(action: "list")
            return
        }

        [copyStatusInstance: copyStatusInstance]
    }

    def edit() {
        def copyStatusInstance = CopyStatus.get(params.id)
        if (!copyStatusInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'copyStatus.label', default: 'CopyStatus'), params.id])
            redirect(action: "list")
            return
        }

        [copyStatusInstance: copyStatusInstance]
    }

    def update() {
        def copyStatusInstance = CopyStatus.get(params.id)
        if (!copyStatusInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'copyStatus.label', default: 'CopyStatus'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (copyStatusInstance.version > version) {
                copyStatusInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'copyStatus.label', default: 'CopyStatus')] as Object[],
                          "Another user has updated this CopyStatus while you were editing")
                render(view: "edit", model: [copyStatusInstance: copyStatusInstance])
                return
            }
        }

        copyStatusInstance.properties = params

        if (!copyStatusInstance.save(flush: true)) {
            render(view: "edit", model: [copyStatusInstance: copyStatusInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'copyStatus.label', default: 'CopyStatus'), copyStatusInstance.id])
        redirect(action: "show", id: copyStatusInstance.id)
    }

    def delete() {
        def copyStatusInstance = CopyStatus.get(params.id)
        if (!copyStatusInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'copyStatus.label', default: 'CopyStatus'), params.id])
            redirect(action: "list")
            return
        }

        try {
            copyStatusInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'copyStatus.label', default: 'CopyStatus'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'copyStatus.label', default: 'CopyStatus'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}

package com.dixit.domain

import org.springframework.dao.DataIntegrityViolationException

class CopyController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [copyInstanceList: Copy.list(params), copyInstanceTotal: Copy.count()]
    }

    def create() {
        [copyInstance: new Copy(params)]
    }

    def save() {
        def copyInstance = new Copy(params)
        if (!copyInstance.save(flush: true)) {
            render(view: "create", model: [copyInstance: copyInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'copy.label', default: 'Copy'), copyInstance.id])
        redirect(action: "show", id: copyInstance.id)
    }

    def show() {
        def copyInstance = Copy.get(params.id)
        if (!copyInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'copy.label', default: 'Copy'), params.id])
            redirect(action: "list")
            return
        }

        [copyInstance: copyInstance]
    }

    def edit() {
        def copyInstance = Copy.get(params.id)
        if (!copyInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'copy.label', default: 'Copy'), params.id])
            redirect(action: "list")
            return
        }

        [copyInstance: copyInstance]
    }

    def update() {
        def copyInstance = Copy.get(params.id)
        if (!copyInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'copy.label', default: 'Copy'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (copyInstance.version > version) {
                copyInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'copy.label', default: 'Copy')] as Object[],
                          "Another user has updated this Copy while you were editing")
                render(view: "edit", model: [copyInstance: copyInstance])
                return
            }
        }

        copyInstance.properties = params

        if (!copyInstance.save(flush: true)) {
            render(view: "edit", model: [copyInstance: copyInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'copy.label', default: 'Copy'), copyInstance.id])
        redirect(action: "show", id: copyInstance.id)
    }

    def delete() {
        def copyInstance = Copy.get(params.id)
        if (!copyInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'copy.label', default: 'Copy'), params.id])
            redirect(action: "list")
            return
        }

        try {
            copyInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'copy.label', default: 'Copy'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'copy.label', default: 'Copy'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}

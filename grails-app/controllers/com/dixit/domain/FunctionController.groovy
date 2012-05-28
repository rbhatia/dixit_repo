package com.dixit.domain

import org.springframework.dao.DataIntegrityViolationException

class FunctionController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [functionInstanceList: Function.list(params), functionInstanceTotal: Function.count()]
    }

    def create() {
        [functionInstance: new Function(params)]
    }

    def save() {
        def functionInstance = new Function(params)
        if (!functionInstance.save(flush: true)) {
            render(view: "create", model: [functionInstance: functionInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'function.label', default: 'Function'), functionInstance.id])
        redirect(action: "show", id: functionInstance.id)
    }

    def show() {
        def functionInstance = Function.get(params.id)
        if (!functionInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'function.label', default: 'Function'), params.id])
            redirect(action: "list")
            return
        }

        [functionInstance: functionInstance]
    }

    def edit() {
        def functionInstance = Function.get(params.id)
        if (!functionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'function.label', default: 'Function'), params.id])
            redirect(action: "list")
            return
        }

        [functionInstance: functionInstance]
    }

    def update() {
        def functionInstance = Function.get(params.id)
        if (!functionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'function.label', default: 'Function'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (functionInstance.version > version) {
                functionInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'function.label', default: 'Function')] as Object[],
                          "Another user has updated this Function while you were editing")
                render(view: "edit", model: [functionInstance: functionInstance])
                return
            }
        }

        functionInstance.properties = params

        if (!functionInstance.save(flush: true)) {
            render(view: "edit", model: [functionInstance: functionInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'function.label', default: 'Function'), functionInstance.id])
        redirect(action: "show", id: functionInstance.id)
    }

    def delete() {
        def functionInstance = Function.get(params.id)
        if (!functionInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'function.label', default: 'Function'), params.id])
            redirect(action: "list")
            return
        }

        try {
            functionInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'function.label', default: 'Function'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'function.label', default: 'Function'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}

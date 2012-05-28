package com.dixit.domain

import org.springframework.dao.DataIntegrityViolationException

class UserFunctionController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [userFunctionInstanceList: UserFunction.list(params), userFunctionInstanceTotal: UserFunction.count()]
    }

    def create() {
        [userFunctionInstance: new UserFunction(params)]
    }

    def save() {
        def userFunctionInstance = new UserFunction(params)
        if (!userFunctionInstance.save(flush: true)) {
            render(view: "create", model: [userFunctionInstance: userFunctionInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'userFunction.label', default: 'UserFunction'), userFunctionInstance.id])
        redirect(action: "show", id: userFunctionInstance.id)
    }

    def show() {
        def userFunctionInstance = UserFunction.get(params.id)
        if (!userFunctionInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'userFunction.label', default: 'UserFunction'), params.id])
            redirect(action: "list")
            return
        }

        [userFunctionInstance: userFunctionInstance]
    }

    def edit() {
        def userFunctionInstance = UserFunction.get(params.id)
        if (!userFunctionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'userFunction.label', default: 'UserFunction'), params.id])
            redirect(action: "list")
            return
        }

        [userFunctionInstance: userFunctionInstance]
    }

    def update() {
        def userFunctionInstance = UserFunction.get(params.id)
        if (!userFunctionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'userFunction.label', default: 'UserFunction'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (userFunctionInstance.version > version) {
                userFunctionInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'userFunction.label', default: 'UserFunction')] as Object[],
                          "Another user has updated this UserFunction while you were editing")
                render(view: "edit", model: [userFunctionInstance: userFunctionInstance])
                return
            }
        }

        userFunctionInstance.properties = params

        if (!userFunctionInstance.save(flush: true)) {
            render(view: "edit", model: [userFunctionInstance: userFunctionInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'userFunction.label', default: 'UserFunction'), userFunctionInstance.id])
        redirect(action: "show", id: userFunctionInstance.id)
    }

    def delete() {
        def userFunctionInstance = UserFunction.get(params.id)
        if (!userFunctionInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'userFunction.label', default: 'UserFunction'), params.id])
            redirect(action: "list")
            return
        }

        try {
            userFunctionInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'userFunction.label', default: 'UserFunction'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'userFunction.label', default: 'UserFunction'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}

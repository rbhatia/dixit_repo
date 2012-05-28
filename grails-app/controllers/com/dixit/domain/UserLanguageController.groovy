package com.dixit.domain

import org.springframework.dao.DataIntegrityViolationException

class UserLanguageController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [userLanguageInstanceList: UserLanguage.list(params), userLanguageInstanceTotal: UserLanguage.count()]
    }

    def create() {
        [userLanguageInstance: new UserLanguage(params)]
    }

    def save() {
        def userLanguageInstance = new UserLanguage(params)
        if (!userLanguageInstance.save(flush: true)) {
            render(view: "create", model: [userLanguageInstance: userLanguageInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'userLanguage.label', default: 'UserLanguage'), userLanguageInstance.id])
        redirect(action: "show", id: userLanguageInstance.id)
    }

    def show() {
        def userLanguageInstance = UserLanguage.get(params.id)
        if (!userLanguageInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'userLanguage.label', default: 'UserLanguage'), params.id])
            redirect(action: "list")
            return
        }

        [userLanguageInstance: userLanguageInstance]
    }

    def edit() {
        def userLanguageInstance = UserLanguage.get(params.id)
        if (!userLanguageInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'userLanguage.label', default: 'UserLanguage'), params.id])
            redirect(action: "list")
            return
        }

        [userLanguageInstance: userLanguageInstance]
    }

    def update() {
        def userLanguageInstance = UserLanguage.get(params.id)
        if (!userLanguageInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'userLanguage.label', default: 'UserLanguage'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (userLanguageInstance.version > version) {
                userLanguageInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'userLanguage.label', default: 'UserLanguage')] as Object[],
                          "Another user has updated this UserLanguage while you were editing")
                render(view: "edit", model: [userLanguageInstance: userLanguageInstance])
                return
            }
        }

        userLanguageInstance.properties = params

        if (!userLanguageInstance.save(flush: true)) {
            render(view: "edit", model: [userLanguageInstance: userLanguageInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'userLanguage.label', default: 'UserLanguage'), userLanguageInstance.id])
        redirect(action: "show", id: userLanguageInstance.id)
    }

    def delete() {
        def userLanguageInstance = UserLanguage.get(params.id)
        if (!userLanguageInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'userLanguage.label', default: 'UserLanguage'), params.id])
            redirect(action: "list")
            return
        }

        try {
            userLanguageInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'userLanguage.label', default: 'UserLanguage'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'userLanguage.label', default: 'UserLanguage'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}

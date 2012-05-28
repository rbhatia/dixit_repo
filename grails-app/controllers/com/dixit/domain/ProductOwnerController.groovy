package com.dixit.domain

import org.springframework.dao.DataIntegrityViolationException

class ProductOwnerController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [productOwnerInstanceList: ProductOwner.list(params), productOwnerInstanceTotal: ProductOwner.count()]
    }

    def create() {
        [productOwnerInstance: new ProductOwner(params)]
    }

    def save() {
        def productOwnerInstance = new ProductOwner(params)
        if (!productOwnerInstance.save(flush: true)) {
            render(view: "create", model: [productOwnerInstance: productOwnerInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'productOwner.label', default: 'ProductOwner'), productOwnerInstance.id])
        redirect(action: "show", id: productOwnerInstance.id)
    }

    def show() {
        def productOwnerInstance = ProductOwner.get(params.id)
        if (!productOwnerInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'productOwner.label', default: 'ProductOwner'), params.id])
            redirect(action: "list")
            return
        }

        [productOwnerInstance: productOwnerInstance]
    }

    def edit() {
        def productOwnerInstance = ProductOwner.get(params.id)
        if (!productOwnerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'productOwner.label', default: 'ProductOwner'), params.id])
            redirect(action: "list")
            return
        }

        [productOwnerInstance: productOwnerInstance]
    }

    def update() {
        def productOwnerInstance = ProductOwner.get(params.id)
        if (!productOwnerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'productOwner.label', default: 'ProductOwner'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (productOwnerInstance.version > version) {
                productOwnerInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'productOwner.label', default: 'ProductOwner')] as Object[],
                          "Another user has updated this ProductOwner while you were editing")
                render(view: "edit", model: [productOwnerInstance: productOwnerInstance])
                return
            }
        }

        productOwnerInstance.properties = params

        if (!productOwnerInstance.save(flush: true)) {
            render(view: "edit", model: [productOwnerInstance: productOwnerInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'productOwner.label', default: 'ProductOwner'), productOwnerInstance.id])
        redirect(action: "show", id: productOwnerInstance.id)
    }

    def delete() {
        def productOwnerInstance = ProductOwner.get(params.id)
        if (!productOwnerInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'productOwner.label', default: 'ProductOwner'), params.id])
            redirect(action: "list")
            return
        }

        try {
            productOwnerInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'productOwner.label', default: 'ProductOwner'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'productOwner.label', default: 'ProductOwner'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}

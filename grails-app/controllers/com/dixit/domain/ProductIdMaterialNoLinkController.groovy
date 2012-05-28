package com.dixit.domain

import org.springframework.dao.DataIntegrityViolationException

class ProductIdMaterialNoLinkController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [productIdMaterialNoLinkInstanceList: ProductIdMaterialNoLink.list(params), productIdMaterialNoLinkInstanceTotal: ProductIdMaterialNoLink.count()]
    }

    def create() {
        [productIdMaterialNoLinkInstance: new ProductIdMaterialNoLink(params)]
    }

    def save() {
        def productIdMaterialNoLinkInstance = new ProductIdMaterialNoLink(params)
        if (!productIdMaterialNoLinkInstance.save(flush: true)) {
            render(view: "create", model: [productIdMaterialNoLinkInstance: productIdMaterialNoLinkInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'productIdMaterialNoLink.label', default: 'ProductIdMaterialNoLink'), productIdMaterialNoLinkInstance.id])
        redirect(action: "show", id: productIdMaterialNoLinkInstance.id)
    }

    def show() {
        def productIdMaterialNoLinkInstance = ProductIdMaterialNoLink.get(params.id)
        if (!productIdMaterialNoLinkInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'productIdMaterialNoLink.label', default: 'ProductIdMaterialNoLink'), params.id])
            redirect(action: "list")
            return
        }

        [productIdMaterialNoLinkInstance: productIdMaterialNoLinkInstance]
    }

    def edit() {
        def productIdMaterialNoLinkInstance = ProductIdMaterialNoLink.get(params.id)
        if (!productIdMaterialNoLinkInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'productIdMaterialNoLink.label', default: 'ProductIdMaterialNoLink'), params.id])
            redirect(action: "list")
            return
        }

        [productIdMaterialNoLinkInstance: productIdMaterialNoLinkInstance]
    }

    def update() {
        def productIdMaterialNoLinkInstance = ProductIdMaterialNoLink.get(params.id)
        if (!productIdMaterialNoLinkInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'productIdMaterialNoLink.label', default: 'ProductIdMaterialNoLink'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (productIdMaterialNoLinkInstance.version > version) {
                productIdMaterialNoLinkInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'productIdMaterialNoLink.label', default: 'ProductIdMaterialNoLink')] as Object[],
                          "Another user has updated this ProductIdMaterialNoLink while you were editing")
                render(view: "edit", model: [productIdMaterialNoLinkInstance: productIdMaterialNoLinkInstance])
                return
            }
        }

        productIdMaterialNoLinkInstance.properties = params

        if (!productIdMaterialNoLinkInstance.save(flush: true)) {
            render(view: "edit", model: [productIdMaterialNoLinkInstance: productIdMaterialNoLinkInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'productIdMaterialNoLink.label', default: 'ProductIdMaterialNoLink'), productIdMaterialNoLinkInstance.id])
        redirect(action: "show", id: productIdMaterialNoLinkInstance.id)
    }

    def delete() {
        def productIdMaterialNoLinkInstance = ProductIdMaterialNoLink.get(params.id)
        if (!productIdMaterialNoLinkInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'productIdMaterialNoLink.label', default: 'ProductIdMaterialNoLink'), params.id])
            redirect(action: "list")
            return
        }

        try {
            productIdMaterialNoLinkInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'productIdMaterialNoLink.label', default: 'ProductIdMaterialNoLink'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'productIdMaterialNoLink.label', default: 'ProductIdMaterialNoLink'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}

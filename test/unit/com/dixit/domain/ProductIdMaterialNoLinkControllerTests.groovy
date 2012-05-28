package com.dixit.domain



import org.junit.*
import grails.test.mixin.*

@TestFor(ProductIdMaterialNoLinkController)
@Mock(ProductIdMaterialNoLink)
class ProductIdMaterialNoLinkControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/productIdMaterialNoLink/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.productIdMaterialNoLinkInstanceList.size() == 0
        assert model.productIdMaterialNoLinkInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.productIdMaterialNoLinkInstance != null
    }

    void testSave() {
        controller.save()

        assert model.productIdMaterialNoLinkInstance != null
        assert view == '/productIdMaterialNoLink/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/productIdMaterialNoLink/show/1'
        assert controller.flash.message != null
        assert ProductIdMaterialNoLink.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/productIdMaterialNoLink/list'


        populateValidParams(params)
        def productIdMaterialNoLink = new ProductIdMaterialNoLink(params)

        assert productIdMaterialNoLink.save() != null

        params.id = productIdMaterialNoLink.id

        def model = controller.show()

        assert model.productIdMaterialNoLinkInstance == productIdMaterialNoLink
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/productIdMaterialNoLink/list'


        populateValidParams(params)
        def productIdMaterialNoLink = new ProductIdMaterialNoLink(params)

        assert productIdMaterialNoLink.save() != null

        params.id = productIdMaterialNoLink.id

        def model = controller.edit()

        assert model.productIdMaterialNoLinkInstance == productIdMaterialNoLink
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/productIdMaterialNoLink/list'

        response.reset()


        populateValidParams(params)
        def productIdMaterialNoLink = new ProductIdMaterialNoLink(params)

        assert productIdMaterialNoLink.save() != null

        // test invalid parameters in update
        params.id = productIdMaterialNoLink.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/productIdMaterialNoLink/edit"
        assert model.productIdMaterialNoLinkInstance != null

        productIdMaterialNoLink.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/productIdMaterialNoLink/show/$productIdMaterialNoLink.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        productIdMaterialNoLink.clearErrors()

        populateValidParams(params)
        params.id = productIdMaterialNoLink.id
        params.version = -1
        controller.update()

        assert view == "/productIdMaterialNoLink/edit"
        assert model.productIdMaterialNoLinkInstance != null
        assert model.productIdMaterialNoLinkInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/productIdMaterialNoLink/list'

        response.reset()

        populateValidParams(params)
        def productIdMaterialNoLink = new ProductIdMaterialNoLink(params)

        assert productIdMaterialNoLink.save() != null
        assert ProductIdMaterialNoLink.count() == 1

        params.id = productIdMaterialNoLink.id

        controller.delete()

        assert ProductIdMaterialNoLink.count() == 0
        assert ProductIdMaterialNoLink.get(productIdMaterialNoLink.id) == null
        assert response.redirectedUrl == '/productIdMaterialNoLink/list'
    }
}

package com.dixit.domain



import org.junit.*
import grails.test.mixin.*

@TestFor(ProductOwnerController)
@Mock(ProductOwner)
class ProductOwnerControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/productOwner/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.productOwnerInstanceList.size() == 0
        assert model.productOwnerInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.productOwnerInstance != null
    }

    void testSave() {
        controller.save()

        assert model.productOwnerInstance != null
        assert view == '/productOwner/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/productOwner/show/1'
        assert controller.flash.message != null
        assert ProductOwner.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/productOwner/list'


        populateValidParams(params)
        def productOwner = new ProductOwner(params)

        assert productOwner.save() != null

        params.id = productOwner.id

        def model = controller.show()

        assert model.productOwnerInstance == productOwner
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/productOwner/list'


        populateValidParams(params)
        def productOwner = new ProductOwner(params)

        assert productOwner.save() != null

        params.id = productOwner.id

        def model = controller.edit()

        assert model.productOwnerInstance == productOwner
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/productOwner/list'

        response.reset()


        populateValidParams(params)
        def productOwner = new ProductOwner(params)

        assert productOwner.save() != null

        // test invalid parameters in update
        params.id = productOwner.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/productOwner/edit"
        assert model.productOwnerInstance != null

        productOwner.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/productOwner/show/$productOwner.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        productOwner.clearErrors()

        populateValidParams(params)
        params.id = productOwner.id
        params.version = -1
        controller.update()

        assert view == "/productOwner/edit"
        assert model.productOwnerInstance != null
        assert model.productOwnerInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/productOwner/list'

        response.reset()

        populateValidParams(params)
        def productOwner = new ProductOwner(params)

        assert productOwner.save() != null
        assert ProductOwner.count() == 1

        params.id = productOwner.id

        controller.delete()

        assert ProductOwner.count() == 0
        assert ProductOwner.get(productOwner.id) == null
        assert response.redirectedUrl == '/productOwner/list'
    }
}

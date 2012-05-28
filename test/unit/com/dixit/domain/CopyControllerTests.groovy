package com.dixit.domain



import org.junit.*
import grails.test.mixin.*

@TestFor(CopyController)
@Mock(Copy)
class CopyControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/copy/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.copyInstanceList.size() == 0
        assert model.copyInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.copyInstance != null
    }

    void testSave() {
        controller.save()

        assert model.copyInstance != null
        assert view == '/copy/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/copy/show/1'
        assert controller.flash.message != null
        assert Copy.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/copy/list'


        populateValidParams(params)
        def copy = new Copy(params)

        assert copy.save() != null

        params.id = copy.id

        def model = controller.show()

        assert model.copyInstance == copy
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/copy/list'


        populateValidParams(params)
        def copy = new Copy(params)

        assert copy.save() != null

        params.id = copy.id

        def model = controller.edit()

        assert model.copyInstance == copy
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/copy/list'

        response.reset()


        populateValidParams(params)
        def copy = new Copy(params)

        assert copy.save() != null

        // test invalid parameters in update
        params.id = copy.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/copy/edit"
        assert model.copyInstance != null

        copy.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/copy/show/$copy.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        copy.clearErrors()

        populateValidParams(params)
        params.id = copy.id
        params.version = -1
        controller.update()

        assert view == "/copy/edit"
        assert model.copyInstance != null
        assert model.copyInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/copy/list'

        response.reset()

        populateValidParams(params)
        def copy = new Copy(params)

        assert copy.save() != null
        assert Copy.count() == 1

        params.id = copy.id

        controller.delete()

        assert Copy.count() == 0
        assert Copy.get(copy.id) == null
        assert response.redirectedUrl == '/copy/list'
    }
}

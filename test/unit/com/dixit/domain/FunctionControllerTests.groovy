package com.dixit.domain



import org.junit.*
import grails.test.mixin.*

@TestFor(FunctionController)
@Mock(Function)
class FunctionControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/function/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.functionInstanceList.size() == 0
        assert model.functionInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.functionInstance != null
    }

    void testSave() {
        controller.save()

        assert model.functionInstance != null
        assert view == '/function/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/function/show/1'
        assert controller.flash.message != null
        assert Function.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/function/list'


        populateValidParams(params)
        def function = new Function(params)

        assert function.save() != null

        params.id = function.id

        def model = controller.show()

        assert model.functionInstance == function
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/function/list'


        populateValidParams(params)
        def function = new Function(params)

        assert function.save() != null

        params.id = function.id

        def model = controller.edit()

        assert model.functionInstance == function
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/function/list'

        response.reset()


        populateValidParams(params)
        def function = new Function(params)

        assert function.save() != null

        // test invalid parameters in update
        params.id = function.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/function/edit"
        assert model.functionInstance != null

        function.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/function/show/$function.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        function.clearErrors()

        populateValidParams(params)
        params.id = function.id
        params.version = -1
        controller.update()

        assert view == "/function/edit"
        assert model.functionInstance != null
        assert model.functionInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/function/list'

        response.reset()

        populateValidParams(params)
        def function = new Function(params)

        assert function.save() != null
        assert Function.count() == 1

        params.id = function.id

        controller.delete()

        assert Function.count() == 0
        assert Function.get(function.id) == null
        assert response.redirectedUrl == '/function/list'
    }
}

package com.dixit.domain



import org.junit.*
import grails.test.mixin.*

@TestFor(UserFunctionController)
@Mock(UserFunction)
class UserFunctionControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/userFunction/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.userFunctionInstanceList.size() == 0
        assert model.userFunctionInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.userFunctionInstance != null
    }

    void testSave() {
        controller.save()

        assert model.userFunctionInstance != null
        assert view == '/userFunction/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/userFunction/show/1'
        assert controller.flash.message != null
        assert UserFunction.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/userFunction/list'


        populateValidParams(params)
        def userFunction = new UserFunction(params)

        assert userFunction.save() != null

        params.id = userFunction.id

        def model = controller.show()

        assert model.userFunctionInstance == userFunction
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/userFunction/list'


        populateValidParams(params)
        def userFunction = new UserFunction(params)

        assert userFunction.save() != null

        params.id = userFunction.id

        def model = controller.edit()

        assert model.userFunctionInstance == userFunction
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/userFunction/list'

        response.reset()


        populateValidParams(params)
        def userFunction = new UserFunction(params)

        assert userFunction.save() != null

        // test invalid parameters in update
        params.id = userFunction.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/userFunction/edit"
        assert model.userFunctionInstance != null

        userFunction.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/userFunction/show/$userFunction.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        userFunction.clearErrors()

        populateValidParams(params)
        params.id = userFunction.id
        params.version = -1
        controller.update()

        assert view == "/userFunction/edit"
        assert model.userFunctionInstance != null
        assert model.userFunctionInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/userFunction/list'

        response.reset()

        populateValidParams(params)
        def userFunction = new UserFunction(params)

        assert userFunction.save() != null
        assert UserFunction.count() == 1

        params.id = userFunction.id

        controller.delete()

        assert UserFunction.count() == 0
        assert UserFunction.get(userFunction.id) == null
        assert response.redirectedUrl == '/userFunction/list'
    }
}

package com.dixit.domain



import org.junit.*
import grails.test.mixin.*

@TestFor(UserLanguageController)
@Mock(UserLanguage)
class UserLanguageControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/userLanguage/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.userLanguageInstanceList.size() == 0
        assert model.userLanguageInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.userLanguageInstance != null
    }

    void testSave() {
        controller.save()

        assert model.userLanguageInstance != null
        assert view == '/userLanguage/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/userLanguage/show/1'
        assert controller.flash.message != null
        assert UserLanguage.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/userLanguage/list'


        populateValidParams(params)
        def userLanguage = new UserLanguage(params)

        assert userLanguage.save() != null

        params.id = userLanguage.id

        def model = controller.show()

        assert model.userLanguageInstance == userLanguage
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/userLanguage/list'


        populateValidParams(params)
        def userLanguage = new UserLanguage(params)

        assert userLanguage.save() != null

        params.id = userLanguage.id

        def model = controller.edit()

        assert model.userLanguageInstance == userLanguage
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/userLanguage/list'

        response.reset()


        populateValidParams(params)
        def userLanguage = new UserLanguage(params)

        assert userLanguage.save() != null

        // test invalid parameters in update
        params.id = userLanguage.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/userLanguage/edit"
        assert model.userLanguageInstance != null

        userLanguage.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/userLanguage/show/$userLanguage.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        userLanguage.clearErrors()

        populateValidParams(params)
        params.id = userLanguage.id
        params.version = -1
        controller.update()

        assert view == "/userLanguage/edit"
        assert model.userLanguageInstance != null
        assert model.userLanguageInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/userLanguage/list'

        response.reset()

        populateValidParams(params)
        def userLanguage = new UserLanguage(params)

        assert userLanguage.save() != null
        assert UserLanguage.count() == 1

        params.id = userLanguage.id

        controller.delete()

        assert UserLanguage.count() == 0
        assert UserLanguage.get(userLanguage.id) == null
        assert response.redirectedUrl == '/userLanguage/list'
    }
}

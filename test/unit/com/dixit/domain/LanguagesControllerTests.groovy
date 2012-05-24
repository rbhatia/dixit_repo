package com.dixit.domain



import org.junit.*
import grails.test.mixin.*

@TestFor(LanguagesController)
@Mock(Languages)
class LanguagesControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/languages/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.languagesInstanceList.size() == 0
        assert model.languagesInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.languagesInstance != null
    }

    void testSave() {
        controller.save()

        assert model.languagesInstance != null
        assert view == '/languages/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/languages/show/1'
        assert controller.flash.message != null
        assert Languages.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/languages/list'


        populateValidParams(params)
        def languages = new Languages(params)

        assert languages.save() != null

        params.id = languages.id

        def model = controller.show()

        assert model.languagesInstance == languages
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/languages/list'


        populateValidParams(params)
        def languages = new Languages(params)

        assert languages.save() != null

        params.id = languages.id

        def model = controller.edit()

        assert model.languagesInstance == languages
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/languages/list'

        response.reset()


        populateValidParams(params)
        def languages = new Languages(params)

        assert languages.save() != null

        // test invalid parameters in update
        params.id = languages.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/languages/edit"
        assert model.languagesInstance != null

        languages.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/languages/show/$languages.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        languages.clearErrors()

        populateValidParams(params)
        params.id = languages.id
        params.version = -1
        controller.update()

        assert view == "/languages/edit"
        assert model.languagesInstance != null
        assert model.languagesInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/languages/list'

        response.reset()

        populateValidParams(params)
        def languages = new Languages(params)

        assert languages.save() != null
        assert Languages.count() == 1

        params.id = languages.id

        controller.delete()

        assert Languages.count() == 0
        assert Languages.get(languages.id) == null
        assert response.redirectedUrl == '/languages/list'
    }
}

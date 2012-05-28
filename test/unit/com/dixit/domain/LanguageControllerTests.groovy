package com.dixit.domain



import org.junit.*
import grails.test.mixin.*

@TestFor(LanguageController)
@Mock(Language)
class LanguageControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/language/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.languageInstanceList.size() == 0
        assert model.languageInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.languageInstance != null
    }

    void testSave() {
        controller.save()

        assert model.languageInstance != null
        assert view == '/language/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/language/show/1'
        assert controller.flash.message != null
        assert Language.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/language/list'


        populateValidParams(params)
        def language = new Language(params)

        assert language.save() != null

        params.id = language.id

        def model = controller.show()

        assert model.languageInstance == language
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/language/list'


        populateValidParams(params)
        def language = new Language(params)

        assert language.save() != null

        params.id = language.id

        def model = controller.edit()

        assert model.languageInstance == language
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/language/list'

        response.reset()


        populateValidParams(params)
        def language = new Language(params)

        assert language.save() != null

        // test invalid parameters in update
        params.id = language.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/language/edit"
        assert model.languageInstance != null

        language.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/language/show/$language.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        language.clearErrors()

        populateValidParams(params)
        params.id = language.id
        params.version = -1
        controller.update()

        assert view == "/language/edit"
        assert model.languageInstance != null
        assert model.languageInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/language/list'

        response.reset()

        populateValidParams(params)
        def language = new Language(params)

        assert language.save() != null
        assert Language.count() == 1

        params.id = language.id

        controller.delete()

        assert Language.count() == 0
        assert Language.get(language.id) == null
        assert response.redirectedUrl == '/language/list'
    }
}

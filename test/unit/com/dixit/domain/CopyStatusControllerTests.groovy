package com.dixit.domain



import org.junit.*
import grails.test.mixin.*

@TestFor(CopyStatusController)
@Mock(CopyStatus)
class CopyStatusControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/copyStatus/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.copyStatusInstanceList.size() == 0
        assert model.copyStatusInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.copyStatusInstance != null
    }

    void testSave() {
        controller.save()

        assert model.copyStatusInstance != null
        assert view == '/copyStatus/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/copyStatus/show/1'
        assert controller.flash.message != null
        assert CopyStatus.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/copyStatus/list'


        populateValidParams(params)
        def copyStatus = new CopyStatus(params)

        assert copyStatus.save() != null

        params.id = copyStatus.id

        def model = controller.show()

        assert model.copyStatusInstance == copyStatus
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/copyStatus/list'


        populateValidParams(params)
        def copyStatus = new CopyStatus(params)

        assert copyStatus.save() != null

        params.id = copyStatus.id

        def model = controller.edit()

        assert model.copyStatusInstance == copyStatus
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/copyStatus/list'

        response.reset()


        populateValidParams(params)
        def copyStatus = new CopyStatus(params)

        assert copyStatus.save() != null

        // test invalid parameters in update
        params.id = copyStatus.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/copyStatus/edit"
        assert model.copyStatusInstance != null

        copyStatus.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/copyStatus/show/$copyStatus.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        copyStatus.clearErrors()

        populateValidParams(params)
        params.id = copyStatus.id
        params.version = -1
        controller.update()

        assert view == "/copyStatus/edit"
        assert model.copyStatusInstance != null
        assert model.copyStatusInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/copyStatus/list'

        response.reset()

        populateValidParams(params)
        def copyStatus = new CopyStatus(params)

        assert copyStatus.save() != null
        assert CopyStatus.count() == 1

        params.id = copyStatus.id

        controller.delete()

        assert CopyStatus.count() == 0
        assert CopyStatus.get(copyStatus.id) == null
        assert response.redirectedUrl == '/copyStatus/list'
    }
}

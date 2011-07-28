package com.intelligrape.linksharing

import grails.plugin.spock.IntegrationSpec


class UserTopicControllerIntegrationSpec extends IntegrationSpec {

    UserController controller = new UserController()
    Map redirectArgs
    Map renderArgs

    def setup() {
        controller.metaClass.redirect = {Map map ->
            redirectArgs = map
        }
        controller.metaClass.render = {Map map ->
            renderArgs = map
        }
    }

    def "testing for edit action of valid user"() {
        setup:
        controller.params.id = 1

        when:
        controller.edit()

        then:
        renderArgs.view == "edit"

    }

    def "testing for edit action of invalid user"() {
        setup:
        controller.params.id = 10

        when:
        controller.edit()

        then:
        redirectArgs.action == 'list'

    }

    def "testing for show action of valid user"() {
        setup:
        controller.params.id = 2

        when:
        controller.show()

        then:
        renderArgs.view == 'show'

    }


    def "testing for show action of invalid user"(){
        setup:
        controller.params.id=100

        when:
        controller.show()

        then:
        redirectArgs.action=='list'


    }




}



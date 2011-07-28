package com.intelligrape.linksharing

import grails.plugin.spock.ControllerSpec

class UserControllerSpec extends ControllerSpec {
    def "test the login handler"() {
        setup:
        mockDomain(User)

        when:

        User user1 = new User(username: "admin", password: "password", confirmPassword: "password", name: "Gaurav",
                                             phoneNumber: 123456789, address: "new delhi", email: "admin@intelligrape.com", age: 34).save(flush: true)
        User user2 = new User(username: "test2", password: "password", confirmPassword: "password", name: "Charu",
                                             phoneNumber: 123456789, address: "new delhi", email: "charu@intelligrape.com", age: 24).save(flush: true)

        mockParams.username = name
        mockParams.password = password
        controller.loginHandler()

        then:

        println redirectArgs
        redirectArgs.controller == controllerName
        redirectArgs.action == actionName
        renderArgs.view == 'login'
        mockSession.currentUser == userId
        println user1.name
        println user2.name

        where:
        sno | name    | password   | controllerName | actionName  | userId
        1   | "admin" | "password" | "admin"        | "stats"     | 1
        2   | "test2" | "password" | "user"         | "dashboard" | 2

    }
}

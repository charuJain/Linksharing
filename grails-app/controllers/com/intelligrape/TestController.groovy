package com.intelligrape

import com.intelligrape.linksharing.User

class TestController {

   // def scaffold = User;

    def index = {
        User  user=new User(name:'charu',username:'charu',password: 'cjain',confirmpassword: 'cjain')
        user.save()
//        user.errors.allErrors.each{println it}
                               println renderErrors(bean:user)
    }

}

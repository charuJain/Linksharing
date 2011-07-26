package com.intelligrape.linksharing

import com.intelligrape.linksharing.User
import com.intelligrape.linksharing.Topic
import com.intelligrape.linksharing.Invitation

class AdminController {

    //def index = { }

//    def beforeInterceptor = {
//
//        if(params.username!='admin@intelligrape.com')  {
//            render "access denied"
//            return false;
//        }
//
//
//
//    }

    def login={
            }

    def stats = {
        [usercount:User.count(),topiccount: Topic.count(),invitationcount:Invitation.count()]
//        int count = ;
//
//        render "number of users are"+" ";
//        render count +"  ";
//
//
//        int count1 =;
//
//
//        render "number of topics are"+" ";
//
//        render count1;
    }
}

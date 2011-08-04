package com.intelligrape.linksharing

class Invitation {

    User sendTo;
    User sendFrom;
    Topic topic;

    static belongsTo = [User, Topic]

    static constraints = {


    }
}

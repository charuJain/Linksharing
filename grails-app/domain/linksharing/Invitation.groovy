package linksharing

class Invitation {

    User sendTo;
    User sendFrom;


    static belongsTo = [User,Topic]
    Topic topic;

   static constraints = {


    }
}

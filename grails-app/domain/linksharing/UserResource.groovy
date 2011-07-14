package linksharing

class UserResource {

    User user;
    Resource resource;
    boolean isRead=false;

    static belongsTo = [User]


    //List<Resource>resources=[]
   // static hasMany = [resorces:Resource]
    static constraints = {
    }
}

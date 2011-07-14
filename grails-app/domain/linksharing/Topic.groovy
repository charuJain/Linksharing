package linksharing

class Topic {

    String name;
    boolean isPrivate=false;
    User CreatedBy;
    Date CreatedOn;

    //List<Invitation>invitations=[]
    //List<Resource>resources=[]


         static belongsTo=[User]

    static hasMany = [users:User,invitations:Invitation,resources:Resource]
    //,resources:Resource,invitations:Invitation
    static constraints = {
        name(unique: true)



    }
}

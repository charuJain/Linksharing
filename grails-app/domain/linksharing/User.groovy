package linksharing

class User {
    String name;
    String username;
    String password;
    String address;
    String email;
    int age;
    boolean isAdmin=false;

    static mappedBy = [invitations:'sendFrom']

    //List<Invitation>invitations=[];
    //List<UserResource>readResorce=[];

   // static belongsTo = []
    static hasMany = [topics:Topic,invitations:Invitation,userResources:UserResource]
                                   //,invitations:Invitation,readResource:UserResource
      static constraints = {
        username(size:5..15, blank:false, unique:true)
        password(size:5..15, blank:false)
        email(email:true, blank:false)
        age(min:18, nullable:false)
    }
}

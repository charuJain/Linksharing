package com.intelligrape.linksharing

class User {
    String name;
    String username;
    String password;
    String confirmPassword;
    String address;
    String email;
    int age;

    int phoneNumber;
    boolean isAdmin = false;

    //static transients = ['confirmpassword']

    static mappedBy = [invitations: 'sendFrom']

    //List<Invitation>invitations=[];
    //List<UserResource>readResorce=[];

    // static belongsTo = []
    static hasMany = [userTopics: UserTopic, invitations: Invitation, userResources: UserResource]
    //,invitations:Invitation,readResource:UserResource
    static constraints = {

        username();
        password()
        confirmPassword()
        name()
        address()
        email()
        age()



        username(size: 5..15, blank: false, unique: true)
        password(size: 5..15, blank: false,validator: { val,obj->

          if(val!= obj.confirmPassword)
          {
              return "User.password.mismatch"
          }



        })
        email(email: true, blank: false)
        age(min: 18, nullable: false)


    }
}

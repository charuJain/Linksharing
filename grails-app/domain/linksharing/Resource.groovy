package linksharing

class Resource {

    Topic topic;
    User createdBy;
    Date createdOn;
    String  header;

    static mapping = {
        tablePerHierarchy false
    }
              static belongsTo=[Topic]

    //static hasMany = [readResources:UserResource]
    static constraints = {
       header(blank:false)

    }
}

package linksharing

class LinkResource extends Resource {
    String url;

     static mapping = {
        tablePerHierarchy false
    }


    static constraints = {

        url(url:true)

    }
}

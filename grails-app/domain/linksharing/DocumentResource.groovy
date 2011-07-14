package linksharing

import org.grails.tomcat.SearchFirstURLClassLoader

class DocumentResource extends Resource{
                  String document;
                   String name;


    static mapping = {
        tablePerHierarchy false
    }
    static constraints = {


    }
}

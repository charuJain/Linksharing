import com.intelligrape.linksharing.User
import com.intelligrape.linksharing.Topic
import com.intelligrape.linksharing.UserTopic
import com.intelligrape.linksharing.LinkResource
import com.intelligrape.linksharing.UserResource
import com.intelligrape.linksharing.Resource
import com.intelligrape.linksharing.DocumentResource
import org.hibernate.Session

class BootStrap {

    def bootstrapService

//    def subscriptionService


    def init = { servletContext ->
        if (!User.count()) {
            bootstrapService.addUser()
            //bootstrapService.addTopic()
            bootstrapService.subscribedTopics()
            bootstrapService.createResources()
            bootstrapService.createDocumentResource()

            bootstrapService.createRead()
            bootstrapService.markUnread()
            bootstrapService.printUnread()
        }

    }

    def destroy = {
    }
}

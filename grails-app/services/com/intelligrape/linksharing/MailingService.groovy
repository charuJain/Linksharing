package com.intelligrape.linksharing

class MailingService {

    static transactional = false

    void sendInvitation(List emailIds, Long userId, Long topicId) {
        emailIds.each {String email->
            def invitation = new Invitation();
            invitation.sendFrom = User.get(userId)
            invitation.sendTo = User.findByEmail(email);
            invitation.topic = Topic.get(topicId)
            invitation.save(flush: true)

            if (invitation) {
                sendMail {
                    to invitation.sendTo?.email
                    subject "Hello ${invitation.sendTo?.name}"
                    html "You have got an invitation for the Topic ${invitation.topic?.name} From ${invitation.sendFrom?.name}."
                }
            }
        }
}
}
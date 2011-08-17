<%@ page import="com.intelligrape.linksharing.User; com.intelligrape.linksharing.Topic" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'topic.label', default: 'Topic')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<div class="nav">
    <g:render template="/shared/header"></g:render>
    <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label"
                                                                               args="[entityName]"/></g:link></span>
</div>

<div class="body">
    <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <div class="dialog">
        <table>
            <tbody>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="topic.id.label" default="Id"/></td>

                <td valign="top" class="value">${fieldValue(bean: topicInstance, field: "id")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="topic.name.label" default="Name"/></td>

                <td valign="top" class="value">${fieldValue(bean: topicInstance, field: "name")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="topic.createdBy.label" default="Created By"/></td>

                <td valign="top" class="value">
                    ${user?.name}</td>


                <g:hiddenField name="createdBy.id" value="${user?.id}"/>
                <br>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="topic.isPrivate.label" default="Is Private"/></td>

                <td valign="top" class="value"><g:formatBoolean boolean="${topicInstance?.isPrivate}"/></td>

            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="topic.resources.label" default="Resources"/></td>
                <td valign="top" style="text-align: left;" class="value">
                    <ul>
                        <g:each in="${topicInstance.resources}" var="resource">
                            <li><g:link controller="resource" action="show"
                                        id="${resource?.id}">${resource?.encodeAsHTML()}</g:link></li>
                        </g:each>
                        <g:link controller="linkResource" action="create"
                                params="['topic.id':topicInstance.id]">Add Link Resource</g:link><br>
                        <g:link controller="documentResource" action="create"
                                params="['topic.id':topicInstance.id]">Add Document Resource</g:link>
                    </ul>
                </td>
            </tr>
            </tbody>
        </table>
    </div>

    <div class="buttons">
        <g:form>
            <g:hiddenField name="id" value="${topicInstance?.id}"/>
            <g:if test="${(topicInstance.createdBy.id == session.currentUser) || (User.get(session.currentUser).isAdmin)}">
                <span class="button"><g:actionSubmit class="edit" action="edit"
                                                     value="${message(code: 'default.button.edit.label', default: 'Edit')}"/></span>
                <span class="button">
                    <g:actionSubmit class="delete" action="delete"
                                    value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                                    onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>

                </span>
            </g:if>

            <g:link controller='invitation' action='handler' id="${topicInstance?.id}">send invitations</g:link>
            <g:link controller="userTopic" action="subscribe" id="${topicInstance?.id}">Subscribe it now</g:link>
        </g:form>
    </div>
</div>
</body>
</html>

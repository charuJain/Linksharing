<%@ page import="com.intelligrape.linksharing.User; com.intelligrape.linksharing.Resource" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'resource.label', default: 'Resource')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<div class="nav">
    <g:render template="/shared/header"></g:render>
    <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label"
                                                                           args="[entityName]"/></g:link></span>
    %{--<span class="menuButton"><g:link class="create" action="create"--}%
                                     %{--params="[resourceInstanceId:resourceInstance.id]"><g:message--}%
                %{--code="default.new.label"--}%
                %{--args="[entityName]"/></g:link></span>--}%
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
                <td valign="top" class="name"><g:message code="resource.id.label" default="Id"/></td>

                <td valign="top" class="value">${fieldValue(bean: resourceInstance, field: "id")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="resource.heading.label" default="Heading"/></td>

                <td valign="top" class="value">${fieldValue(bean: resourceInstance, field: "heading")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="resource.summary.label" default="Summary"/></td>

                <td valign="top" class="value">${fieldValue(bean: resourceInstance, field: "summary")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="resource.createdBy.label" default="Created By"/></td>

                <td valign="top" class="value"><g:link controller="user" action="show"
                                                       id="${resourceInstance?.createdBy?.id}">${user.name}</g:link></td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="resource.dateCreated.label" default="Date Created"/></td>

                <td valign="top" class="value"><ls:myDateFormat date="${new Date()}"/></td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="resource.topic.label" default="Topic"/></td>

                <td valign="top" class="value"><g:link controller="topic" action="show"
                                                       id="${resourceInstance?.topic?.id}">${resourceInstance?.topic?.name}</g:link></td>

            </tr>

            </tbody>
        </table>
    </div>


    <div class="buttons">
        <g:form>
            <g:if test="${(resourceInstance.createdBy.id == session.currentUser) || (User.get(session.currentUser).isAdmin)}">
            <g:hiddenField name="id" value="${resourceInstance?.id}"/>

                <span class="button"><g:actionSubmit class="edit" action="edit"
                                                     value="${message(code: 'default.button.edit.label', default: 'Edit')}"/></span>
                <span class="button"><g:actionSubmit class="delete" action="delete"
                                                     value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                                                     onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/></span>

            </g:if>
             <g:link controller="resource" action="markUnread" id="${resourceInstance?.id}">Mark Unread</g:link>
        </g:form>
    </div>
</div>
</body>
</html>

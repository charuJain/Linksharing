<%@ page import="com.intelligrape.linksharing.LinkResource" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'linkResource.label', default: 'LinkResource')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<div class="nav">
    <<g:render template="/shared/header"></g:render>
    <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label"
                                                                           args="[entityName]"/></g:link></span>
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
                <td valign="top" class="name"><g:message code="linkResource.id.label" default="Id"/></td>

                <td valign="top" class="value">${fieldValue(bean: linkResourceInstance, field: "id")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="linkResource.heading.label" default="Heading"/></td>

                <td valign="top" class="value">${fieldValue(bean: linkResourceInstance, field: "heading")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="linkResource.url.label" default="Url"/></td>

                <td valign="top" class="value">${fieldValue(bean: linkResourceInstance, field: "url")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="linkResource.createdBy.label" default="Created By"/></td>

                <td valign="top" class="value"><g:link controller="user" action="show"
                                                       id="${linkResourceInstance?.createdBy?.id}">${user.name}</g:link></td>
                <g:hiddenField name="createdBy.id" value="${user?.id}"/>
                <br>
            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="linkResource.dateCreated.label"
                                                         default="Date Created"/></td>

                <td valign="top" class="value"><g:formatDate date="${linkResourceInstance?.dateCreated}"/></td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="linkResource.summary.label" default="Summary"/></td>

                <td valign="top" class="value">${fieldValue(bean: linkResourceInstance, field: "summary")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="linkResource.topic.label" default="Topic"/></td>

                <td valign="top" class="value"><g:link controller="topic" action="show"
                                                       id='${linkResourceInstance?.topic?.id}'>${linkResourceInstance?.topic?.name}</g:link><br></p>

                    <br></td>

            </tr>

            </tbody>
        </table>
    </div>

    <div class="buttons">
        <g:form>
            <g:hiddenField name="id" value="${linkResourceInstance?.id}"/>


                <span class="button"><g:actionSubmit class="edit" action="edit"
                                                     value="${message(code: 'default.button.edit.label', default: 'Edit')}"/></span>
                <span class="button"><g:actionSubmit class="delete" action="delete"
                                                     value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                                                     onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/></span>


        </g:form>
    </div>
</div>
</body>
</html>

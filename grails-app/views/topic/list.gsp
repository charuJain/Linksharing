<%@ page import="com.intelligrape.linksharing.Topic" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'topic.label', default: 'Topic')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div class="nav">
    <g:render template="/shared/header"></g:render>
    <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label"
                                                                               args="[entityName]"/></g:link></span>
</div>

<div class="body">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <div class="list">
        <table>
            <thead>
            <tr>

                <g:sortableColumn property="id" title="${message(code: 'topic.id.label', default: 'Id')}"/>

                <g:sortableColumn property="name" title="${message(code: 'topic.name.label', default: 'Name')}"/>

                <th><g:message code="topic.createdBy.label" default="Created By"/></th>

                <g:sortableColumn property="isPrivate"
                                  title="${message(code: 'topic.isPrivate.label', default: 'Is Private')}"/>

            </tr>
            </thead>
            <tbody>
            <g:each in="${topicInstanceList}" status="i" var="topicInstance">
                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                    <td><g:link action="show"
                                id="${topicInstance.id}">${fieldValue(bean: topicInstance, field: "id")}</g:link></td>

                    <td>${fieldValue(bean: topicInstance, field: "name")}</td>

                    <td>${fieldValue(bean: topicInstance, field: "createdBy")}</td>

                    <td><g:formatBoolean boolean="${topicInstance.isPrivate}"/></td>

                    <ls:isSubscribed topic="${topicInstance}">
                            <g:form controller="userTopic" action="save">
                                <g:hiddenField name="user.id" value="${session.currentUser}"/>
                                <g:hiddenField name="topic.id" value="${topicInstance?.id}"/>
                                <g:hiddenField name="searchText" value="${searchText}"/>
                                <g:submitButton name="subscribe" value="subscribe"/>
                            </g:form>
                        </ls:isSubscribed>


                </tr>
            </g:each>
            </tbody>
        </table>
    </div>

    <div class="paginateButtons">
        <g:paginate total="${topicInstanceTotal}"/>
    </div>
</div>
</body>
</html>

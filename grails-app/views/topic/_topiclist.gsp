<div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
    </span>
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

                </tr>
            </g:each>
            </tbody>
        </table>
    </div>

    <div class="paginateButtons">
        <g:paginate total="${topicInstanceTotal}"/>
    </div>
</div>
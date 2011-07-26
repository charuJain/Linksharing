<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>

    <meta name="layout" content="main"/>
</head>

<body>

<div class="nav">
    <g:render template="/shared/header"></g:render>
    <span class="menuButton"><g:link controller="user" action="log">logout</g:link></span>
    <span class="menuButton"><g:link controller="topic" action="create">create new topic</g:link>
    </span>

</div>

<div class="body">
    <h1>Create Topic</h1>

    <g:form action="save">
        <div class="dialog">
            <table>
                <tbody>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="name"><g:message code="topic.name.label" default="Name"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: topicInstance, field: 'name', 'errors')}">
                        <g:textField name="name" value="${topicInstance?.name}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                    %{--@declare id="createdby"--}%<label for="createdBy"><g:message code="topic.createdBy.label"
                                                                                     default="Created By"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: topicInstance, field: 'createdBy', 'errors')}">
                        ${user?.name}
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="isPrivate"><g:message code="topic.isPrivate.label" default="Is Private"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: topicInstance, field: 'isPrivate', 'errors')}">
                        <g:checkBox name="isPrivate" value="${topicInstance?.isPrivate}"/>
                    </td>
                </tr>

                </tbody>
            </table>
        </div>

        <div class="buttons">
            <span class="button"><g:submitButton name="create" class="save"
                                                 value="${message(code: 'default.button.create.label', default: 'Create')}"/></span>
        </div>
    </g:form>
</div>
</body>

</html>
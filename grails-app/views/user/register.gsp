<%@ page import="com.intelligrape.linksharing.User" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>


    <script src="${resource(dir: 'js', file: 'jquery.validate.js')}" type="text/javascript"></script>

</head>

<body>
<div class="nav">
    <g:render template="/shared/header"></g:render>
</div>

<div class="body">
    <h1><g:message code="default.create.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${userInstance}">
        <div class="errors">
            <g:renderErrors bean="${userInstance}" as="list"/>
        </div>
    </g:hasErrors>
    <g:form action="registerHandler" name="formId">
        <div class="dialog">
            <table>
                <tbody>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="username"><g:message code="user.username.label" default="Username"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: userInstance, field: 'username', 'errors')}">
                        <g:textField name="username" maxlength="15" value="${userInstance?.username}" class="required"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="password"><g:message code="user.password.label" default="Password"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: userInstance, field: 'password', 'errors')}">
                        <g:passwordField name="password" minLength="9" maxlength="15"
                                         value="${userInstance?.password}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="password"><g:message code="user.password.label" default="confirmPassword"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: userInstance, field: 'confirmPassword', 'errors')}">
                        <g:passwordField name="confirmPassword" maxlength="15"
                                         value="${userInstance?.confirmPassword}" class="required"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="name"><g:message code="user.name.label" default="Name"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: userInstance, field: 'name', 'errors')}">
                        <g:textField name="name" value="${userInstance?.name}" class="required"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="address"><g:message code="user.address.label" default="Address"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: userInstance, field: 'address', 'errors')}">
                        <g:textField name="address" value="${userInstance?.address}" class="required"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="email"><g:message code="user.email.label" default="Email"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: userInstance, field: 'email', 'errors')}">
                        <g:textField name="email" value="${userInstance?.email}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="age"><g:message code="user.age.label" default="Age"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: userInstance, field: 'age', 'errors')}">
                        <g:textField name="age" value="${fieldValue(bean: userInstance, field: 'age')}"
                                     class="required"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="isAdmin"><g:message code="user.isAdmin.label" default="Is Admin"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: userInstance, field: 'isAdmin', 'errors')}">
                        <g:checkBox name="isAdmin" value="${userInstance?.isAdmin}" class="required"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="phoneNumber"><g:message code="user.phoneNumber.label"
                                                            default="Phone Number"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: userInstance, field: 'phoneNumber', 'errors')}">
                        <g:textField name="phoneNumber"
                                     value="${fieldValue(bean: userInstance, field: 'phoneNumber')}"
                                     class="number required"/>
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

<script type="text/javascript">

    $(function() {
        $('#formId').validate({
                    rules: {
                        password: "required",
                        confirmPassword: {
                            equalTo: "#password"
                        },

                        email: {
                            required: true,
                            remote:"${createLink(controller:'user' ,action:'alreadyExisting')}"

                        }
                    }

                })
    })

</script>

</body>

</html>

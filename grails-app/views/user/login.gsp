<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><meta name="layout" content="main"/></head>


<body>

<g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
</g:if>


<g:form controller="user" action="loginHandler" name="formId">
    username <input type="text" name="username" id="userid">    <br>
    password<input type="password" name="password" id="passid">  <br>
    Submit <input type="submit" value="Log In Now!">
    <br><br>
    <g:link controller="user" action="registerHandler">sign up now</g:link>

    </g:form>

    </body>
    </html>
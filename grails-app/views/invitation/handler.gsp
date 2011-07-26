<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
</head>

<body>
<br>
<g:link controller="user" action="log">logout</g:link>
<g:form controller="invitation" action="sendHandler">

    <br>

    <p>from:  ${from?.email}</p>

    <g:hiddenField name="sendFrom" value="${from?.id}"/>
    <br>

    <p>topic: ${topic?.name} <br></p>

    <g:hiddenField name="topic" value="${topic?.id}"/>
    <br>

    <p>to:<input type="text" name="sendTos"></p>    <br>

    <p>to:<input type="text" name="sendTos"></p>    <br>

    <p>to:<input type="text" name="sendTos"></p>

    <p>sending invites <input type="submit" value="sending invites"></p>

</g:form>
</body>
</html>
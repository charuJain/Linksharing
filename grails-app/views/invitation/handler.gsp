<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>

    <meta name="layout" content="main"/>

</head>

<body>
<br>

<div class="nav">
    <g:render template="/shared/header"/>
</div>
<g:form controller="invitation" action="sendHandler">
    <table>
        <br>

        <tr><p>from:  ${from?.email}</p></tr>

        <g:hiddenField name="sendFrom" value="${from?.id}"/>
        <br>

        <tr><p>topic: ${topic?.name} <br></p></tr>

        <g:hiddenField name="topic" value="${topic?.id}"/>
        <br>
        <tr>to:<input type="text" name="sendTos"></tr> <br>

        <tr>to:<input type="text" name="sendTos"></tr> <br>

        <tr>to:<input type="text" name="sendTos"></tr> <br>


        <tr><input type="submit" value="sending invites"></tr>
    </table>
</g:form>

</body>
</html>
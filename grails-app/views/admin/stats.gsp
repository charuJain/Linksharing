<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <script src="${resource(dir: 'js', file: 'jquery.tablesorter.js')}" type="text/javascript"></script>
    <script src="${resource(dir: 'js', file: 'jquery-ui-1.8.14.custom.min.js')}" type="text/javascript"></script>
</head>

<body>

<div class="nav">
    <g:render template="/shared/header"/>
</div>

<h1>Welcome Admin</h1>

<div>

    <div id="tabs">
        <ul>
            <li><a href="#tabs-1">Complete Statistics</a></li>
            <li><a href="#tabs-2">User's List</a></li>
            <li><a href="#tabs-3">Topic's List</a></li>
            <li><a href="#tabs-4">Resorce's List</a></li>
        </ul>

        <div id="tabs-1">

            <tr>Nunber Of User's are:${usercount}</tr>  <br>
            <tr>Number Of Topics are:${topiccount}</tr>     <br>
            <tr>Number Of Invitations are:${invitationcount}</tr><br>

        </div>

        <div id="tabs-2">
            <div id="updateUserTable">
                <g:include action="userInformationPopulate"/>
            </div>
        </div>

        <div id="tabs-3">
            <div id="updateTopicTable">
                <g:include action="topicInformationPopulate"/>

            </div>
        </div>

        <div id="tabs-4">

            <div id="updateResourceTable">
                <g:include action="resourceInformationPopulate"/>

            </div>
        </div>

    </div>
</div>

<jq:jquery>
    $("#tabs").tabs();
</jq:jquery>

</body>

</html>
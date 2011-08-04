<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <script src="${resource(dir: 'js', file: 'jquery.tablesorter.js')}" type="text/javascript"></script>
    <script src="${resource(dir: 'js', file: 'jquery-ui-1.8.14.custom.min.js')}" type="text/javascript"></script>
</head>

<body>

<div class="nav">
    <g:render template="/shared/header"></g:render>
    <span class="menuButton"><g:link controller="topic" action="create">create new topic</g:link>
    </span>

    <g:form controller="topic" action="list">
        Find The topic <g:textField name="searchText"/>
        <g:submitButton name="topicsearch" value="search now"/>
    </g:form>
</div>

<h1 align="center">welcome ${user1.name}</h1>

<div class="demo">

    <div id="tabs">
        <ul>
            <li><a href="#tabs-1">Unread Resouces</a></li>
            <li><a href="#tabs-2">highest subscribed topics</a></li>
            <li><a href="#tabs-3">Most Read Resources</a></li>
            <li><a href="#tabs-4">Subscibed Topics</a></li>

        </ul>

        <div id="tabs-1">
            <div id="updateResourceTable">
                <g:include action="resourcePopulate"/>
        </div>

        <div id="tabs-2">
            <div id="updateHighestSubscribedTable">
                <g:include action="highestSubscribedTopics"/>
            </div>
        </div>

        <div id="tabs-3">
            <table border="1" id="myTable2" class="tablesorter">
                <thead>
                <tr>
                    <th>topics</th>
                    <th>Number Of Reads</th>
                </tr>
                </thead>
                <tbody>
                <g:each in='${userResources}' var="userResource">
                    <tr><td><g:link controller="resource" action="show"
                                    id="${userResource.first().id}">${userResource.first().heading}</g:link></td>
                        <td> ${userResource.last()}</td></tr>
                </g:each>
                </tbody>
            </table>
        </div>

        <div id="tabs-4">
            <table border="1" id="myTable3" class="tablesorter">
                <thead>
                <tr>
                    <th>Topic name</th>
                </tr>
                </thead>
                <tbody>
                <g:each in='${topics}' var='topic'>
                    <tr><td>
                    <g:link controller="topic" action="show" id="${topic?.topic?.id}">
                        ${topic?.topic?.name}</g:link></td>
                </g:each>
                </tbody>
            </table>
        </div>
    </div>

</div>
<script type="text/javascript">
    $(document).ready(function() {
        $("#myTable").tablesorter();
        $("#tabs").tabs();

    }
    );

</script>
</body>
</html>
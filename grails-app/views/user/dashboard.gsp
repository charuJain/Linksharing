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
            <li><a href="#tabs-1">unread items</a></li>
            <li><a href="#tabs-2">highest subscribed topics</a></li>
            <li><a href="#tabs-3">most Read items</a></li>
            <li><a href="#tabs-4">subscibed Resources</a></li>

        </ul>

        <div id="tabs-1">
            <table border="1" id="myTable" class="tablesorter">
                <thead>
                <tr>
                    <th>Resource Heading</th>
                    <th>Resource Belongsto</th>
                    <th>Resource Summary</th>
                </tr>
                </thead>
                <tbody>
                <g:each in='${resources}' var="resource">
                    <tr>
                        <td>${resource?.resource?.heading}</td>
                        <td>${resource?.resource?.topic?.name}</td>
                        <td>${resource.resource.summary}</td>
                    </tr>
                </g:each>
                </tbody>
            </table>
        </div>

        <div id="tabs-2">
            <table border="1" id="myTable1" class="tablesorter">
                <thead>
                <tr>
                    <th>topics</th>
                    <th>count</th>
                </tr>
                </thead>
                <tbody>
                <g:each in='${userTopics}' var="userTopic">
                    <tr>
                       <g:link controller="topic" action="show" id="${userTopic.first().id}">
                        <td>${userTopic.first().name} </g:link>
                        <td> ${userTopic.last()}</tr>
                </g:each>
                </tbody>
            </table>
        </div>

        <div id="tabs-3">
            <table border="1" id="myTable2" class="tablesorter">
                <thead>
                <tr>
                    <th>topics</th>
                    <th>isreadcount</th>
                </tr>
                </thead>
                <tbody>
                <g:each in='${userResources}' var="userResource">
                    <tr><td>${userResource.first().heading}
                        <td> ${userResource.last()}</tr>
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

        %{--<ls:highestSubscribedPublicTopic/>--}%
                %{--<ls:mostReadItems/>--}%
        %{--<div class=" paginateButtons">--}%
            %{--<g:paginate total="${topicCount}"/>--}%

        %{--</div>--}%

    </div>

    </div>
    <script type="text/javascript">
        $(document).ready(function() {
            $("#myTable").tablesorter();
           $( "#tabs" ).tabs();

        }
        );

    </script>
</body>
</html>
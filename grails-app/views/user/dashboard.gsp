<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
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

<div>
    <table border="1">
        <tr>
            <th>Resource Heading</th>
            <th>Resource Belongsto</th>
            <th>Resource Summary</th>
        </tr>

        <h1><u>unread items</u></h1>
        <g:each in='${resources}' var="resource">
            <tr><td>${resource?.resource?.heading}</td>
                <td>${resource?.resource?.topic?.name}</td>
                <td>${resource.resource.summary}</td>   <tr>
        </g:each>
    </table>

    <h1><u>subscibed topics</u></h1>
    <table border="1">
        <tr>
            <th>Topic name</th>
        </tr>
        <g:each in='${topics}' var='topic'>
            <tr><td>
            <g:link controller="topic" action="show" id="${topic?.topic?.id}">
                ${topic?.topic?.name}</td></g:link>
        </g:each>
        
       
    </table>
    <ls:highestSubscribedPublicTopic/>
       <br>
     %{--<ls:mostReadItems/>--}%
    <div class="paginateButtons" >
    <g:paginate total="${topicCount}"/>

      </div>
    
    
</div>
</body>
</html>
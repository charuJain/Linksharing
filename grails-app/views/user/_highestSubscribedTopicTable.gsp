<table border="1" id="myTable1" class="tablesorter">
    <thead>
    <tr>
        <th>Topics</th>
        <th>No. of subscriptions</th>
    </tr>
    </thead>
    <tbody>
    <g:each in='${userTopics}' var="userTopic">
        <tr>
            <td><g:link controller="topic" action="show"
                        id="${userTopic.first().id}">${userTopic.first().name}</g:link></td>
            <td>${userTopic.last()}</td></tr>
    </g:each>
    </tbody>
</table>


<div class="paginateButtons">
    <util:remotePaginate controller="user" action="highestSubscribedTopics" total="${mostSubscribedTopicTotal}"
                         update="updateHighestSubscribedTable" max="5" pageSizes="[5,10,20,50]"/>
</div>
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


<div class="paginateButtons">
    <util:remotePaginate controller="user" action="topicPopulate" total="${topicCount}"
                         update="updateTopicTable" max="5" pageSizes="[5,10,20,50]"/>
</div>
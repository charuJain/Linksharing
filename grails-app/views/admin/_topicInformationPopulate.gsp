<table border="1" id="myTable2" class="tablesorter">
    <thead>
    <tr>
        <th>Name</th>
        <th>Created By</th>
        <th>Date Of Creation</th>
        <th>Private Topic</th>
    </tr>
    </thead>
    <tbody>
    <g:each in='${topics}' var="topic">
        <tr><td><g:link controller="topic" action="show" id="${topic.id}">${topic.name}</g:link></td>

            <td>${topic.createdBy.name}</td>
            <td>${topic.dateCreated}</td>
            <td>${topic.isPrivate}</td>
        </tr>
    </g:each>
    </tbody>
</table>

<div class="paginateButtons">
    <util:remotePaginate controller="admin" action="topicInformationPopulate" total="${topicTotal}"
        update="updateTopicTable" max="5" pageSizes="[5,10,20,50]" />
</div>
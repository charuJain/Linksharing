<table border="1" id="myTable3" class="tablesorter">
    <thead>
    <tr>
        <th>Topic Name</th>
        <th>Created By</th>
        <th>Date of Creation</th>
        <th>Heading Of Resource</th>
        <th>Summary Of Resource</th>
    </tr>
    </thead>
    <tbody>
    <g:each in='${resources}' var="resource">
        <tr><td><g:link controller="resource" action="show"
                        id="${resource.topic.id}">${resource.heading}</g:link>
        </td>
            <td>${resource.summary}</td>
            <td>${resource.createdBy.name}</td>
            <td>${resource.topic.name}</td>
            <td>${resource.dateCreated}</td>

        </tr>
    </g:each>
    </tbody>
</table>

<div class="paginateButtons">
    <util:remotePaginate controller="admin" action="resourceInformationPopulate" total="${resourceTotal}"
        update="updateResourceTable" max="5" pageSizes="[5,10,20,50,100]" />
</div>
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
            <td>${userResource.last()}</td></tr>
    </g:each>
    </tbody>
</table>

<div class="paginateButtons">
    <util:remotePaginate controller="user" action="mostReadResources" total="${mostReadResourceTotal}"
                         update="updateMostReadTable" max="10" pageSizes="[5,10,20,50]"/>
</div>
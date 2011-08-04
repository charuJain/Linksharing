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

            <td>
                <g:link controller="resource" action="show"
                        id="${resource?.resource?.id}">${resource?.resource?.heading}</g:link></td>
            <td>${resource?.resource?.topic?.name}</td>
            <td>${resource?.resource?.summary}</td>
        </tr>
    </g:each>
    </tbody>
</table>


<div class="paginateButtons">
    <util:remotePaginate controller="user" action="resourcePopulate" total="${resourceTotal}"
        update="updateResourceTable" max="5" pageSizes="[5,10,20,50]" />
</div>
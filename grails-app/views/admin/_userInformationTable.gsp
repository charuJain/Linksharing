<table border="1" id="myTable1">
    <thead>
    <tr>
        <th>Name</th>
        <th>UserName</th>
        <th>Password</th>
        <th>Address</th>
        <th>Age</th>
        <th>Email</th>
        <th>PhoneNumber</th>

    </tr>
    </thead>
    <tbody>
    <g:each in='${users}' var="user">
        <tr><td><g:link controller="user" action="show" id="${user.id}">${user.name}</g:link></td>
            <td>${user.username}</td>
            <td>${user.password}</td>
            <td>${user.address}</td>
            <td>${user.age}</td>
            <td>${user.email}</td>
            <td>${user.phoneNumber}</td>
        </tr>
    </g:each>
    </tbody>
</table>

<div class="paginateButtons">
    <util:remotePaginate controller="admin" action="userInformationPopulate" total="${userTotal}"
        update="updateUserTable" max="5" pageSizes="[5,10,20,50]" />
</div>
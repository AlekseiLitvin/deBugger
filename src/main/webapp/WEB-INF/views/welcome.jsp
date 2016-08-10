<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome page</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

    <link href="https://cdn.datatables.net/plug-ins/1.10.7/integration/bootstrap/3/dataTables.bootstrap.css" rel="stylesheet" />
    <script src="https://cdn.datatables.net/1.10.7/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/plug-ins/1.10.7/integration/bootstrap/3/dataTables.bootstrap.js"></script>

    <script>
        $(document).ready(function(){
            $('tbody tr').each(function(){
                var priority = $(this).find("td#priority").html();
                var element = $(this).find("td#priority");
                if (priority == "Critical"){
                    element.css('color', 'red');
                }
                if (priority == "Major"){
                    element.css('color', 'orange');
                }
                if (priority == "Important"){
                    element.css('color', 'purple');
                }
                if (priority == "Minor"){
                    element.css('color', 'green');
                }
            });
            $("#sortableTable").dataTable();
            if (${empty successMessage}){
                $("#success").hide();
            }
        })
    </script>
</head>
<body>
    <%@include file="/templates/header.jsp"%>

    <div class="container">
        <div id="success" class="alert alert-success fade in text-center">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Success!</strong> ${sessionScope.successMessage}
            <c:remove var="successMessage" scope="session" />
        </div>
    </div>

    <c:choose>
        <c:when test="${empty issuesList}">
            <h2 class="text-center">No issues</h2>
        </c:when>
        <c:otherwise>
            <div class="container">
                <h2>All issues</h2>
                <table id = "sortableTable" class="table table-striped">
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>Priority</th>
                        <th>Assignee</th>
                        <th>Type</th>
                        <th>Status</th>
                        <th>Summary</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${issuesList}" var="user">
                        <tr>
                            <td><a href="<c:url value="/editIssuePage/${user.id}"/>">${user.id}</a></td>
                            <td id="priority">${user.priority}</td>
                            <td>${user.assignee.firstName} ${user.assignee.lastName}</td>
                            <td>${user.type}</td>
                            <td>${user.status}</td>
                            <td>${user.summary}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:otherwise>
    </c:choose>

</body>
</html>

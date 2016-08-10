<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All projects</title>
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
            $("#sortableTable").dataTable();
        })
    </script>
    <style>
        .table {
            table-layout:fixed;
        }

        .description{
            overflow: hidden;
            -ms-text-overflow: ellipsis;
            -o-text-overflow: ellipsis;
            text-overflow: ellipsis;
            display: block;
            white-space: nowrap;
        }
    </style>
</head>
<body>
    <%@include file="/templates/header.jsp"%>
    <div class="container">
        <h2>All projects</h2>
        <table id="sortableTable" class="table table-striped">
            <thead>
            <tr>
                <th>Name</th>
                <th>Manager</th>
                <th>Description</th>
            </tr>
            </thead>
            <tbody>
                <c:forEach items="${projectsList}" var="project">
                    <tr>
                        <td><a href="<c:url value="/admin/editProject/${project.id}"/> ">${project.name}</a> </td>
                        <td>${project.manager.firstName} ${project.manager.lastName}</td>
                        <td title="${project.description}"><span class="description">${project.description}</span></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

</body>
</html>

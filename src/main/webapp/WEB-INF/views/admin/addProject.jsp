<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Project addition</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
    <%@include file="/templates/header.jsp"%>
    <div class="container col-md-offset-4 col-md-4">
        <form class="form-signin" method="post" action="<c:url value="/admin/addProject"/> ">
            <h2>Project operations</h2>
            <input type="hidden" name="projectId" value="${project.id}">
            <label for="projectName">Name</label>
            <input name="projectName" value="${project.name}" type="text" id="projectName" class="form-control" placeholder="Project name" required >
            <label for="description">Description</label>
            <input name="projectDescription" value="${project.description}" type="text" id="description" class="form-control" placeholder="Description" required >
            <label for="build">Build</label>
            <input name="projectBuild" value="${project.build}" type="text" id="build" class="form-control" placeholder="Build" required >
            <label for="manager">Manager</label>
            <select name="projectManagerEmail" class="form-control" id="manager">
                <c:forEach items="${usersList}" var="user">
                    <option value="${user.email}">${user.id}.${user.firstName} ${user.lastName}</option>
                </c:forEach>
            </select>
            <br>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
        </form>
        <%@include file="/templates/errorMessage.jsp"%>
    </div>

</body>
</html>

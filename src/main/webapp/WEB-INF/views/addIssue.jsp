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
        <form class="form-signin" method="post" action="<c:url value="/operations/addIssue"/>">
            <h2>Add Issue</h2>
            <label for="summary">Summary</label>
            <input name="summary" type="text" id="summary" class="form-control" placeholder="Summary" required >
            <label for="description">Description</label>
            <input name="description" type="text" id="description" class="form-control" placeholder="Description" required >
            <label for="status">Status</label>
            <select name="status" class="form-control" id="status">
                <c:forEach items="${statuses}" var="status">
                    <option>${status}</option>
                </c:forEach>
            </select>
            <label for="type">Type</label>
            <select name="type" class="form-control" id="type">
                <c:forEach items="${types}" var="type">
                    <option>${type}</option>
                </c:forEach>
            </select>
            <label for="priority">Priority</label>
            <select name="priority" class="form-control" id="priority">
                <c:forEach items="${priorities}" var="priority">
                    <option>${priority}</option>
                </c:forEach>
            </select>
            <label for="project">Project</label>
            <select name="project" class="form-control" id="project">
                <c:forEach items="${projectsList}" var="project">
                    <option value="${project.id}">${project.name}</option>
                </c:forEach>
            </select>

            <label for="assignee">Assignee</label>
            <select name="assigneeEmail" class="form-control" id="assignee">
                <option>Not assigned</option>
                <c:forEach items="${usersList}" var="user">
                    <option value="${user.email}">${user.id}.${user.firstName} ${user.lastName}</option>
                </c:forEach>
            </select>
            <br>

            <input name="modifier" type="hidden" value="${sessionScope.user.email}">
            <button class="btn btn-lg btn-primary btn-block" type="submit">Add issue</button>
        </form>
        <%@include file="/templates/errorMessage.jsp"%>
    </div>
</body>
</html>

<%@ page import="litvin.model.user.Role" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit my profile</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
<%@include file="/templates/header.jsp"%>
<div class="container col-md-offset-4 col-md-4">
    <form class="form-signin" method="post" action="<c:url value="/operations/editMyProfile"/> ">
        <h2>Edit profile</h2>
        <label for="firstName">First name</label>
        <input name="firstName" value="${sessionScope.user.firstName}" type="text" id="firstName" class="form-control">
        <label for="lastName">Last name</label>
        <input name="lastName" value="${sessionScope.user.lastName}" type="text" id="lastName" class="form-control"  required>
        <label for="inputEmail">Email address</label>
        <input name="email" value="${sessionScope.user.email}" type="email" id="inputEmail" class="form-control" required>
        <input type="hidden" name="oldEmail" value="${sessionScope.user.email}">
        <br>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    </form>
    <%@include file="templates/errorMessage.jsp"%>
</div>
</body>
</html>

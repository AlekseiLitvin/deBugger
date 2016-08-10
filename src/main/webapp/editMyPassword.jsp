<%@ page import="litvin.constants.Constants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change password</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
<%@include file="/templates/header.jsp"%>
<div class="container col-md-offset-4 col-md-4">
    <form class="form-signin" method="post" action="<c:url value="operations/editMyPassword"/> ">
        <h2>Change password</h2>
        <label for="inputPassword">New password</label>
        <input name="password" type="password" id="inputPassword" class="form-control" placeholder="Password" required
               pattern="<%=Constants.PASSWORD_PATTERN%>" title="<%=Constants.PASSWORD_TITLE%>">
        <label for="checkPassword">Repeat password</label>
        <input name="passwordCheck" type="password" id="checkPassword" class="form-control" placeholder="Repeat password" required
               pattern="<%=Constants.PASSWORD_PATTERN%>" title="<%=Constants.PASSWORD_TITLE%>" >
        <br>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    </form>
    <%@include file="templates/errorMessage.jsp"%>
</div>
</body>
</html>

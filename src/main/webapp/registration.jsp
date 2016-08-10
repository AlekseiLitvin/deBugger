<%@ page import="litvin.constants.Constants" %>
<%@ page import="litvin.model.user.Role" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
    <%@include file="/templates/header.jsp"%>
    <div class="container col-md-offset-4 col-md-4">
        <form class="form-signin" method="post" action="<c:url value="admin/registration"/> ">
            <h2>Registration</h2>
            <label for="firstName">First name</label>
            <input name="firstName" type="text" id="firstName" class="form-control" placeholder="First name">
            <label for="lastName">Last name</label>
            <input name="lastName" type="text" id="lastName" class="form-control" placeholder="Last name" required>
            <label for="inputEmail">Email address</label>
            <input dirname="email" name="email" type="email" id="inputEmail" class="form-control" placeholder="Email address" required >
            <label for="role">Role</label>
            <select name="role" class="form-control" id="role">
                <option><%=Role.USER.name()%></option>
                <option><%=Role.ADMIN.name()%>></option>
            </select>
            <label for="inputPassword">Password</label>
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

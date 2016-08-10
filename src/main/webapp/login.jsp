<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Singing in</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
    <%@include file="/templates/header.jsp"%>
    <div class="container col-md-offset-4 col-md-4">
        <form class="form-signin" method="post" action="/login">
            <h2>Please sign in</h2>
            <label for="inputEmail" class="sr-only">Email address</label>
            <input name="email" type="email" id="inputEmail" class="form-control" placeholder="Email address" required >
            <label for="inputPassword" class="sr-only">Password</label>
            <input name="password" type="password" id="inputPassword" class="form-control" placeholder="Password" required >
            <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
        </form>
        <%@include file="templates/errorMessage.jsp"%>
    </div>
</body>
</html>

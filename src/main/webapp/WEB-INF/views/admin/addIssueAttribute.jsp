<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add issue attribute</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
<%@include file="/templates/header.jsp"%>
<div class="container col-md-offset-4 col-md-4">
    <form class="form-signin" method="post" action="/admin/addAttribute/${fileName}">
        <h2>Add to ${fileName}</h2>
        <input name="oldAttributeName" type="hidden" value="${oldAttributeName}">
        <input name="newAttributeName" value="${oldAttributeName}" type="text" class="form-control" placeholder="Name" required >
        <br/>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Add</button>
    </form>
    <%@include file="/templates/errorMessage.jsp"%>
</div>
</body>
</html>

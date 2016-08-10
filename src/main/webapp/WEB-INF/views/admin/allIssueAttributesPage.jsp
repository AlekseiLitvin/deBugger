<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All ${attributeName}</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
<%@include file="/templates/header.jsp"%>
<div class="container">
    <h2 class="text-center">All ${attributeName}</h2>
    <div class="row col-md-offset-4 col-md-4">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th class="text-center">Name</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${attributesArray}" var="attribute">
                <tr>
                    <td class="text-center">
                        <a href="<c:url value="/admin/editAttribute/${attributeName}-${attribute}"/> ">${attribute}</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit issue</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

    <script>
        $(document).ready(function(){
            $("select[name='status']").val('${issue.status}');
            $("select[name='type']").val('${issue.type}');
            $("select[name='priority']").val('${issue.priority}');
            $('select[name="project"]').find('option:contains("${issue.project.name}")').attr("selected",true);
            $('select[name="assigneeEmail"]')
                    .find('option:contains("${issue.assignee.id}.${issue.assignee.firstName} ${issue.assignee.lastName}")')
                    .attr("selected",true);


            if (${sessionScope.user.role eq 'GUEST'}){
                $("#editForm").find(":input").attr('disabled', 'disabled');
                $("select").attr('disabled', 'disabled');
                $("textarea").attr('disabled', 'disabled');
                $("button").hide();
            }else {
                if(${issue.status eq 'Closed'}){
                    $("#editForm").find(":input").attr('disabled', 'disabled');
                    $("select").attr('disabled', 'disabled');
                    $("textarea").attr('disabled', 'disabled');
                    $("button").attr('disabled', 'disabled');
                    $("select[name='status']").removeAttr("disabled");
                }

                $("select[name='status']").change( function() {
                    var status = $(this).val();
                    if(status == "Closed" || status == "Resolved"){
                        $("select[name='resolution']").removeAttr("disabled");
                    }else if(status == "Reopened"){
                        $("input").removeAttr("disabled");
                        $("select").removeAttr("disabled");
                        $("textarea").removeAttr("disabled");
                        $("button").removeAttr("disabled");
                    } else {
                        var resolution = $("select[name='resolution']");
                        resolution.attr('disabled', 'disabled');
                        resolution.val("Not finished");
                    }

                });
            }
        });
    </script>
</head>
<body>
<%@include file="/templates/header.jsp"%>

<div class="row">
    <div class="container">
        <div class="col-md-offset-2 col-md-4">
            <form id="editForm" class="form-signin" method="post" enctype="multipart/form-data" action="<c:url value="/operations/editIssue"/>">
                <h2>Edit issue</h2>
                <label>Id: ${issue.id}</label><br>
                <input name="id" value="${issue.id}" type="hidden">

                <label>Crete date: ${issue.createDate}</label><br>
                <label>Created By: ${issue.creator.firstName} ${issue.creator.lastName}</label><br>
                <label>Modify date: ${issue.modifyDate}</label><br>
                <label>ModifiedBy: ${issue.modifier.firstName} ${issue.modifier.lastName}</label><br>
                <label>Build: ${issue.build}</label><br>

                <label for="summary">Summary</label>
                <input name="summary" value="${issue.summary}" type="text" id="summary" class="form-control"  required >
                <label for="description">Description</label>
                <input name="description" value="${issue.description}" type="text" id="description" class="form-control" required >
                <label for="status">Status</label>
                <select name="status" class="form-control" id="status">
                    <c:forEach items="${statuses}" var="status">
                        <option>${status}</option>
                    </c:forEach>
                </select>
                <label for="resolution">Resolution</label>
                <select name="resolution" id="resolution" class="form-control" id="resolution" disabled="disabled">
                    <option>Not finished</option>
                    <c:forEach items="${resolutions}" var="resolution">
                        <option>${resolution}</option>
                    </c:forEach>
                </select>

                <label for="type">Type </label>
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

                <textarea name="comment" style="resize: none" class="form-control" rows="2" maxlength="140" placeholder="Enter your comment here"></textarea><br>
                <input type="file" name="attachment"><br>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Edit issue</button>
                <%@include file="/templates/errorMessage.jsp"%>
            </form>
        </div>
        <div class="col-md-4">
            <h2>Comments</h2>
            <c:forEach items="${issue.comments}" var="comment">
                <label><small>${comment.user.firstName} ${comment.user.lastName} ${comment.date}</small></label><br>
                <label><i>${comment.text}</i></label><hr>
            </c:forEach>
            <h2>Attachments</h2>
            <c:forEach items="${issue.attachments}" var="attachment">
                <label><small>${attachment.user.firstName} ${attachment.user.lastName} ${attachment.date}</small></label><br>
                <a href="<c:url value="/downloadAttachment/${attachment.id}"/> ">Download ${attachment.fileName}</a><hr>
            </c:forEach>
        </div>
    </div>
</div>

</body>
</html>

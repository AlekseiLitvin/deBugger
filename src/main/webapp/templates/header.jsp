<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="<c:url value="/main"/> ">Issue Tracker</a>
        </div>
        <ul class="nav navbar-nav">
            <c:if test="${(user.role eq 'ADMIN') || (user.role eq 'USER')}">
                <li class="active"><a href="<c:url value="/operations/addIssuePage"/> ">Submit Issue</a></li>
            </c:if>
            <c:if test="${user.role eq 'ADMIN'}">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">Content
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="<c:url value="/admin/allProjects"/> ">Projects</a></li>
                        <li><a href="<c:url value="/admin/allIssueAttributes/statuses"/> ">Statuses</a></li>
                        <li><a href="<c:url value="/admin/allIssueAttributes/resolutions"/> ">Resolutions</a></li>
                        <li><a href="<c:url value="/admin/allIssueAttributes/priorities"/> ">Priorities</a></li>
                        <li><a href="<c:url value="/admin/allIssueAttributes/types"/> ">Types</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">Add new
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li class="active"><a href="<c:url value="/registration.jsp"/> ">User</a></li>
                        <li><a href="<c:url value="/admin/addProjectPage"/>">Project</a></li>
                        <li><a href="<c:url value="/admin/addIssueAttr/resolutions"/>">Resolution</a></li>
                        <li><a href="<c:url value="/admin/addIssueAttr/priorities"/>">Priority</a></li>
                        <li><a href="<c:url value="/admin/addIssueAttr/types"/>">Type</a></li>
                    </ul>
                </li>
                <form class="navbar-form navbar-left" role="search" method="post" action="<c:url value="/admin/searchUser"/> ">
                    <div class="form-group">
                        <input name="searchQuery" type="text" class="form-control input-sm" placeholder="Search user by name">
                    </div>
                    <button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button>
                </form>
            </c:if>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <c:choose>
                <c:when test="${user.role eq 'GUEST'}">
                    <li><a href="<c:url value="/login.jsp"/> "><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                </c:when>
                <c:otherwise>
                    <li>
                        <p class="navbar-text" style="color: blue">
                            <span class="glyphicon glyphicon-user"></span>
                            ${user.firstName} ${user.lastName}
                        </p>
                    </li>
                    <li><a href="<c:url value="/operations/allIssues"/> ">All issues <span class="glyphicon glyphicon-folder-open"></span></a></li>
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Settings
                            <span class="glyphicon glyphicon-cog"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="<c:url value="/editMyProfile.jsp"/> ">Edit my profile</a></li>
                            <li><a href="<c:url value="/editMyPassword.jsp"/> ">Change password</a></li>
                            <li><a href="<c:url value="/operations/logout"/> ">Logout</a></li>
                        </ul>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</nav>

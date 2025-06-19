<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Settings</title>
    <style>
        body { font-family: sans-serif; margin: 2em; }
        .form-group { margin-bottom: 1em; }
        label { display: inline-block; width: 100px; font-weight: bold; }
        textarea { vertical-align: top; }
    </style>
</head>
<body>
<h1>Settings</h1>
<%--@elvariable id="emailConfig" type="com"--%>
<form:form method="post" action="settings/update" modelAttribute="emailConfig">
    <div class="form-group">
        <label>Languages</label>
            <%-- path="languages" liên kết với thuộc tính "languages" của object emailConfig --%>
        <form:select path="languages">
            <form:options items="${languages}"/>
        </form:select>
    </div>

    <div class="form-group">
        <label>Page Size</label>
        <sp>Show
        <form:select path="pageSize">
             <form:options items="${pageSizes}"/>
        </form:select> emails per page</sp>
    </div>

    <div class="form-group">
        <label>Spams filter:</label>
        <form:checkbox path="spamsFilter"/> Enable spams filter
    </div>

    <div class="form-group">
        <label>Signature:</label>
        <form:textarea path="signature" rows="4" cols="30"/>
    </div>

    <div class="form-group">
        <button type="submit">Update</button>
        <button type="button">Cancel</button>
    </div>
</form:form>

</body>
</html>

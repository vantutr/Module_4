<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 6/19/2025
  Time: 2:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>${message}</h2>

<h3>Current Configuration:</h3>
<p><strong>Language:</strong> ${updatedConfig.languages}</p>
<p><strong>Page Size:</strong> ${updatedConfig.pageSize}</p>
<p><strong>Spam Filter Enabled:</strong> ${updatedConfig.spamsFilter}</p>
<p><strong>Signature:</strong></p>
<pre>${updatedConfig.signature}</pre>
<br/>
<a href="/emailConfig/settings">Back to Settings</a>

</body>
</html>

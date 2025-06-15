<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${vietnamese != null}">
  <p>Nghĩa của từ ${english} là: ${vietnamese}</p>
</c:if>
<c:if test="${vietnamese == null}">
  <p>Không tìm thấy nghĩa của từ</p>
</c:if>

<a href="/index">Quay lại trang chủ</a>

</body>
</html>

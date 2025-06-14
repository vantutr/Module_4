<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Customer List</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 40px;
      background-color: #f8f9fa;
    }

    table {
      width: 100%;
      border-collapse: collapse;
      background-color: #fff;
      box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
      border-radius: 8px;
      overflow: hidden;
    }

    th, td {
      padding: 12px 16px;
      text-align: left;
    }

    th {
      background-color: #343a40;
      color: white;
    }

    tr:nth-child(even) {
      background-color: #f1f1f1;
    }

    tr:hover {
      background-color: #e2e6ea;
    }

    a {
      color: #007bff;
      text-decoration: none;
    }

    a:hover {
      text-decoration: underline;
    }
  </style>
</head>
<body>
<h2>Danh sách <span style="color: red">${requestScope.customers.size()}</span> khách hàng</h2>
<table>
  <tr>
    <th>Id</th>
    <th>Name</th>
    <th>Email</th>
    <th>Address</th>
  </tr>
  <c:forEach items='${requestScope.customers}' var="c">
    <tr>
      <td>
        <c:out value="${c.id}"/>
      </td>
      <td>
        <a href="customers/info?id=${c.id}">${c.name}</a>
      </td>
      <td>
        <c:out value="${c.email}"/>
      </td>
      <td>
        <c:out value="${c.address}"/>
      </td>
    </tr>
  </c:forEach>
</table>
</body>
</html>

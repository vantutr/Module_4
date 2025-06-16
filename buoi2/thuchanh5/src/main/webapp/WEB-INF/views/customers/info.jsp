<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Customer Detail</title>
  <style>
    body {
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
      margin: 40px;
      background-color: #f4f6f8;
    }

    form {
      background-color: #fff;
      padding: 24px;
      border-radius: 10px;
      max-width: 600px;
      box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
    }

    fieldset {
      border: none;
    }

    legend {
      font-size: 20px;
      font-weight: bold;
      margin-bottom: 10px;
    }

    table {
      width: 100%;
    }

    td {
      padding: 10px;
      vertical-align: middle;
    }

    input[type="text"] {
      width: 100%;
      padding: 8px 12px;
      border: 1px solid #ccc;
      border-radius: 6px;
      box-sizing: border-box;
    }

    a {
      display: inline-block;
      margin-top: 20px;
      text-decoration: none;
      color: #007bff;
    }

    a:hover {
      text-decoration: underline;
    }
  </style>
</head>
<body>

<form>
  <fieldset>
    <legend>Thông tin chi tiết khách hàng</legend>
    <c:if test="${customer != null}">
      <table>
        <tr>
          <td><strong>Id:</strong></td>
          <td>
            <c:out value="${customer.id}"/>
          </td>
        </tr>
        <tr>
          <td><strong>Name:</strong></td>
          <td><c:out value="${customer.name}"/></td>
        </tr>
        <tr>
          <td><strong>Email:</strong></td>
          <td><c:out value="${customer.email}"/></td>
        </tr>
        <tr>
          <td><strong>Address:</strong></td>
          <td><c:out value="${customer.address}"/></td>
        </tr>
      </table>
    </c:if>
    <c:if test="${customer == null}">
      <p>Không tìm thấy thông tin khách hàng.</p>
    </c:if>
  </fieldset>
</form>

<p><a href="/customers">← Quay lại danh sách</a></p>

</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Máy tính cá nhân</title>
    <style>
        body {
            font-family: sans-serif;
        }
        fieldset {
            width: 500px;
            border: 1px solid #ccc;
            border-radius: 5px;
            padding: 20px;
        }
        legend {
            font-size: 1.5em;
            font-weight: bold;
        }
        input[type="text"] {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
            box-sizing: border-box;
        }
        button {
            padding: 10px 20px;
            margin-right: 6px;
            border: none;
            background-color: #5c67f2;
            color: white;
            cursor: pointer;
        }
        #result {
            margin-top: 15px;
            font-size: 1.2em;
            font-weight: bold;
        }
        .error {
            color: red;
        }
    </style>
</head>
<body>

<form action="<c:url value="/calculate"/>" method="post">
    <fieldset>
        <legend>Calculator</legend>
        <input type="text" name="firstOperand" placeholder="First operand" value="${firstOperand}" required>
        <input type="text" name="secondOperand" placeholder="Second operand" value="${secondOperand}" required>
        <div>
            <button type="submit" name="operator" value="Addition(+)">Addition(+)</button>
            <button type="submit" name="operator" value="Subtraction(-)">Subtraction(-)</button>
            <button type="submit" name="operator" value="Multiplication(x)">Multiplication(x)</button>
            <button type="submit" name="operator" value="Division(/)">Division(/)</button>
        </div>
    </fieldset>
</form>

<div id="result">
    <h2>Result: </h2>
    <c:if test="${not empty result}">
        <span>${result}</span>
    </c:if>
    <c:if test="${not empty error}">
        <span class="error">${error}</span>
    </c:if>
</div>

</body>
</html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <style>
        body {
            font-family: Georgia, serif;
            margin: 40px;
        }

        h1 {
            font-size: 28px;
            font-weight: bold;
        }

        .condiments {
            display: flex;
            gap: 20px;
            margin: 20px 0;
            font-size: 24px;
        }

        label {
            display: flex;
            align-items: center;
            gap: 5px;
        }

        hr {
            margin: 20px 0;
        }

        button {
            font-size: 16px;
            padding: 6px 14px;
            border-radius: 6px;
            border: 1px solid #ccc;
            cursor: pointer;
            background-color: white;
        }

        button:hover {
            background-color: #f0f0f0;
        }
    </style>
</head>
<body>

<h1>Sandwich Condiments</h1>

<form action="{condiment}" method="post">
    <div class="condiments">
        <label><input type="checkbox" name="condiment" value="lettuce">Lettuce</label>
        <label><input type="checkbox" name="condiment" value="tomato">Tomato</label>
        <label><input type="checkbox" name="condiment" value="mustard">Mustard</label>
        <label><input type="checkbox" name="condiment" value="sprouts">Sprouts</label>
    </div>

    <hr>

    <button type="submit">Save</button>
</form>

</body>
</html>
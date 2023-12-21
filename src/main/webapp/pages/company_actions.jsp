<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Действия перевозчика</title>
</head>

<body>
<div>
    Выберите действие:<br><br>
    <form action="company_actions" method="post">
        <button name="buttonType" value="loginButton"> Вход </button><br><br>
        <button name="buttonType" value="registerButton"> Регистрация </button><br><br><br>
        <button name="buttonType" value="exitButton"> На главную... </button><br><br>
    </form>
</div>
</body>

</html>

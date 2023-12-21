<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Авторизация (компания)</title>
</head>

<body>
<div>
    Введите логин и пароль компании-перевозчика:<br><br>
    <form action="company_login" method="post">
    <label>Введите логин:</label><br>
    <input name="login" id="login" type="text" /><br>
    <label>Введите пароль:</label><br>
    <input name="password" id="password" type="password" /><br>
    <span>${requestScope.errorText}</span><br>
    <button name="buttonType" value="companyButton"> Войти </button><br><br><br>
    <button name="buttonType" value="companyActionsButton"> Назад... </button>
    </form>
</div>
</body>

</html>

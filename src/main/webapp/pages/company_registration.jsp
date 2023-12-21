<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Регистрация (компания)</title>
</head>


<body>
<div>
    Введите данные для создания аккаунта компании-перевозчика:<br><br>
    <form action="company_registration" method="post">
    <label>Логин:</label><br>
    <input name="login" id="login" type="text" /><br>
    <label>Введите название:</label><br>
    <input name="company_name" id="company_name" type="text" /><br>
    <label>Введите пароль:</label><br>
    <input name="password" id="password" type="password" /><br>
    <span>${requestScope.errorText}</span><br>
    <button name="buttonType" value="companyButton"> Зарегистрироваться </button><br><br><br>
    <button name="buttonType" value="companyActionsButton"> Назад... </button>

    </form>
</div>
</body>

</html>

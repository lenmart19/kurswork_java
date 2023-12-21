<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Компания-перевозчик</title>
</head>


<body>
<div>
    Компания-перевозчик<br><br>
    <form action="company" method="post">
        <button name="buttonType" value="showCompanyTripsButton"> Посмотреть рейсы </button><br>
        <button name="buttonType" value="createTripButton"> Создать рейс </button><br>
        <button name="buttonType" value="editTripButton"> Изменить рейс </button><br>
        <button name="buttonType" value="deleteTripButton"> Удалить рейс </button><br><br><br>
        <button name="buttonType" value="exitButton"> На главную... </button><br><br>
    </form>
</div>
</body>
</html>


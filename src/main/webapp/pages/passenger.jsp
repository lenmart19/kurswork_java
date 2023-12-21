<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Пассажир</title>
</head>


<body>
<div>
    Пассажир<br><br>
    <form action="passenger" method="post">
    <button name="buttonType" value="showPassengerTripsButton"> Посмотреть доступные билеты </button><br>
    <button name="buttonType" value="pass_in_tripButton"> Забронировать билет </button><br>
    <button name="buttonType" value="deletePass_in_tripButton"> Вернуть билет </button><br><br><br>
    <button name="buttonType" value="exitButton"> На главную... </button><br>
    </form>
</div>
</body>

</html>

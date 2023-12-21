<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Создание рейса</title>
</head>


<body>
<div>
    Создание рейса<br>
    <form action="create_trip" method="post">
        Рейс: <input type="text" name="trip_name" /><br>
        Время отправления: <input type="datetime-local" name="time_from" /><br>
        Время прибытия: <input type="datetime-local" name="time_to" /><br>
        Автобус: <input type="text" name="bus" /><br><br>
    <button name="buttonType" value="createTripButton"> Создать </button><br><br><br>
        <% if (request.getAttribute("successMessage") != null) { %>
        <div class="successMessage"><%= request.getAttribute("successMessage") %></div>
        <% } %>
        <% if (request.getAttribute("errorMessage") != null) { %>
        <div class="errorMessage"><%= request.getAttribute("errorMessage") %></div>
        <% } %>
    <button name="buttonType" value="companyButton"> Назад... </button>
    </form>
</div>
</body>
</html>


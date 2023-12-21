<%@ page import="model.Trip" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Доступные билеты</title>
    <style> table {
        border-collapse: collapse;
    }
    table, th, td {
        border: 1px solid black;
    } </style>
</head>

<body>
<div>
    Доступные билеты<br>
<% if (request.getAttribute("trips") != null) { %>
    <table>
        <thead>
        <tr>
            <th>Рейс</th>
            <th>Время отправления</th>
            <th>Время прибытия</th>
            <th>Компания-перевозчик</th>
            <th>Автобус</th>
        </tr>
        </thead>
        <tbody>
        <% List<Trip> trips = (List<Trip>) request.getAttribute("trips");
            for (Trip trip : trips) { %>
        <tr>
            <td><%= trip.getTrip_name() %></td>
            <td><%= trip.getTime_from() %></td>
            <td><%= trip.getTime_to() %></td>
            <td><%= trip.getCompanyName() %></td>
            <td><%= trip.getBus() %></td>
        </tr>
        <% } %>
        </tbody>
    </table>
        <% } else { %>
        <% } %>
    <br><span>${requestScope.errorText}</span><br><br>
    <form action="show_passenger_trips" method="post">
        <button name="buttonType" value="passengerButton"> Назад... </button>
    </form>
</body>

</html>
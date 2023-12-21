<%@ page import="model.Trip" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Рейсы компании</title>
    <style> table {
        border-collapse: collapse;
    }
    table, th, td {
        border: 1px solid black;
    } </style>
</head>

<body>
<div>
    Рейсы компании<br>
        <% if (request.getAttribute("trips") != null) { %>
    <table>
        <thead>
        <tr>
            <th>Рейс</th>
            <th>Время отправления</th>
            <th>Время прибытия</th>
            <th>Автобус</th>
            <th>Кол-во пассажиров</th>
        </tr>
        </thead>
        <tbody>
        <% List<Trip> trips = (List<Trip>) request.getAttribute("trips");
            for (Trip trip : trips) { %>
        <tr>
            <td><%= trip.getTrip_name() %></td>
            <td><%= trip.getTime_from() %></td>
            <td><%= trip.getTime_to() %></td>
            <td><%= trip.getBus() %></td>
            <td><%= trip.getPas_count() %></td>
        </tr>
        <% } %>
        </tbody>
    </table>
        <% } else { %>
        <% } %>
    <br><span>${requestScope.errorText}</span><br>
    <form action="show_company_trips" method="post">
        <button name="buttonType" value="companyButton"> Назад... </button>
    </form>
</body>

</html>
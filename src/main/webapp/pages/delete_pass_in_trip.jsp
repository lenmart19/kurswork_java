<%@ page import="model.Trip" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Отмена бронирования</title>
</head>

<body>
<div>
    Отмена бронирования<br>
    <form action="delete_pass_in_trip" method="post">
    Введите email: <input type="email" name="email" /><br>
        Выберите рейс:
    <select name="trip_id">
            <% List<Trip> trips = (List<Trip>) request.getAttribute("trips");
                if (trips != null) {
                    for (Trip trip : trips) { %>
            <option value="<%= trip.getId_t() %>">
                <%= trip.getTrip_name() %> - <%= trip.getTime_from() %> - <%= trip.getTime_to() %>
            </option>
            <% }
            } %>
        </select><br><br>
    <button name="buttonType" value="deletePass_in_tripButton"> Удалить </button><br><br><br>
    <% if (request.getAttribute("successMessage") != null) { %>
    <div class="successMessage"><%= request.getAttribute("successMessage") %></div>
    <% } %>
    <% if (request.getAttribute("errorMessage") != null) { %>
    <div class="errorMessage"><%= request.getAttribute("errorMessage") %></div>
    <% } %>
    <button name="buttonType" value="passengerButton"> Назад... </button>
    </form>
</div>
</body>
</html>
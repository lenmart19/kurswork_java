<%@ page import="model.Trip" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Бронирование билета</title>
</head>


<body>
<div>
    Бронирование билета<br>
    <form action="pass_in_trip" method="post">
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
        </select><br>
        Введите имя: <input type="text" name="passenger_name" /><br>
        Введите email: <input type="email" name="email" /><br><br>
        <button name="buttonType" value="pass_in_tripButton"> Забронировать </button><br><br><br>
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


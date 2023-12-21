<%@ page import="model.Trip" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Изменение рейса</title>
</head>

<body>
<div>
    Изменение рейса<br>
    <form action="edit_trip" method="post">
        Выберите рейс, который необходимо изменить:
        <select name="trip_id">
            <% List<Trip> trips = (List<Trip>) request.getAttribute("trips");
                if (trips != null) {
                    for (Trip trip : trips) { %>
            <option value="<%= trip.getId_t() %>">
                <%= trip.getTrip_name() %> - <%= trip.getTime_from() %> - <%= trip.getTime_to() %> - <%= trip.getBus() %>
            </option>
            <% }
            } %>
        </select><br>
        Введите время отправления: <input type="datetime-local" name="time_from" /><br>
        Введите время прибытия: <input type="datetime-local" name="time_to" /><br>
        Введите автобус: <input type="text" name="bus" /><br>
        <button name="buttonType" value="editTripButton"> Изменить </button><br><br><br>
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


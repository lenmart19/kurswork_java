package services.db;
import model.Trip;
import model.Company;
import model.Passenger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PassengerDBService {
    public List<Trip> showPassengerTrips() {
        List<Trip> trips = new ArrayList<>();
        DataBaseService dataBaseService = new DataBaseService();
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        String sql = "SELECT trip.trip_name, trip.time_from, trip.time_to, company.company_name, trip.bus FROM trip INNER JOIN company ON trip.company_n = company.login WHERE time_from > '"+formattedDateTime+"' ORDER BY time_from ASC";
        try (Statement statement = dataBaseService.getConnect().createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Trip trip = new Trip();
                trip.setTrip_name(resultSet.getString("trip_name"));
                trip.setCompanyName(resultSet.getString("company_name"));
                trip.setTime_from(resultSet.getString("time_from"));
                trip.setTime_to(resultSet.getString("time_to"));
                trip.setBus(resultSet.getString("bus"));
                trips.add(trip);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trips;
    }

    public List<Trip> showUpcomingPassengerTrips() {
        List<Trip> trips = new ArrayList<>();
        DataBaseService dataBaseService = new DataBaseService();
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        String sql = "SELECT * FROM trip WHERE time_from > '" + formattedDateTime + "' ORDER BY time_from ASC";
        try (Statement statement = dataBaseService.getConnect().createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Trip trip = new Trip();
                trip.setId_t(resultSet.getInt("id_t"));
                trip.setTrip_name(resultSet.getString("trip_name"));
                trip.setTime_from(resultSet.getString("time_from"));
                trip.setTime_to(resultSet.getString("time_to"));
                trip.setBus(resultSet.getString("bus"));
                trips.add(trip);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trips;
    }

    public boolean isUserExist(String email) {
        boolean userExist = false;
        DataBaseService dataBaseService = new DataBaseService();
        String sql = "SELECT * FROM passenger WHERE email = '" + email + "'";
        try (Statement statement = dataBaseService.getConnect().createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                userExist = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userExist;
    }

    public Boolean editPassengerName(Passenger passenger){
        Boolean isSuccess = false;
        DataBaseService dataBaseService = new DataBaseService();
        String sql = "UPDATE passenger SET passenger_name = '"+passenger.getPassenger_name()+"' WHERE email = '"+passenger.getEmail()+"'";
        if(dataBaseService.update(sql)){
            isSuccess =true;
        } else {
        }
        return isSuccess;
    }

    public Boolean createPassenger(Passenger passenger){
        Boolean isSuccess = false;
        DataBaseService dataBaseService = new DataBaseService();
        String sql = "INSERT INTO passenger (passenger_name, email)" +
                "VALUES ('"+passenger.getPassenger_name()+"','"+passenger.getEmail()+"')";
        if(dataBaseService.insert(sql)){
            isSuccess = true;
        } else {
        }
        return isSuccess;
    }

    public Passenger getPassengerByEmail(String email) {
        Passenger passenger = null;
        DataBaseService dataBaseService = new DataBaseService();
        String sql = "SELECT * FROM passenger WHERE email = '" + email + "'";
        try (Statement statement = dataBaseService.getConnect().createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                passenger = new Passenger();
                passenger.setId_p(resultSet.getInt("id_p"));
                passenger.setPassenger_name(resultSet.getString("passenger_name"));
                passenger.setEmail(resultSet.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passenger;
    }

    public Boolean createPass_in_trip(int tripId, int passengerId) {
        Boolean isSuccess = false;
        DataBaseService dataBaseService = new DataBaseService();
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        String sql = "INSERT INTO pass_in_trip (trip_n, passenger_n, registration_time) " +
                "VALUES (" + tripId + ", " + passengerId + ", '" + formattedDateTime + "')";
        if (dataBaseService.insert(sql)) {
            isSuccess = true;
        }
        return isSuccess;
    }

    public boolean isPass_in_tripExist(String email, int tripId) {
        boolean pitExist = false;
        DataBaseService dataBaseService = new DataBaseService();
        String sql = "SELECT * FROM pass_in_trip WHERE passenger_n IN (SELECT id_p FROM passenger WHERE email = '" + email + "') AND trip_n = '" + tripId + "'";
        try (Statement statement = dataBaseService.getConnect().createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                pitExist = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pitExist;
    }

    public Boolean deletePass_in_trip(String email, int tripId){
        Boolean isSuccess = false;
        DataBaseService dataBaseService = new DataBaseService();
        String sql = "DELETE FROM pass_in_trip WHERE passenger_n IN (SELECT id_p FROM passenger WHERE email = '" + email + "') AND trip_n = '" + tripId + "'";
        if(dataBaseService.delete(sql)){
            isSuccess =true;
        } else {
        }
        return isSuccess;
    }

}

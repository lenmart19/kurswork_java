package services.db;
import model.Trip;
import model.Company;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CompanyDBService {

    public Boolean registrationCompany(Company company){
        Boolean isSuccess = false;
        DataBaseService dataBaseService = new DataBaseService();
        String sql = "INSERT INTO company (login, company_name, password)" +
                "VALUES ('"+company.getLogin()+"', '"+company.getCompany_name()+"','"+company.getPassword()+"')";
        if(dataBaseService.insert(sql)){
            isSuccess =true;
        } else {
        }
        return isSuccess;
    }

    public boolean isLoginUnique(String login) {
        boolean isUnique = true;
        DataBaseService dataBaseService = new DataBaseService();
        String sql = "SELECT login FROM company WHERE login = '" + login + "'";
        try (Statement statement = dataBaseService.getConnect().createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                isUnique = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isUnique;
    }

    public Boolean createTrip(Company company, Trip trip){
        Boolean isSuccess = false;
        DataBaseService dataBaseService = new DataBaseService();
        String sql = "INSERT INTO trip (trip_name, time_from, time_to, company_n, bus)" +
                "VALUES ('"+trip.getTrip_name()+"','"+trip.getTime_from()+"','"+trip.getTime_to()+"','"+company.getLogin()+"','"+trip.getBus()+"')";
        if(dataBaseService.insert(sql)){
            isSuccess =true;
        } else {
        }
        return isSuccess;
    }

    public List<Trip> showCompanyTrips(Company company) {
        List<Trip> trips = new ArrayList<>();
        DataBaseService dataBaseService = new DataBaseService();
        String sql = "SELECT trip_name, time_from, time_to, bus, pas_count FROM trip WHERE company_n = '" + company.getLogin() + "' ORDER BY time_from ASC";
        try (Statement statement = dataBaseService.getConnect().createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Trip trip = new Trip();
                trip.setTrip_name(resultSet.getString("trip_name"));
                trip.setTime_from(resultSet.getString("time_from"));
                trip.setTime_to(resultSet.getString("time_to"));
                trip.setBus(resultSet.getString("bus"));
                trip.setPas_count(resultSet.getInt("pas_count"));
                trips.add(trip);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trips;
    }

    public List<Trip> showUpcomingCompanyTrips(Company company) {
        List<Trip> trips = new ArrayList<>();
        DataBaseService dataBaseService = new DataBaseService();
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        String sql = "SELECT * FROM trip WHERE company_n = '" + company.getLogin() + "' AND time_from > '"+formattedDateTime+"' ORDER BY time_from ASC";
        try (Statement statement = dataBaseService.getConnect().createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Trip trip = new Trip();
                trip.setId_t(resultSet.getInt("id_t"));
                trip.setTrip_name(resultSet.getString("trip_name"));
                trip.setTime_from(resultSet.getString("time_from"));
                trip.setTime_to(resultSet.getString("time_to"));
                trip.setBus(resultSet.getString("bus"));
                trip.setPas_count(resultSet.getInt("pas_count"));
                trips.add(trip);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trips;
    }

    public Boolean editTrip(Company company, Trip trip){
        Boolean isSuccess = false;
        DataBaseService dataBaseService = new DataBaseService();
        String sql = "UPDATE trip SET time_from = '"+trip.getTime_from()+"', time_to = '"+trip.getTime_to()+"', bus = '"+trip.getBus()+"' WHERE id_t = '"+trip.getId_t()+"' AND company_n = '"+company.getLogin()+"'";
        if(dataBaseService.update(sql)){
            isSuccess =true;
        } else {
        }
        return isSuccess;
    }

    public Boolean deleteTrip(Company company, Trip trip){
        Boolean isSuccess = false;
        DataBaseService dataBaseService = new DataBaseService();
        String sql = "DELETE FROM trip WHERE id_t = '"+trip.getId_t()+"' AND company_n = '"+company.getLogin()+"'";
        if(dataBaseService.delete(sql)){
            isSuccess =true;
        } else {
        }
        return isSuccess;
    }

}


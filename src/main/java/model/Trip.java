package model;
import lombok.Data;
import javax.servlet.http.HttpServletRequest;

@Data
public class Trip {
    private Integer id_t;
    private String trip_name;
    private String time_from;
    private String time_to;
    private Company company;
    private String companyName;
    private String bus;
    private Integer pas_count;

    public Trip() {
    }

    public Trip(Integer id_t, String trip_name, String time_from, String time_to, Company company, String bus, Integer pas_count) {
        this.id_t = id_t;
        this.trip_name = trip_name;
        this.time_from = time_from;
        this.time_to = time_to;
        this.bus = bus;
        this.pas_count = pas_count;
    }

    public Trip(HttpServletRequest request) {
        this.trip_name = request.getParameter("trip_name");
        this.time_from = request.getParameter("time_from");
        this.time_to = request.getParameter("time_to");
        this.bus = request.getParameter("bus");
    }


    public Integer getId_t() {
        return id_t;
    }

    public void setId_t(Integer id_t) {
        this.id_t = id_t;
    }

    public String getTrip_name() {
        return trip_name;
    }

    public void setTrip_name(String trip_name) {
        this.trip_name = trip_name;
    }

    public String getTime_from() {
        return time_from;
    }

    public void setTime_from(String time_from) {
        this.time_from = time_from;
    }

    public String getTime_to() {
        return time_to;
    }

    public void setTime_to(String time_to) {
        this.time_to = time_to;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getBus() {
        return bus;
    }

    public void setBus(String bus) {
        this.bus = bus;
    }

    public Integer getPas_count() {
        return pas_count;
    }

    public void setPas_count(Integer pas_count) {
        this.pas_count = pas_count;
    }

}

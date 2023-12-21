package model;
import lombok.Data;

@Data
public class Passenger {
    private Integer id_p;
    private String passenger_name;
    private String email;

    public Passenger() { }

    public Passenger(Integer id_p, String passenger_name, String email) {
        this.id_p = id_p;
        this.passenger_name = passenger_name;
        this.email = email;
    }

    public Integer getId_p() { return id_p; }

    public void setId_p(Integer id_p) {
        this.id_p = id_p;
    }

    public String getPassenger_name() {
        return passenger_name;
    }

    public void setPassenger_name(String passenger_name) {
        this.passenger_name = passenger_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

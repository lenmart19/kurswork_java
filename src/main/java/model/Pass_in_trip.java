package model;
import lombok.Data;

@Data
public class Pass_in_trip {
    private Integer id_pit;
    private Trip trip;
    private Passenger passenger;
    private String registration_time;

    public Pass_in_trip(Integer id_pit, Trip trip, Passenger passenger, String registration_time) {
        this.id_pit = id_pit;
        this.trip = trip;
        this.passenger = passenger;
        this.registration_time = registration_time;
    }

    public Integer getId_pit() {
        return id_pit;
    }

    public void setId_pit(Integer id_pit) {
        this.id_pit = id_pit;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public String getRegistration_time() {
        return registration_time;
    }

    public void setRegistration_time(String registration_time) {
        this.registration_time = registration_time;
    }
}

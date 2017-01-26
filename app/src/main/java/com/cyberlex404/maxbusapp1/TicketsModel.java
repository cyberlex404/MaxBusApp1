package com.cyberlex404.maxbusapp1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TicketsModel {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("telephone")
    @Expose
    private String telephone;
    @SerializedName("flight_name")
    @Expose
    private String flightName;
    @SerializedName("seats")
    @Expose
    private String seats;
    @SerializedName("flight_id")
    @Expose
    private String flightId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

}
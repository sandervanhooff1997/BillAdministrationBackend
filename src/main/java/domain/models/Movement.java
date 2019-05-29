package domain.models;

import java.awt.geom.Point2D;
import java.util.Date;

public class Movement {

    private Long id;

    private String serialNumber;

    private String address;

    private Point2D.Double coordinate;

    private String authCode;

    private Date date;

    private Long carTrackerId;

    private Double distance;

    public Movement() {
    }

    public Movement(String serialNumber, Long carTracker) {
        this.serialNumber = serialNumber;
        this.carTrackerId = carTracker;
    }

    public Movement(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Point2D.Double getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Point2D.Double coordinate) {
        this.coordinate = coordinate;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getCarTrackerId() {
        return carTrackerId;
    }

    public void setCarTrackerId(Long carTrackerId) {
        this.carTrackerId = carTrackerId;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Movement{" +
                "id=" + id +
                ", serialNumber='" + serialNumber + '\'' +
                ", address='" + address + '\'' +
                ", coordinate=" + coordinate +
                ", authCode='" + authCode + '\'' +
                ", date=" + date +
                ", carTrackerId=" + carTrackerId +
                ", distance=" + distance +
                '}';
    }
}
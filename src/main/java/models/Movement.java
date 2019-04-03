package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Calendar;
import java.util.Date;

public class Movement {
    private Long id;
    private Long serialNumber;
    private String authorisationCode;
    private Date date;
    private CarTracker carTracker;

    public Movement(Long id, Long serialNumber, String authorisationCode, Date date, CarTracker cartracker) {
        this.id = id;
        this.serialNumber = serialNumber;
        this.authorisationCode = authorisationCode;
        this.date = date;
        this.carTracker = cartracker;
    }


    public CarTracker getCarTracker() {
        return carTracker;
    }

    public void setCarTracker(CarTracker carTracker) {
        this.carTracker = carTracker;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Long serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getAuthorisationCode() {
        return authorisationCode;
    }

    public void setAuthorisationCode(String authorisationCode) {
        this.authorisationCode = authorisationCode;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMonth() {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH);
        return String.valueOf( month );
    }
}

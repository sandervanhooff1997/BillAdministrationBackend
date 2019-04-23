package domain.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.awt.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Movement implements Serializable {
    private Long id;
    private Long serialNumber;
    private String authorisationCode;
    private Date date;
    private CarTracker carTracker;
    private Point coord = new Point();

    public Movement() {
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

    public String getMonthName() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        int month = c.get(Calendar.MONTH);
        return String.valueOf( month );
    }

    public int getMonthIndex() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        return c.get(Calendar.MONTH);
    }

    public Point getCoord() {
        return coord;
    }

    public void setCoord(Point coord) {
        this.coord = coord;
    }
}

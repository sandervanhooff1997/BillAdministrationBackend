package domain.services;

import domain.models.CountryRegion;
import domain.models.Movement;
import domain.models.Road;
import domain.models.Vehicle;

import javax.ejb.Local;
import javax.ejb.Stateless;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Local
@Stateless
public class CountryService {

    private List<CountryRegion> countryRegions;
    private List<Road> roads;
    private Date startRushHour;
    private Double rushHourRate;
    private Double nonRushHourRate;

    public CountryService() {
        this.countryRegions = new ArrayList<>();
        this.roads = new ArrayList<>();
        this.rushHourRate = 0.1;
        this.nonRushHourRate = 0.0;
        startRushHour = new Date();
    }

    public List<CountryRegion> addRegionToCountry(CountryRegion countryRegion) {
        if (countryRegion != null) {
            this.countryRegions.add(countryRegion);
            return this.countryRegions;
        }
        return null;
    }

    public List<Road> addRoadToCountry(Road road) {
        if (road != null)
            this.roads.add(road);
        return this.roads;
    }

    public Double getTotalPricePerKilometer(Vehicle vehicle) {
        Double totalPrice = 0.0;

        for (int i = 0; i < this.roads.size(); i++) {
            if (this.roads.get(i).getName() ==
                vehicle.getCarTracker().getMovements().get(vehicle.getCarTracker().getMovements().size() -1)) {
                totalPrice =  this.roads.get(i).getPricePerKilometer();
            } else {
                for(CountryRegion countryRegion : this.countryRegions) {
                    
                }
            }
        }

        totalPrice = vehicle.getRateCategory().getPrice() + totalPrice;

        return totalPrice;
    }

    public Double getRushHourRate(Movement movement) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm") ;
        dateFormat.format(movement.getDate());

        try {
            if (dateFormat.parse(dateFormat.format(movement.getDate())).after(dateFormat.parse("17:00"))){
                return this.rushHourRate;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return this.nonRushHourRate;
    }
}

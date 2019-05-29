package domain.services;

import domain.models.Movement;
import domain.models.CountryRegion;
import domain.models.Road;
import domain.models.Vehicle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CountryService {

    private List<CountryRegion> countryRegions;
    private List<Road> roads;
    private Double rushHourRate;
    private Double nonRushHourRate;

    public CountryService() {
        this.countryRegions = new ArrayList<>();
        this.roads = new ArrayList<>();
        this.rushHourRate = 0.1;
        this.nonRushHourRate = 0.0;
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
//                totalPrice =  this.roads.get(i).getPricePerKilometer().getPrice();
            }
        }

        List<Movement> movements = vehicle.getCarTracker().getMovements();

        for (Movement m : movements) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm") ;

            try {
                if (dateFormat.parse(dateFormat.format(m.getDate())).after(dateFormat.parse("17:00")) == true && dateFormat.parse(dateFormat.format(m.getDate())).before(dateFormat.parse("19:00")) == true) {
//                    totalPrice = totalPrice +
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

//        for(CountryRegion countryRegion : this.countryRegions) {
//            Point point = (Point) vehicle.getCarTracker().getMovements().get(vehicle.getCarTracker().getMovements().size() -1);
//            if (countryRegion.getPolygon().contains(point)) {
//                totalPrice = totalPrice + countryRegion.getPrice();
//            }
//        }

        totalPrice = vehicle.getRateCategory().getPrice() + totalPrice;
        return totalPrice;
    }

    public Double getRushHourRate(Movement movement) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm") ;
        dateFormat.format(movement.getDate());

        try {
            if (dateFormat.parse(dateFormat.format(movement.getDate())).after(dateFormat.parse("17:00")) == true && dateFormat.parse(dateFormat.format(movement.getDate())).before(dateFormat.parse("19:00")) == true){
                return this.rushHourRate;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return this.nonRushHourRate;
    }

    public List<Road> getRoads() {
        return roads;
    }

    public void setRoads(List<Road> roads) {
        this.roads = roads;
    }
}

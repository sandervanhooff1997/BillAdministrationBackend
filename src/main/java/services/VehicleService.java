package services;

import models.*;
import repositories.*;

import javax.ejb.*;
import java.util.*;

@Local
@Stateless
public class VehicleService {
    @EJB
    private VehicleRepository repository;

    public List<Vehicle> getAll() {
        return repository.getAll();
    }

    public Vehicle getById(Long id) {
        return repository.getById(id);
    }

    public boolean create(Vehicle vehicle) {
        if (vehicle == null)
            return false;

        repository.create(vehicle);
        return true;
    }

    public boolean update(Vehicle vehicle) {
        if (vehicle == null)
            return false;

        repository.update(vehicle);
        return true;
    }

    public boolean delete(Long id) {
        Vehicle vehicle = repository.getById(id);

        if (vehicle == null)
            return false;

        repository.delete(vehicle);
        return true;
    }
}

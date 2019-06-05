package domain.services;

import domain.models.OwnerCredentials;
import domain.models.Vehicle;
import domain.repositories.OwnerCredentialRepository;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class OwnerCredentialService {
    @EJB
    private OwnerCredentialRepository repository;

    @EJB
    private VehicleService vehicleService;

    public List<OwnerCredentials> getAll() {
        return repository.getAll();
    }

    public List<OwnerCredentials> getAllUnused() {
        List<OwnerCredentials> ownerCredentials = getAll();
        List<Vehicle> vehicles = vehicleService.getAll();

        // remove used cartrackers
        for (Vehicle v : vehicles) {
            ownerCredentials.remove(v.getOwnerCredentials());
        }

        return ownerCredentials;
    }

    public List<OwnerCredentials> getAllUnusedByVehicleId(Long vehicleId) {
        List<OwnerCredentials> ownerCredentials = getAll();
        Vehicle v = vehicleService.getById(vehicleId);

        if (v == null)
            return null;

        for (OwnerCredentials oc : ownerCredentials) {
            if (oc.getId().equals(v.getOwnerCredentials().getId()))
                ownerCredentials.remove(oc);
        }

        return ownerCredentials;
    }

    public OwnerCredentials getByBsnAndPostalCode(Long bsn, String postalCode) {
        if (bsn == null || postalCode == null || postalCode.isEmpty())
            return null;

        return repository.getByBsnAndPostalCode(bsn, postalCode);
    }

    public OwnerCredentials getByBsn(Long bsn) {
        if (bsn == null)
            return null;

        return repository.getByBsn(bsn);
    }

    public OwnerCredentials getById(Long id) {
        return repository.getById(id);
    }

    public boolean create(OwnerCredentials ownerCredentials) {
        if (ownerCredentials == null)
            return false;

        repository.create(ownerCredentials);
        return true;
    }

    public boolean update(OwnerCredentials ownerCredentials) {
        if (ownerCredentials == null)
            return false;

        repository.update(ownerCredentials);
        return true;
    }

    public boolean delete(Long id) {
        OwnerCredentials ownerCredentials = repository.getById(id);

        if (ownerCredentials == null)
            return false;

        repository.delete(ownerCredentials);
        return true;
    }
}

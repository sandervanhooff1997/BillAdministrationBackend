package domain.services;

import domain.models.OwnerCredentials;
import domain.models.OwnershipHistory;
import domain.models.Vehicle;
import domain.repositories.OwnershipHistoryRepository;
import org.hibernate.HibernateException;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.Date;
import java.util.List;

@Local
@Stateless
public class OwnershipHistoryService {
    @EJB
    private OwnershipHistoryRepository repository;

    @EJB
    private VehicleService vehicleService;

    @EJB
    private OwnerCredentialService ownerCredentialService;

    public List<OwnershipHistory> getAll() {
        return repository.getAll();
    }

    public List<OwnershipHistory> getAllByLicensePlate(String licensePlate) {
        return repository.getAllByLicensePlate(licensePlate);
    }

    public OwnershipHistory getCurrentOwnerHistory(String licensePlate) {
        return repository.getCurrentOwnerHistory(licensePlate);
    }

    public OwnershipHistory create(Long vehicleId, Long ownerCredentialsId) {
        if (vehicleId == null || ownerCredentialsId == null)
            return null;

        try {
            Vehicle v = vehicleService.getById(vehicleId);
            if (v == null) return null;

            OwnerCredentials oc = ownerCredentialService.getById(ownerCredentialsId);
            if (oc == null) return null;

            // if a previous owner was set, make and end to it.
            OwnershipHistory oh = getCurrentOwnerHistory(v.getLicencePlate());
            if (oh != null)
                oh.setEnd(new Date());

            OwnershipHistory newOh = new OwnershipHistory();
            newOh.setBegin(new Date());
            newOh.setVehicle(v);
            newOh.setOwnerCredentials(oc);

            return repository.create(newOh);
        } catch (HibernateException e) {
            return null;
        }
    }

    public OwnershipHistory getById(Long id) {
        return repository.getById(id);
    }

    public OwnershipHistory getOnDate(String licensePlate, Date d) {
        if (licensePlate == null || d == null) return null;

        List<OwnershipHistory> list = getAllByLicensePlate(licensePlate);

        for (OwnershipHistory oh : list) {
            // if begin is set and d after begin
            if (oh.getBegin() != null && oh.getBegin().before(d)) {
                // if no end or between dates
                if (oh.getEnd() == null || oh.getEnd().after(d)) return oh;
            }
        }

        return null;
    }

    public boolean update(OwnershipHistory ownershipHistory) {
        if (ownershipHistory == null)
            return false;

        repository.update(ownershipHistory);
        return true;
    }

    public boolean delete(Long id) {
        OwnershipHistory ownershipHistory = repository.getById(id);

        if (ownershipHistory == null)
            return false;

        repository.delete(ownershipHistory);
        return true;
    }
}

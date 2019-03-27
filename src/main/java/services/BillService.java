package services;

import models.Bill;
import repositories.BillRepository;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;

@Local
@Stateless
public class BillService {

    @EJB
    private BillRepository repository;

    public List<Bill> getAll() {
        return repository.getAll();
    }

    public Bill getById(Long id) {
        return repository.getById(id);
    }

    public boolean create(Bill bill) {
        if (bill == null)
            return false;

        repository.create(bill);
        return true;
    }

    public boolean update(Bill bill) {
        if (bill == null)
            return false;

        repository.update(bill);
        return true;
    }

    public boolean delete(Long id) {
        Bill bill = repository.getById(id);

        if (bill == null)
            return false;

        repository.delete(bill);
        return true;
    }
}

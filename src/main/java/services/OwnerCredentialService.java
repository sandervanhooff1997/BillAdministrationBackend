package services;


import models.*;
import repositories.*;

import javax.ejb.*;
import java.util.*;

@Local
@Stateless
public class OwnerCredentialService {
    @EJB
    private OwnerCredentialRepository repository;

    public List<OwnerCredentials> getAll() {
        return repository.getAll();
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

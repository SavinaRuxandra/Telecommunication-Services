package intrusii.core.repository;

import intrusii.core.model.Client;

import java.util.List;

@org.springframework.stereotype.Repository
public interface ClientRepository extends Repository<Client, Long> {

    List<Client> findByNameContaining(String name);
    List<Client> findByCnp(String cnp);
}

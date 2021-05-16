package intrusii.core.service;

import intrusii.core.model.Client;
import intrusii.core.model.Contract;

import java.util.List;

public interface ClientService {

    Client addClient(Client client);

    void deleteClient(Long id);

    Client updateClient(Client client);

    List<Client> getAllClients();

    Client getClientById(Long id);

    List<Contract> getContractsOfClient(Long client);

    List<Client> getClientsWithContracts();

    List<Client> filterClientsByName(String name);

    List<Client> filterClientsByCnp(String cnp);
}

package intrusii.core.service;

import intrusii.core.model.Client;

import java.util.List;

public interface ClientService {

    Client addClient(Client client);

    void deleteClient(Long id);

    Client updateClient(Client client);

    List<Client> getAllClients();

    Client getClientByID(Long id);

    List<Client> filterClientsByName(String name);

    List<Client> filterClientsByCnp(String cnp);
}

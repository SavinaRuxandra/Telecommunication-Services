package intrusii.core.service;

import intrusii.core.model.Client;
import intrusii.core.model.Contract;
import intrusii.core.model.validators.ClientValidator;
import intrusii.core.model.validators.ValidatorException;
import intrusii.core.repository.ClientRepository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ClientServiceImpl implements ClientService {

    public static final Logger log = LoggerFactory.getLogger(ClientServiceImpl.class);

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientValidator validator;

    @Override
    public Client addClient(Client client) throws ValidatorException {
        log.trace("addClient - method entered: client = {}", client);

        validator.validate(client);
        Client result = clientRepository.save(client);

        log.trace("addClient - method finished");
        return result;
    }

    @Override
    public void deleteClient(Long id) throws ValidatorException, ServiceException {
        log.trace("deleteClient - method entered: id={}", id);

        if (id == null) {
            throw new ValidatorException("Id must not be null");
        }

        clientRepository.findById(id).orElseThrow(() -> new ServiceException("Entity does not exist"));

        clientRepository.deleteById(id);

        log.trace("deleteClient - method finished");
    }

    @Override
    @Transactional
    public Client updateClient(Client client) throws ValidatorException, ServiceException {
        log.trace("updateClient - method entered: client={}", client);

        validator.validate(client);
        clientRepository.findById(client.getId())
                .ifPresentOrElse(newClient -> {
                    newClient.setIDCard(client.getIDCard());
                    newClient.setName(client.getName());
                    newClient.setEmail(client.getEmail());
                    log.debug("updateClient - updated: c={}", newClient);
                }, () -> {throw new ServiceException("There is no client with this id");});

        log.trace("updateClient - method finished");
        return client;
    }

    @Override
    public List<Client> getAllClients() {
        log.trace("getAllClients - method entered");
        Iterable<Client> clients = clientRepository.findAll();

        log.trace("getAllClients - method finished: clients={}", clients);
        return StreamSupport.stream(clients.spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Client getClientById(Long id) throws ServiceException {
        log.trace("getClientByID - method entered: id={}", id);

        if(id == null){
            throw new ServiceException ("Id must not be null");
        }

        Client client = clientRepository.findById(id).orElseThrow(() -> new ServiceException("There is no client with this id"));

        log.trace("getClientByID - method finished: client={}", client);
        return client;
    }

    @Override
    public List<Contract> getContractsOfClient(Long id) {
        log.trace("getContractsOfClient - method entered: id={}", id);

        Client client = clientRepository.findById(id).orElseThrow(() -> new ServiceException("There is no client with this id"));
        List<Contract> contracts = client.getContracts();

        log.trace("getContractsOfClient - method finished: contracts={}", contracts);
        return contracts;
    }

    @Override
    public List<Client> getClientsWithContracts() {
        log.trace("getClientsWithContracts - method entered");

        List<Client> clients = clientRepository.findAllWithContracts();

        log.trace("getClientsWithContracts - method finished: clients={}", clients);
        return clients;
    }

    @Override
    public List<Client> filterClientsByName(String name) {
        log.trace("filterClientsByName - method entered: name={}", name);

        List<Client> clients = clientRepository.findByNameContaining(name);

        log.trace("filterClientsByName - method finished: clients={}", clients);
        return clients;
    }

    @Override
    public List<Client> filterClientsByCnp(String cnp) {
        log.trace("filterClientsByCnp - method entered: cnp={}", cnp);

        List<Client> clients = clientRepository.findByIdCard_Cnp(cnp);

        log.trace("filterClientsByCnp - method finished: clients={}", clients);
        return clients;
    }
}

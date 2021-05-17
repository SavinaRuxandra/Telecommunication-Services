package intrusii.web.controller;

import intrusii.core.model.Client;
import intrusii.core.model.Contract;
import intrusii.core.model.SubscriptionType;
import intrusii.core.service.ClientService;
import intrusii.web.converter.ClientConverter;
import intrusii.web.converter.ContractConverter;
import intrusii.web.dto.ClientDto;
import intrusii.web.dto.ContractDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/clients")
@RestController
public class ClientController {
    public static final Logger log = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientConverter clientConverter;

    @Autowired
    private ContractConverter contractConverter;

    @RequestMapping(value = "", method = RequestMethod.POST)
    ClientDto addClient(@RequestBody ClientDto clientDto){
        try
        {
            log.trace("addClient - method entered: client={}", clientDto);

            var client = clientConverter.convertDtoToModel(clientDto);
            var result = clientService.addClient(client);
            var resultModel = clientConverter.convertModelToDto(result);

            log.trace("addClient - method finished : client={}", result);
            return resultModel;
        } catch (Exception ex){
            throw new RuntimeException("The client has not been added");
        }
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteClient(@PathVariable Long id) {
        log.trace("deleteClient - method entered: id={}", id);

        clientService.deleteClient(id);

        log.trace("deleteClient - method finished");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    ClientDto updateClient(@PathVariable Long id, @RequestBody ClientDto dto) {
        log.trace("updateClient - method entered: client={}", dto);

        ClientDto clientDto = clientConverter.convertModelToDto(
                clientService.updateClient(
                        clientConverter.convertDtoToModel(dto)));

        log.trace("updateClient - method finished: client={}", clientDto);
        return clientDto;
    }

    @RequestMapping(value = "")
    List<ClientDto> getAllClients() {
        log.trace("getAllClients - method entered");

        List<ClientDto> clientsDto = clientConverter.convertModelsToDtos(
                clientService.getAllClients());

        log.trace("getAllClients - method finished: clients={}", clientsDto);
        return clientsDto;
    }

    @RequestMapping(value = "/byId", method = RequestMethod.POST)
    ClientDto getClientById(@RequestBody Long id) {
        log.trace("getClientById - method entered: id={}", id);

        ClientDto clientDto = clientConverter.convertModelToDto(
                clientService.getClientById(id));

        log.trace("getClientById - method finished: client={}", clientDto);
        return clientDto;
    }

    @RequestMapping(value = "/contracts/{id}")
    List<ContractDto> getContractsOfClient(@PathVariable Long id) {
        log.trace("getContractsOfClient - method entered: id={}", id);

        List<ContractDto> contractsDto = contractConverter.convertModelsToDtos(
                clientService.getContractsOfClient(id));

        log.trace("getContractsOfClient - method finished: contracts={}", contractsDto);
        return contractsDto;
    }

    @RequestMapping(value = "/clientsWithContracts")
    List<ClientDto> getClientsWithContracts() {
        log.trace("getClientsWithContracts - method entered");

        List<ClientDto> clientsDto = clientConverter.convertModelsToDtos(
                clientService.getClientsWithContracts());

        log.trace("getClientsWithContracts - method finished: clients={}", clientsDto);
        return clientsDto;
    }

    @RequestMapping(value = "/filterBySubscriptionType/{subscriptionType}")
    List<ClientDto> getClientsBySubscriptionType(@PathVariable String subscriptionType) {
        log.trace("getClientsBySubscriptionType - method entered");

        List<ClientDto> clientsDto = new ArrayList<>();

        if(subscriptionType.equals("Internet") || subscriptionType.equals("TV") || subscriptionType.equals("Phone")) {
            clientsDto = clientConverter.convertModelsToDtos(
                    clientService.filterClientsBySubscriptionType(SubscriptionType.valueOf(subscriptionType)));
        }

        log.trace("getClientsBySubscriptionType - method finished: clients={}", clientsDto);
        return clientsDto;
    }

    @RequestMapping(value = "/filterByName/{name}")
    List<ClientDto> filterClientsByName(@PathVariable String name) {
        log.trace("filterClientByDuration - method entered");

        List<ClientDto> clientsDto = clientConverter.convertModelsToDtos(
                clientService.filterClientsByName(name));

        log.trace("filterClientByDuration - method finished: clients={}", clientsDto);
        return clientsDto;
    }

    @RequestMapping(value = "/filterByCnp/{cnp}")
    List<ClientDto> filterClientsByCnp(@PathVariable String cnp) {
        log.trace("filterClientByType - method entered");

        List<ClientDto> clientsDto = clientConverter.convertModelsToDtos(
                clientService.filterClientsByCnp(cnp));

        log.trace("filterClientByType - method finished: clients={}", clientsDto);
        return clientsDto;
    }
}

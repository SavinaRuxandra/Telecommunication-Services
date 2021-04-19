package intrusii.web.controller;

import intrusii.core.service.ClientService;
import intrusii.web.converter.ClientConverter;
import intrusii.web.dto.ClientDto;
import intrusii.web.dto.ClientsDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/clients")
@RestController
public class ClientController {
    public static final Logger log = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientConverter clientConverter;

    @RequestMapping(value = "", method = RequestMethod.POST)
    ClientDto addClient(@RequestBody ClientDto clientDto){
        log.trace("addClient - method entered: client={}", clientDto);

        var client = clientConverter.convertDtoToModel(clientDto);
        var result = clientService.addClient(client);
        var resultModel = clientConverter.convertModelToDto(result);

        log.trace("updateClient - method finished : subscription={}", result);
        return resultModel;
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
        log.trace("updateClient - method entered: subscription={}", dto);

        ClientDto clientDto = clientConverter.convertModelToDto(
                clientService.updateClient(
                        clientConverter.convertDtoToModel(dto)));

        log.trace("updateClient - method finished: subscription={}", clientDto);
        return clientDto;
    }

    @RequestMapping(value = "")
    ClientsDto getAllClients() {
        log.trace("getAllClients - method entered");

        ClientsDto clients = new ClientsDto(
                clientConverter.convertModelsToDtos(
                        clientService.getAllClients()));

        log.trace("getAllClients - method finished: clients={}", clients);
        return clients;
    }

    @RequestMapping(value = "/byId", method = RequestMethod.POST)
    ClientDto getClientById(@RequestBody Long id) {
        log.trace("getClientById - method entered: id={}", id);

        ClientDto clientDto = clientConverter.convertModelToDto(
                clientService.getClientByID(id));

        log.trace("getClientById - method finished: client={}", clientDto);
        return clientDto;
    }

    @RequestMapping(value = "/filterByName", method = RequestMethod.POST)
    ClientsDto filterClientsByName(@RequestBody String name) {
        log.trace("filterClientByDuration - method entered");

        ClientsDto clientsDto = new ClientsDto(
                clientConverter.convertModelsToDtos(
                        clientService.filterClientsByName(name)));

        log.trace("filterClientByDuration - method finished: clients={}", clientsDto);
        return clientsDto;
    }

    @RequestMapping(value = "/filterByCnp", method = RequestMethod.POST)
    ClientsDto filterClientsByCnp(@RequestBody String cnp) {
        log.trace("filterClientByType - method entered");

        ClientsDto clientsDto = new ClientsDto(
                clientConverter.convertModelsToDtos(
                        clientService.filterClientsByCnp(cnp)));

        log.trace("filterClientByType - method finished: clients={}", clientsDto);
        return clientsDto;
    }
}

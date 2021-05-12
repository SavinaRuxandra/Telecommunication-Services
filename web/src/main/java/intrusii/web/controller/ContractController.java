package intrusii.web.controller;

import intrusii.core.model.Contract;
import intrusii.core.service.ContractService;
import intrusii.web.converter.ContractConverter;
import intrusii.web.dto.ContractDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/contracts")
@RestController
public class ContractController {
    public static final Logger log = LoggerFactory.getLogger(ContractController.class);

    @Autowired
    private ContractService contractService;

    @Autowired
    private ContractConverter contractConverter;

    @RequestMapping(value = "", method = RequestMethod.POST)
    ContractDto addContract(@RequestBody ContractDto contractDto){
        log.trace("addSubscription - method entered: contract={}", contractDto);

        var contract = contractConverter.convertDtoToModel(contractDto);
        var result = contractService.addContract(contract);
        var resultModel = contractConverter.convertModelToDto(result);

        log.trace("updateContract - method finished : contract={}", result);
        return resultModel;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteContract(@PathVariable Long id) {
        log.trace("deleteContract - method entered: id={}", id);

        contractService.deleteContract(id);

        log.trace("deleteContract - method finished");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    ContractDto updateContract(@PathVariable Long id, @RequestBody ContractDto dto) {
        log.trace("updateContract - method entered: contract={}", dto);

        ContractDto contractDto = contractConverter.convertModelToDto(
                contractService.updateContract(
                        contractConverter.convertDtoToModel(dto)));

        log.trace("updateContract - method finished: contract={}", contractDto);
        return contractDto;
    }

    @RequestMapping(value = "")
    List<ContractDto> getAllContracts() {
        log.trace("getAllContracts - method entered");

        List<ContractDto> contractsDto = contractConverter.convertModelsToDtos(
                contractService.getAllContracts());

        log.trace("getAllContracts - method finished: contracts={}", contractsDto);
        return contractsDto;
    }

    @RequestMapping(value = "/byId", method = RequestMethod.POST)
    ContractDto getContractById(@RequestBody Long id) {
        log.trace("getContractById - method entered: id={}", id);

        ContractDto contractDto = contractConverter.convertModelToDto(
                contractService.getContractById(id));

        log.trace("getContractById - method finished: contract={}", contractDto);
        return contractDto;
    }

    @RequestMapping(value = "/filterActive")
    List<ContractDto> filterActiveContracts() {
        log.trace("filterActiveContracts - method entered");

        List<ContractDto> contractsDto = contractConverter.convertModelsToDtos(
                contractService.filterActiveContracts());

        log.trace("filterActiveContracts - method finished: contracts={}", contractsDto);
        return contractsDto;
    }

    @RequestMapping(value = "/statistics")
    List<Pair<String, Integer>> getStatistics() {
        log.trace("getStatistics - method entered");

        List<Pair<String, Integer>> pairs = contractService.getStatistics();

        log.trace("getStatistics - method finished: pairs={}", pairs);
        return pairs;
    }
}

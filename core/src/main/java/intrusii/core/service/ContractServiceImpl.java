package intrusii.core.service;

import intrusii.core.model.Contract;
import intrusii.core.repository.ClientRepository.ClientRepository;
import intrusii.core.repository.ContractRepository.ContractRepository;
import intrusii.core.repository.SubscriptionRepository.SubscriptionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ContractServiceImpl implements ContractService {

    public static final Logger log = LoggerFactory.getLogger(ContractServiceImpl.class);

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Override
    public Contract addContract(Contract contract) throws ServiceException {
        log.trace("addContract - method entered: contract={}", contract);

        Contract result;
        if (clientRepository.findById(contract.getClient().getId()).isPresent() && subscriptionRepository.findById(contract.getSubscription().getId()).isPresent())
            result = contractRepository.save(contract);
        else
            throw new ServiceException("The ids are not valid");
        log.trace("addContract - method finished");
        return result;
    }

    @Override
    public void deleteContract(Long id) throws ServiceException {
        log.trace("deleteContract - method entered: id={}", id);
        if (id == null) {
            throw new ServiceException("Id must not be null");
        }
        contractRepository.findById(id).orElseThrow(() -> new ServiceException("Entity does not exist"));
        contractRepository.deleteById(id);
        log.trace("deleteContract - method finished");
    }

    @Override
    @Transactional
    public Contract updateContract(Contract contract) throws ServiceException {
        log.trace("updateContract - method entered: contract={}", contract);

        Contract oldContract = contractRepository.findById(contract.getId()).orElseThrow(() -> new ServiceException("There is no contract with this id"));
        clientRepository.findById(contract.getClient().getId()).orElseThrow(() -> new ServiceException("There is no client with this id"));

        oldContract.setClient(contract.getClient());
        oldContract.setAddress(contract.getAddress());
        oldContract.setDate(contract.getDate());

        log.debug("updateContract - updated: c={}", oldContract);
        log.trace("updateContract - method finished");
        return oldContract;
    }

    @Override
    public List<Contract> getAllContracts() {
        log.trace("getAllContracts - method entered");
        Iterable<Contract> contracts = contractRepository.findAll();
        log.trace("getAllContracts - method finished: contracts={}", contracts);
        return StreamSupport.stream(contracts.spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Contract getContractById(Long id) {
        log.trace("getContractByID - method entered: id={}", id);

        if(id == null){
            throw new ServiceException ("Id must not be null");
        }

        Contract contract = contractRepository.findById(id).orElseThrow(() -> new ServiceException("There is no contract with this id"));
        log.trace("getContractByID - method finished: contract={}", contract);

        return contract;
    }

    @Override
    public List<Contract> filterActiveContracts() {
        log.trace("filterActiveContracts - method entered");
        List<Contract> contracts = filterGeneric(x -> ChronoUnit.MONTHS.between(x.getDate(), LocalDate.now()) <= subscriptionRepository.findById(x.getSubscription().getId()).get().getDuration());
        log.trace("filterActiveContracts - method finished: contracts={}", contracts);
        return contracts;
    }

    private List<Contract> filterGeneric(Predicate<Contract> function){
        log.trace("filterGeneric - method entered: function={}", function);
        Iterable<Contract> contractsIterable;
        contractsIterable = contractRepository.findAll();
        List<Contract> contracts = StreamSupport.stream(contractsIterable.spliterator(), false).filter(function).collect(Collectors.toList());
        log.trace("filterGeneric - method finished: contracts={}", contracts);
        return contracts;
    }

    @Override
    public List<Pair<String, Integer>> getStatistics() {
        List<Pair<String, Integer>> pairs = new ArrayList<>();
        List<Contract> contracts = contractRepository.findAll();

        List<String> contractsDate = contracts.stream()
                .map(s -> s.getDate().getMonth().toString())
                .collect(Collectors.toList());

        Map<String, Long> counted = contractsDate.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        counted.forEach((key, value) -> pairs.add(Pair.of(key, Math.toIntExact(value))));

        pairs.sort((a, b) -> {
            SimpleDateFormat sdf = new SimpleDateFormat("MMMMM");
            try {
                return sdf.parse(a.getFirst()).compareTo(sdf.parse(b.getFirst()));
            } catch (ParseException e) {
                return a.getFirst().compareTo(b.getFirst());
            }
        });

        return pairs;
    }
}

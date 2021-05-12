package intrusii.core.service;

import intrusii.core.model.Contract;
import org.springframework.data.util.Pair;

import java.util.List;

public interface ContractService {

    Contract addContract(Contract contract);

    void deleteContract(Long id);

    Contract updateContract(Contract contract);

    List<Contract> getAllContracts();

    Contract getContractById(Long id);

    List<Contract> filterActiveContracts();

    List<Pair<String, Integer>> getStatistics();
}

package intrusii.core.service;

import intrusii.core.model.Contract;

import java.util.List;

public interface ContractService {

    Contract addContract(Contract contract);

    void deleteContract(Long id);

    Contract updateContract(Contract contract);

    List<Contract> getAllContracts();

    Contract getContractById(Long id);

    List<Contract> filterActiveContracts();
}

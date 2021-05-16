package intrusii.core.repository.ContractRepository;

import intrusii.core.model.Contract;
import intrusii.core.repository.Repository;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.List;

@org.springframework.stereotype.Repository
public interface ContractRepository extends Repository<Contract, Long> {

    @Override
    @EntityGraph(value = "contracts", type = EntityGraph.EntityGraphType.LOAD)
    List<Contract> findAll();
}

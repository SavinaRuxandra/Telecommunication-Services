package intrusii.core.repository.ClientRepository;

import intrusii.core.model.Client;
import intrusii.core.model.SubscriptionType;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.List;

public interface ClientRepositoryCustom {

    @EntityGraph(value = "clientsWithContracts", type = EntityGraph.EntityGraphType.LOAD)
    List<Client> findAllWithContracts();

    List<Client> findAllWithSubscriptionType(SubscriptionType type);
}

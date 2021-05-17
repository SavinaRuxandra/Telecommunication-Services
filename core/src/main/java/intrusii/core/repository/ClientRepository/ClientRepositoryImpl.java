package intrusii.core.repository.ClientRepository;

import intrusii.core.model.Client;
import intrusii.core.model.SubscriptionType;
import intrusii.core.repository.CustomRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class ClientRepositoryImpl extends CustomRepositorySupport implements ClientRepositoryCustom {

    @Autowired
    @Qualifier("JPQL")
    private ClientRepositoryCustom clientRepositoryCustom;

    @Override
    public List<Client> findAllWithContracts() {
        return clientRepositoryCustom.findAllWithContracts();
    }

    @Override
    public List<Client> findAllWithSubscriptionType(SubscriptionType type) {
        return clientRepositoryCustom.findAllWithSubscriptionType(type);
    }
}

package intrusii.core.repository.ClientRepository;

import intrusii.core.model.Client;
import intrusii.core.model.Client_;
import intrusii.core.model.Contract;
import intrusii.core.repository.CustomRepositorySupport;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.List;

@Repository("CriteriaAPI")
public class ClientRepositoryCriteriaAPIImpl extends CustomRepositorySupport implements ClientRepositoryCustom {
    @Override
    public List<Client> findAllWithContracts() {
        System.out.println("---CriteriaAPI---");
        EntityManager entityManager = getEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Client> query = criteriaBuilder.createQuery(Client.class);
        query.distinct(Boolean.TRUE);
        Root<Client> root = query.from(Client.class);
        Fetch<Client, Contract> clientContractFetch = root.fetch(Client_.contracts, JoinType.LEFT);

        List<Client> clients = entityManager.createQuery(query).getResultList();

        return clients;
    }
}

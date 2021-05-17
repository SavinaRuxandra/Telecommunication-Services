package intrusii.core.repository.ClientRepository;

import intrusii.core.model.*;
import intrusii.core.repository.CustomRepositorySupport;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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

    @Override
    public List<Client> findAllWithSubscriptionType(SubscriptionType type) {
        System.out.println("---CriteriaAPI---");
        EntityManager entityManager = getEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Client> query = criteriaBuilder.createQuery(Client.class);
        query.distinct(Boolean.TRUE);
        Root<Client> root = query.from(Client.class);
        ParameterExpression<SubscriptionType> param = criteriaBuilder.parameter(SubscriptionType.class);
        Fetch<Client, Contract> clientContractFetch = root.fetch(Client_.contracts, JoinType.LEFT);
        clientContractFetch.fetch(Contract_.subscription, JoinType.LEFT);

        TypedQuery<Client> result = entityManager.createQuery(query);
        result.setParameter(param, type);
        List<Client> clients = entityManager.createQuery(query).getResultList();

        return clients;
    }
}

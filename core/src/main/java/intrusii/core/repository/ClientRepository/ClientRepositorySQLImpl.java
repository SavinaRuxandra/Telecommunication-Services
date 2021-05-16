package intrusii.core.repository.ClientRepository;

import intrusii.core.model.Client;
import intrusii.core.repository.CustomRepositorySupport;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.jpa.HibernateEntityManager;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository("SQL")
public class ClientRepositorySQLImpl extends CustomRepositorySupport implements ClientRepositoryCustom {

    @Override
    @Transactional
    public List<Client> findAllWithContracts() {
        System.out.println("---SQL---");
        HibernateEntityManager hibernateEntityManager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = hibernateEntityManager.getSession();

        org.hibernate.Query query = session.createSQLQuery("select distinct {cl.*},{co.*} " +
                "from client cl " +
                "left join contract co on cl.id=co.client ")
                .addEntity("cl",Client.class)
                .addJoin("co", "cl.contracts")
                .addEntity("cl",Client.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Client> clients = query.getResultList();

        return clients;
    }
}

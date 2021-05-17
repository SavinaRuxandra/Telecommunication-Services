package intrusii.core.repository.ClientRepository;

import intrusii.core.model.Client;
import intrusii.core.repository.Repository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@org.springframework.stereotype.Repository
public interface ClientRepository extends Repository<Client, Long>, ClientRepositoryCustom {

    List<Client> findByNameContaining(String name);

    List<Client> findByIdCard_Cnp(String cnp);

//    @Query("select distinct c from Client where")
//    @EntityGraph(value = "clientsWithContracts",  type = EntityGraph.EntityGraphType.LOAD)
//    List<Client> findByContractId(Long id);

}

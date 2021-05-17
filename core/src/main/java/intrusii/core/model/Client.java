package intrusii.core.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NamedEntityGraphs({
        @NamedEntityGraph(name = "clientsWithContracts",
                attributeNodes = @NamedAttributeNode(value = "contracts")),

//        @NamedEntityGraph(name = "clientsWithContractsAndSubscription",
//                attributeNodes = {
//                    @NamedAttributeNode(value = "contracts"),
//                    @NamedAttributeNode(value = "subscription", subgraph = "contractsWithSubscription")
//                },
//                subgraphs = {
//                    @NamedSubgraph(name = "contractsWithSubscription", attributeNodes = {
//                        @NamedAttributeNode(value = "subscription")
//                    })
//                }
//        )
})
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
//@ToString(callSuper = true)
@Builder
public class Client extends BaseEntity<Long> {
    private IDCard idCard;
    private String name;
    private String email;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client", cascade = CascadeType.ALL)
    private List<Contract> contracts;

    public IDCard getIDCard() {
        return idCard;
    }

    public void setIDCard(IDCard idCard) {
        this.idCard = idCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }

}

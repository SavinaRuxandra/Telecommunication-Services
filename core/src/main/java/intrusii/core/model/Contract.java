package intrusii.core.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@NamedEntityGraphs(
        @NamedEntityGraph(name = "contracts",
        attributeNodes = {
                @NamedAttributeNode(value = "client"),
                @NamedAttributeNode(value = "subscription")
        })
)

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
//@EqualsAndHashCode(callSuper = true)
@EqualsAndHashCode(exclude = {"client", "subscription"}, callSuper = true)
//@ToString(callSuper = true)
@ToString(exclude = {"client", "subscription"})
@Builder
public class Contract extends BaseEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="client")
    private Client client;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="subscription")
    private Subscription subscription;
    private String address;
    private LocalDate date;

    public Contract(Client client){
        this.client = client;
        this.subscription = null;
        this.address = "";
        this.date = LocalDate.of(2000, 1, 1);
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address)
    {
        this.address=address;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}

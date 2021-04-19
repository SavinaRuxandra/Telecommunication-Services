package intrusii.core.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Contract extends BaseEntity<Long> {
    @ManyToOne
    @JoinColumn(name="clientId")
    private Client client;
    @ManyToOne
    @JoinColumn(name="subscriptionId")
    private Subscription subscription;
    private LocalDate date;

    public Contract(Client client){
        this.client = client;
        this.subscription = null;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}

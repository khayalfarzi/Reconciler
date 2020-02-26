package az.company.billing.entities;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Builder
public class IssueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "checked_date")
    @CreationTimestamp
    private Date checkedDate;

    @Column(name = "payment_status")
    private String paymentStatus;

    @Column(name = "issued_amount")
    private String issuedAmount;

    @Column(name = "issued_tr_count")
    private String issuedTrCount;

    @Override
    public String toString() {
        return "IssueEntity{" +
                "id=" + id +
                ", checkedDate=" + checkedDate +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", issuedAmount='" + issuedAmount + '\'' +
                ", issuedTrCount='" + issuedTrCount + '\'' +
                '}';
    }
}

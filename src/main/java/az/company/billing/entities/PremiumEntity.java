package az.company.billing.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PremiumEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "credit_num")
    private String creditNum;

    @Column(name = "fin")
    private String fin;

    @Column(name = "tr_id")
    private String transactionId;

    @Column(name = "rrn")
    private String RRN;

    @Column(name = "status")
    private String status;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "transaction_date")
    @CreationTimestamp
    private LocalDate transactionDate;

    @Column(name = "transaction_time")
    @CreationTimestamp
    private LocalTime transactionTime;
}

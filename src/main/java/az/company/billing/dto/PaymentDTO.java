package az.company.billing.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "Payments")
@XmlAccessorType(XmlAccessType.FIELD)
public class PaymentDTO {

    @XmlElement(name = "RRN")
    private String RRN;

    @XmlElement(name = "Amount")
    private Double amount;

    @XmlElement(name = "Currency")
    private String currency;

    @XmlElement(name = "Date")
    private LocalDate date;

    @XmlElement(name = "Time")
    private LocalTime time;

    @XmlElement(name = "Details")
    private String details;

    @Override
    public String toString() {
        return "PaymentDTO{" +
                "RRN='" + RRN + '\'' +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", details='" + details + '\'' +
                '}';
    }
}

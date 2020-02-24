package az.company.billing.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "mBilling")
public class PaymentDTO {

    private String RRN;

    private Double amount;

    private String currency;

    private LocalDate date;

    private LocalTime time;

    private String details;

    @XmlElement(name = "RRN")
    public String getRRN() {
        return RRN;
    }

    @XmlElement(name = "Amount")
    public Double getAmount() {
        return amount;
    }

    @XmlElement(name = "Currency")
    public String getCurrency() {
        return currency;
    }

    @XmlElement(name = "Date")
    public LocalDate getDate() {
        return date;
    }

    @XmlElement(name = "Time")
    public LocalTime getTime() {
        return time;
    }

    @XmlElement(name = "Details")
    public String getDetails() {
        return details;
    }

}

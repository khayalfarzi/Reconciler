package az.company.billing.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "mBilling")
public class SecondCheckResponse {

    private String trType;

    private List<PaymentDTO> payments;

    @XmlElement(name = "TrType")
    public String getTrType() {
        return trType;
    }

    @XmlElementWrapper(name = "Payments")
    @XmlElement(name = "Payment")
    public List<PaymentDTO> getPayments() {
        return payments;
    }
}

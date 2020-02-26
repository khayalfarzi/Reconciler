package az.company.billing.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "mBilling")
@XmlAccessorType(XmlAccessType.FIELD)
public class SecondCheckResponse {

    @XmlElement(name = "TrType")
    private String trType;

    @XmlElementWrapper(name = "Payments")
    @XmlElement(name = "Payment")
    private List<PaymentDTO> payments;

    @Override
    public String toString() {
        return "SecondCheckResponse{" +
                "trType='" + trType + '\'' +
                ", payments=" + payments +
                '}';
    }
}

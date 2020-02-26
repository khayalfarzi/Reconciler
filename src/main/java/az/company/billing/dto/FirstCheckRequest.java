package az.company.billing.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "mBilling")
@XmlAccessorType(XmlAccessType.FIELD)
public class FirstCheckRequest {

    @XmlElement(name = "TrType")
    private String trType;

    @XmlElement(name = "FirstRRN")
    private String firstRRN;

    @XmlElement(name = "LastRRN")
    private String lastRRN;

    @XmlElement(name = "TotalAmount")
    private Double totalAmount;

    @XmlElement(name = "TotalOperCount")
    private String totalOperCount;

    @Override
    public String toString() {
        return "FirstCheckRequest{" +
                "trType='" + trType + '\'' +
                ", firstRRN='" + firstRRN + '\'' +
                ", lastRRN='" + lastRRN + '\'' +
                ", totalAmount=" + totalAmount +
                ", totalOperCount='" + totalOperCount + '\'' +
                '}';
    }
}

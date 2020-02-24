package az.company.billing.dto;

import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@Setter
@NoArgsConstructor
@XmlRootElement(name = "mBilling")
public class FirstCheckRequest {

    private String TrType;

    private String firstRRN;

    private String lastRRN;

    private Double totalAmount;

    private String totalOperCount;

    @XmlElement(name = "TrType")
    public String getTrType() {
        return TrType;
    }

    @XmlElement(name = "FirstRRN")
    public String getFirstRRN() {
        return firstRRN;
    }

    @XmlElement(name = "LastRRN")
    public String getLastRRN() {
        return lastRRN;
    }

    @XmlElement(name = "TotalAmount")
    public Double getTotalAmount() {
        return totalAmount;
    }

    @XmlElement(name = "TotalOperCount")
    public String getTotalOperCount() {
        return totalOperCount;
    }

    @Override
    public String toString() {
        return "FirstCheckRequest{" +
                "TrType='" + TrType + '\'' +
                ", firstRRN='" + firstRRN + '\'' +
                ", lastRRN='" + lastRRN + '\'' +
                ", totalAmount=" + totalAmount +
                ", totalOperCount='" + totalOperCount + '\'' +
                '}';
    }
}

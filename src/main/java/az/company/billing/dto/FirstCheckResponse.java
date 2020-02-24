package az.company.billing.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "mBilling")
public class FirstCheckResponse {

    private String trType;

    private String status;

    @XmlElement(name = "TrType")
    public String getTrType() {
        return trType;
    }

    @XmlElement(name = "Status")
    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "FirstCheckResponseEntity{" +
                "trType='" + trType + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

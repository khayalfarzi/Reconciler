package az.company.billing.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "mBilling")
@XmlAccessorType(XmlAccessType.FIELD)
public class FirstCheckResponse {

    @XmlElement(name = "TrType")
    private String trType;

    @XmlElement(name = "Status")
    private String status;

    @Override
    public String toString() {
        return "FirstCheckResponseEntity{" +
                "trType='" + trType + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

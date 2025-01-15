package impulse.passerelle.domain.estates.elements;

import com.google.gson.annotations.SerializedName;
import jakarta.xml.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

/**
 * Représentation d'une structure XML pour l'identifiant d'un élément.
 * <p>
 * Cette classe inclut l'identifiant principal ainsi que des métadonnées associées telles que
 * les dates d'enregistrement et de mise à jour, et une clé de mandat.
 * </p>
 *
 * Exemple de structure XML correspondante :
 * <pre>
 * {@code
 * <id dateEnr="2023-01-01" dateMaj="2023-01-15" mandateKey="ABC123">1001</id>
 * }
 * </pre>
 */
@XmlRootElement(name = "id")
@XmlAccessorType(XmlAccessType.FIELD)
public class Id {
    @XmlValue
    private Integer id;
    @XmlAttribute(name = "dateEnr")
    @SerializedName("date_enr")
    private Date dateEnr;
    @XmlAttribute(name = "dateMaj")
    @SerializedName("date_maj")
    private Date dateMaj;
    @XmlAttribute(name = "mandateKey")
    @SerializedName("mandate_key")
    private String mandateKey;

    public Id() {}

    public Id(int id, Date dateEnr, Date dateMaj, String mandateKey) throws ParseException {
        this.id = id;
        this.dateEnr = dateEnr;
        this.dateMaj = dateMaj;
        this.mandateKey = mandateKey;
    }

    public Integer getId() {
        return id;
    }

    public Date getDateEnr() {
        return dateEnr;
    }

    public Date getDateMaj() {
        return dateMaj;
    }

    public String getMandateKey() {
        return mandateKey;
    }

    @Override
    public String toString() {
        return "Id{" +
                "id=" + id +
                ", dateEnr='" + dateEnr + '\'' +
                ", dateMaj=" + dateMaj +
                ", mandateKey='" + mandateKey + '\'' +
                '}';
    }
}

package impulse.passerelle.domain.estates.elements;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlValue;

/**
 * Représente un prix avec une valeur et une devise dans un nœud XML.
 * <p>
 * Exemple de structure XML :
 * <pre>
 * {@code
 * <prix currency="EUR">1000.0</prix>
 * }
 * </pre>
 * </p>
 */
@XmlRootElement(name = "prix")
public class Prix {
    // get value of xml element
    @XmlValue
    private Double valeur;
    @XmlAttribute(name = "currency")
    private String currency;

    public Prix() {}

    public Double getValeur() {
        return valeur;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return "Prix{" +
                "valeur=" + valeur +
                ", currency='" + currency + '\'' +
                '}';
    }
}

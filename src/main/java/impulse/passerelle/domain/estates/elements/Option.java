package impulse.passerelle.domain.estates.elements;

import jakarta.xml.bind.annotation.*;

/**
 * Représente une option dans un nœud XML avec un identifiant, un label, un prix, et un descriptif.
 * <p>
 * Exemple de structure XML :
 * <pre>
 * {@code
 * <opt id="1">
 *     <label>Nom de l'option</label>
 *     <prix currency="EUR">99.99</prix>
 *     <descriptif>Details de l'option</descriptif>
 * </opt>
 * }
 * </pre>
 * </p>
 */
@XmlRootElement(name = "opt")
@XmlAccessorType(XmlAccessType.FIELD)
public class Option {
    @XmlAttribute(name = "id")
    private Integer id;
    @XmlElement(name = "label")
    private String label;
    @XmlElement(name = "prix")
    private Prix prix;
    @XmlElement(name = "descriptif")
    private String descriptif;

    public Option() {}

    @Override
    public String toString() {
        return "Option{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", prix=" + prix +
                ", descriptif='" + descriptif + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public Prix getPrix() {
        return prix;
    }

    public String getDescriptif() {
        return descriptif;
    }
}

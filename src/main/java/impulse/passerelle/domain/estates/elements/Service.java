package impulse.passerelle.domain.estates.elements;

import jakarta.xml.bind.annotation.*;

/**
 * Représente un service dans un nœud XML.
 * <p>
 * Exemple de structure XML :
 * <pre>
 * {@code
 * <service id="1">Draps fournis</service>
 * }
 * </pre>
 * </p>
 */
@XmlRootElement(name = "service")
@XmlAccessorType(XmlAccessType.FIELD)
public class Service {
    @XmlAttribute(name = "id")
    private Integer id;
    @XmlValue
    private String description;

    public Service() {
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}

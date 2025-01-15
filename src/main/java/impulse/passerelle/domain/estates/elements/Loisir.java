package impulse.passerelle.domain.estates.elements;

import jakarta.xml.bind.annotation.*;

/**
 * Représente un loisir dans un nœud XML avec un identifiant et une description.
 * <p>
 * Exemple de structure XML :
 * <pre>
 * {@code
 * <loisir id="1">Description du loisir</loisir>
 * }
 * </pre>
 * </p>
 */
@XmlRootElement(name = "loisir")
@XmlAccessorType(XmlAccessType.FIELD)
public class Loisir {
    @XmlAttribute(name = "id")
    private Integer id;
    @XmlValue
    private String description;

    public Loisir() {}

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Loisir{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}

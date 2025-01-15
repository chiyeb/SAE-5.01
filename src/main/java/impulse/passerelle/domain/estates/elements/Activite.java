package impulse.passerelle.domain.estates.elements;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * Représentation d'un nœud XML pour les activités autorisées dans un commerce.
 * <p>
 * Cette classe est mappée sur un nœud XML nommé {@code <activite>}, avec deux attributs principaux :
 * <ul>
 *     <li>{@code id} : Identifiant unique de l'activité</li>
 *     <li>{@code label} : Libellé ou description de l'activité</li>
 * </ul>
 * Elle utilise les annotations JAXB pour permettre la sérialisation et la désérialisation XML.
 * </p>
 *
 * Exemple de structure XML correspondante :
 * <pre>
 * {@code
 * <activite>
 *     <id>123</id>
 *     <label>Restaurant</label>
 * </activite>
 * }
 * </pre>
 *
 */
@XmlRootElement(name = "activite")
@XmlAccessorType(XmlAccessType.FIELD)
public class Activite {
    @XmlElement(name = "id")
    private String id;
    @XmlElement(name= "label")
    private String label;

    public Activite() {}

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public Activite(String id, String label) {
        this.id = id;
        this.label = label;
    }
}

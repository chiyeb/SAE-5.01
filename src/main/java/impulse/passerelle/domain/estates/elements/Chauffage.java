package impulse.passerelle.domain.estates.elements;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * Représentation d'un nœud XML pour le chauffage.
 * <p>
 * Cette classe décrit les informations relatives au chauffage d'un bien immobilier,
 * incluant son type, l'énergie utilisée, et son format.
 * </p>
 *
 * Exemple de structure XML correspondante :
 * <pre>
 * {@code
 * <chauffage>
 *     <type_chauffage>RADIATEUR</type_chauffage>
 *     <energie_chauffage>GAZ</energie_chauffage>
 *     <format_chauffage>INDIVIDUEL</format_chauffage>
 * </chauffage>
 * }
 * </pre>
 */
@XmlRootElement(name = "chauffage")
@XmlAccessorType(XmlAccessType.FIELD)
public class Chauffage {
    @XmlElement(name = "type_chauffage")
    private String typeChauffage;
    @XmlElement(name = "energie_chauffage")
    private String energieChauffage;
    @XmlElement(name = "format_chauffage")
    private String formatChauffage;

    public Chauffage() {}

    public String getTypeChauffage() {
        return typeChauffage;
    }

    public String getEnergieChauffage() {
        return energieChauffage;
    }

    public String getFormatChauffage() {
        return formatChauffage;
    }

    @Override
    public String toString() {
        return "Chauffage{" +
                "typeChauffage='" + typeChauffage + '\'' +
                ", energieChauffage='" + energieChauffage + '\'' +
                ", formatChauffage='" + formatChauffage + '\'' +
                '}';
    }
}

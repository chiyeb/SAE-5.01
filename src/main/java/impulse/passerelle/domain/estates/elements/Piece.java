package impulse.passerelle.domain.estates.elements;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * Représente une pièce dans un bien immobilier, avec son type, sa surface, son label, sa description, et son étage.
 * <p>
 * Exemple de structure XML :
 * <pre>
 * {@code
 * <piece>
 *     <type><![CDATA[Chambre]]></type>
 *     <surface><![CDATA[20]]></surface>
 *     <label><![CDATA[Chambre principale]]></label>
 *     <description><![CDATA[Pièce calme avec vue]]></description>
 *     <etage>1</etage>
 * </piece>
 * }
 * </pre>
 * </p>
 */
@XmlRootElement(name = "piece")
public class Piece {
    @XmlElement(name = "type")
    private String type;
    @XmlElement(name = "surface")
    private String surface;
    @XmlElement(name = "label")
    private String label;

    @XmlElement(name = "description")
    private String description;
    @XmlElement(name = "etage")
    private double etage;

    public Piece() {}

    public String getType() {
        return type;
    }

    public String getSurface() {
        return surface;
    }

    public String getLabel() {
        return label;
    }

    public String getDescription() {
        return description;
    }

    public double getEtage() {
        return etage;
    }
}

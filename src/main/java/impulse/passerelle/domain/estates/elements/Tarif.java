package impulse.passerelle.domain.estates.elements;


import jakarta.xml.bind.annotation.*;

/**
 * Représente un tarif dans un nœud XML.
 * <p>
 * Exemple de structure XML :
 * <pre>
 * {@code
 * <tarif id="1">
 *     <label>Haute saison</label>
 *     <duree>7</duree>
 *     <prix currency="EUR">500.0</prix>
 *     <periode>
 *         <start>01/01/2023</start>
 *         <end>07/01/2023</end>
 *     </periode>
 * </tarif>
 * }
 * </pre>
 * </p>
 */
@XmlRootElement(name = "tarif")
@XmlAccessorType(XmlAccessType.FIELD)
public class Tarif {
    @XmlAttribute(name = "id")
    private Integer id;
    @XmlElement(name = "label")
    private String label;
    @XmlElement(name = "duree")
    private Integer duree;
    @XmlElement(name = "prix")
    private Prix prix;
    @XmlElement(name = "periode")
    private Periode periode;

    public Tarif() {}

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public Integer getDuree() {
        return duree;
    }

    public Prix getPrix() {
        return prix;
    }

    public Periode getPeriode() {
        return periode;
    }

    @Override
    public String toString() {
        return "Tarif{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", duree=" + duree +
                ", prix=" + prix +
                ", periode='" + periode + '\'' +
                '}';
    }
}

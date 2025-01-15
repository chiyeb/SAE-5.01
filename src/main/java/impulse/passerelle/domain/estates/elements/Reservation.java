package impulse.passerelle.domain.estates.elements;

import jakarta.xml.bind.annotation.*;

/**
 * Représente une réservation dans un nœud XML.
 * <p>
 * Exemple de structure XML :
 * <pre>
 * {@code
 * <reservation id="1">
 *     <start>01/01/2025</start>
 *     <end>07/01/2025</end>
 * </reservation>
 * }
 * </pre>
 * </p>
 */
@XmlRootElement(name = "reservation")
@XmlAccessorType(XmlAccessType.FIELD)
public class Reservation {
    @XmlAttribute(name = "id")
    private Integer id;
    @XmlElement(name = "start")
    private String start;
    @XmlElement(name = "end")
    private String end;

    public Reservation() {}

    public Integer getId() {
        return id;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                '}';
    }
}

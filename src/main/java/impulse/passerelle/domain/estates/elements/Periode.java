package impulse.passerelle.domain.estates.elements;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * Représente une période avec une date de début et une date de fin dans un nœud XML.
 * <p>
 * Exemple de structure XML :
 * <pre>
 * {@code
 * <periode>
 *     <start><![CDATA[2025-01-01]]></start>
 *     <end><![CDATA[2025-12-31]]></end>
 * </periode>
 * }
 * </pre>
 * </p>
 */
@XmlRootElement(name = "periode")
@XmlAccessorType(XmlAccessType.FIELD)
public class Periode {
    @XmlElement(name = "start")
    private String start;
    @XmlElement(name = "end")
    private String end;

    public Periode() {}

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "Periode{" +
                "start='" + start + '\'' +
                ", end='" + end + '\'' +
                '}';
    }
}

package impulse.passerelle.domain.estates.elements;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlValue;

import java.util.Objects;

/**
 * Représente un pays dans un nœud XML avec un code ISO et un nom.
 * <p>
 * Exemple de structure XML :
 * <pre>
 * {@code
 * <pays ISO="FR"><![CDATA[France]]></pays>
 * }
 * </pre>
 * </p>
 */
@XmlRootElement(name = "pays")
public class Pays {
    @XmlAttribute(name = "ISO")
    private String iso;
    @XmlValue
    private String nom;

    public Pays() {}

    public Pays(String iso, String nom) {
        this.iso = iso;
        this.nom = nom;
    }

    public String getIso() {
        return iso;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pays pays = (Pays) o;
        return Objects.equals(iso, pays.iso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iso);
    }
}

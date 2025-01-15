package impulse.passerelle.domain.estates.elements;

import impulse.passerelle.domain.estates.lists.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * Représente une location saisonnière, contenant des informations sur les tarifs,
 * les options, les services, les contraintes, les loisirs, et les réservations.
 * Cette classe est utilisée pour encapsuler les informations d'une annonce de location saisonnière.
 * <p>
 * Exemple de nœud XML pour une location saisonnière :
 * <pre>
 * {@code
 * <ls>
 *     <tarifs>...</tarifs>
 *     <opts>...</opts>
 *     <services>...</services>
 *     <contraintes>...</contraintes>
 *     <loisirs>...</loisirs>
 *     <reservations>...</reservations>
 * </ls>
 * }
 * </pre>
 * </p>
 */
@XmlRootElement(name = "ls")
@XmlAccessorType(XmlAccessType.FIELD)
public class LocationSaisonniere {
    @XmlElement(name = "tarifs")
    private Tarifs tarifs;
    @XmlElement(name = "opts")
    private Options options;

    @XmlElement(name = "services")
    private Services services;

    @XmlElement(name = "contraintes")
    private Contraintes contraintes;

    @XmlElement(name = "loisirs")
    private Loisirs loisirs;
    @XmlElement(name = "reservations")
    private Reservations reservations;

    public LocationSaisonniere() {
    }

    @Override
    public String toString() {
        return "LocationSaisonniere{" +
                "tarifs=" + tarifs +
                ", options=" + options +
                ", services=" + services +
                ", contraintes=" + contraintes +
                ", loisirs=" + loisirs +
                ", reservations=" + reservations +
                '}';
    }
}

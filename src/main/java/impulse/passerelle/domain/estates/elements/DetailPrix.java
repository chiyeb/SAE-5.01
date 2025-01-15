package impulse.passerelle.domain.estates.elements;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * Représentation d'un nœud XML pour les locations saisonnières.
 * <p>
 * Cette classe contient les détails des prix pour différentes périodes de location,
 * en distinguant les saisons basse et haute. Les périodes couvertes incluent :
 * <ul>
 *     <li>{@code prix_semaine_basse} : Prix par semaine en basse saison.</li>
 *     <li>{@code prix_quinzaine_basse} : Prix par quinzaine en basse saison.</li>
 *     <li>{@code prix_mois_basse} : Prix par mois en basse saison.</li>
 *     <li>{@code prix_semaine_haute} : Prix par semaine en haute saison.</li>
 *     <li>{@code prix_quinzaine_haute} : Prix par quinzaine en haute saison.</li>
 *     <li>{@code prix_mois_haute} : Prix par mois en haute saison.</li>
 * </ul>
 * </p>
 *
 * Exemple de structure XML correspondante :
 * <pre>
 * {@code
 * <detail_prix>
 *     <prix_semaine_basse>500.0</prix_semaine_basse>
 *     <prix_quinzaine_basse>900.0</prix_quinzaine_basse>
 *     <prix_mois_basse>1600.0</prix_mois_basse>
 *     <prix_semaine_haute>700.0</prix_semaine_haute>
 *     <prix_quinzaine_haute>1300.0</prix_quinzaine_haute>
 *     <prix_mois_haute>2400.0</prix_mois_haute>
 * </detail_prix>
 * }
 * </pre>
 */
@XmlRootElement(name = "detail_prix")
@XmlAccessorType(XmlAccessType.FIELD)
public class DetailPrix {
    @XmlElement(name = "prix_semaine_basse")
    private Double prixSemaineBasse;
    @XmlElement(name = "prix_quinzaine_basse")
    private Double prixQuinzaineBasse;
    @XmlElement(name = "prix_mois_basse")
    private Double prixMoisBasse;
    @XmlElement(name = "prix_semaine_haute")
    private Double prixSemaineHaute;
    @XmlElement(name = "prix_quinzaine_haute")
    private Double prixQuinzaineHaute;
    @XmlElement(name = "prix_mois_haute")
    private Double prixMoisHaute;

    public DetailPrix() {}

    public Double getPrixSemaineBasse() {
        return prixSemaineBasse;
    }

    public Double getPrixQuinzaineBasse() {
        return prixQuinzaineBasse;
    }

    public Double getPrixMoisBasse() {
        return prixMoisBasse;
    }

    public Double getPrixSemaineHaute() {
        return prixSemaineHaute;
    }

    public Double getPrixQuinzaineHaute() {
        return prixQuinzaineHaute;
    }

    public Double getPrixMoisHaute() {
        return prixMoisHaute;
    }
}

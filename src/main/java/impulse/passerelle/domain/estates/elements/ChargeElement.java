package impulse.passerelle.domain.estates.elements;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * Représentation d'un nœud XML pour les charges des locations.
 * <p>
 * Cette classe représente un élément XML décrivant une charge spécifique associée à une location.
 * Le nœud contient trois attributs principaux :
 * <ul>
 *     <li>{@code chargeElementName} : Le nom de la charge.</li>
 *     <li>{@code chargeElementValeur} : La valeur associée à la charge.</li>
 *     <li>{@code chargeElementRecuperable} : Indique si la charge est récupérable ou non.</li>
 * </ul>
 * Les annotations JAXB permettent la sérialisation et la désérialisation entre objets Java et XML.
 * </p>
 *
 * Exemple de structure XML correspondante :
 * <pre>
 * {@code
 * <chargeElement>
 *     <chargeElementName>Eau</chargeElementName>
 *     <chargeElementValeur>50</chargeElementValeur>
 *     <chargeElementRecuperable>true</chargeElementRecuperable>
 * </chargeElement>
 * }
 * </pre>
 *
 */
@XmlRootElement(name = "chargeElement")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class ChargeElement {
    @XmlElement(name = "chargeElementName")
    private String name;
    @XmlElement(name = "chargeElementValeur")
    private String valeur;
    @XmlElement(name = "chargeElementRecuperable")
    private Boolean recuperable;

    public ChargeElement() {}

    public String getName() {
        return name;
    }

    public String getValeur() {
        return valeur;
    }

    public Boolean isRecuperable() {
        return recuperable;
    }
}

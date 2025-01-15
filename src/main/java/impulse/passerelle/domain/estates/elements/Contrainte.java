package impulse.passerelle.domain.estates.elements;

import jakarta.xml.bind.annotation.*;

/**
 * Représentation d'un nœud XML pour une contrainte.
 * <p>
 * Cette classe décrit une contrainte liée à un bien immobilier, avec deux éléments principaux :
 * <ul>
 *     <li>{@code id} : Identifiant unique de la contrainte (attribut XML).</li>
 *     <li>{@code description} : Description textuelle de la contrainte (valeur XML).</li>
 * </ul>
 * </p>
 *
 * Exemple de structure XML correspondante :
 * <pre>
 * {@code
 * <contrainte id="4"><![CDATA[Animaux non autorisés]]></contrainte>
 * }
 * </pre>
 */
@XmlRootElement(name = "contrainte")
@XmlAccessorType(XmlAccessType.FIELD)
public class Contrainte {
    @XmlAttribute(name = "id")
    private Integer id;
    @XmlValue
    private String description;

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}

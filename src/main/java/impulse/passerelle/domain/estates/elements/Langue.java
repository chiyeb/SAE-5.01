package impulse.passerelle.domain.estates.elements;

import jakarta.xml.bind.annotation.*;

/**
 * Représentation d'un nœud XML pour les informations multilingues liées à une annonce.
 * <p>
 * Cette classe contient des éléments associés à une langue spécifique pour une annonce,
 * tels que le titre, le corps et un corps réduit.
 * </p>
 *
 * Exemple de structure XML correspondante :
 * <pre>
 * {@code
 * <langue code="fr">
 *     <titre><![CDATA[Titre de l'annonce en français]]></titre>
 *     <corps><![CDATA[Corps de l'annonce en français]]></corps>
 *     <corps_impression><![CDATA[Corps réduit pour impression]]></corps_impression>
 * </langue>
 * }
 * </pre>
 */
@XmlRootElement(name = "langue")
@XmlAccessorType(XmlAccessType.FIELD)
public class Langue {
    @XmlAttribute(name = "code")
    private String code;
    @XmlElement(name = "titre")
    private String titre;
    @XmlElement(name = "corps")
    private String corps;
    @XmlElement(name = "corps_impression")
    private String corps_impression;

    public Langue() {}

    public String getCode() {
        return code;
    }

    public String getTitre() {
        return titre;
    }

    public String getCorps() {
        return corps;
    }

    public String getCorps_impression() {
        return corps_impression;
    }

    @Override
    public String toString() {
        return "Langue{" +
                "code='" + code + '\'' +
                ", titre='" + titre + '\'' +
                ", corps='" + corps + '\'' +
                ", corps_impression='" + corps_impression + '\'' +
                '}';
    }
}

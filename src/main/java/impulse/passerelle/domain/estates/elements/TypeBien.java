package impulse.passerelle.domain.estates.elements;

/**
 * Représente le type de bien immobilier dans une annonce.
 * <p>
 * Ce record encapsule les informations suivantes  :
 * <ul>
 *     <li><b>code</b>: Code du type de bien (par exemple, 2 pour "Appartement").</li>
 *     <li><b>name</b>: Nom du type de bien (par exemple, "Appartement").</li>
 * </ul>
 * <p>
 * Exemple de nœud XML pour une annonce :
 * <pre>
 * {@code
 * <ad>
 *     <type_bien><![CDATA[Appartement]]></type_bien>
 *     <type_bien_code>2</type_bien_code>
 * </ad>
 * }
 * </pre>
 * </p>
 */
public record TypeBien(int code, String name) {
}

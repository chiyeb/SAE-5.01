package impulse.passerelle.domain.estates.elements;

import java.util.Objects;

/**
 * Représente une ville associée à une annonce immobilière.
 * <p>
 * Cette classe encapsule les informations suivantes :
 * <ul>
 *     <li><b>cp</b>: Code postal de la ville (par exemple, "83400").</li>
 *     <li><b>nom</b>: Nom de la ville (par exemple, "Hyères").</li>
 *     <li><b>pays</b>: Le pays de la ville avec un code ISO (par exemple, "FR" pour la France).</li>
 * </ul>
 * Exemple de nœud XML pour une annonce :
 * <pre>
 * {@code
 * <ad>
 *     <ville><![CDATA[Hyères]]></ville>
 *     <cp><![CDATA[83400]]></cp>
 *     <pays ISO="FR"><![CDATA[France]]></pays>
 * </ad>
 * }
 * </pre>
 * </p>
 */
public class Ville {
    private final String cp;
    private final String nom;
    private final impulse.passerelle.domain.estates.elements.Pays pays;

    public Ville(String cp, String nom, Pays pays) {
        this.cp = cp;
        this.nom = nom;
        this.pays = pays;
    }

    public String getCp() {
        return cp;
    }

    public String getNom() {
        return nom;
    }

    public String getPaysIso() {
        return pays != null ? pays.getIso() : null;
    }

    private String getPaysNom() {
        return pays != null ? pays.getNom() : null;
    }
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Ville ville)) return false;
        return Objects.equals(cp, ville.cp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cp);
    }
}

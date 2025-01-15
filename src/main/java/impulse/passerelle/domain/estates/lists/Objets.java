package impulse.passerelle.domain.estates.lists;

import java.util.List;

/**
 * Classe abstraite servant de wrapper pour une liste d'objets de type générique T.
 * Fournit une méthode abstraite pour obtenir les éléments de la liste.
 *
 * Utilisé pour mapper des noeuds XML contenus des sous-noeuds dans des listes par exemple :
 * <pre>
 * {@code
 *  <objets>
 *      <objet>...</objet>
 *      <objet>...</objet>
 *  </objets>
 * }</pre>
 * @param <T> Type des éléments contenus dans la liste.
 */
public abstract class Objets<T> {
    public abstract List<T> getElements();
}

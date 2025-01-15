package impulse.passerelle.repositories.annonces;

import impulse.passerelle.domain.estates.Annonce;
import impulse.passerelle.domain.filtering.estates.EstateFilterData;
import impulse.passerelle.domain.sorting.estates.SortData;

import java.util.List;

public interface AnnonceRepository {
    List<Annonce> getAllAnnonces(SortData sortData, EstateFilterData filterData);

    Annonce getAnnonceById(int id);
}

package impulse.passerelle.repositories.annonces;

import impulse.passerelle.domain.estates.elements.Pays;

import java.util.List;

public interface PaysRepository {
    List<Pays> getAllCountries();
    Pays getCountryByName(String name);
    Pays getCountryByIso(String iso);
}

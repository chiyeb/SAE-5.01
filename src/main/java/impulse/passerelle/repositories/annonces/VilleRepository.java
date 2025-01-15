package impulse.passerelle.repositories.annonces;

import impulse.passerelle.domain.estates.elements.Ville;
import impulse.passerelle.domain.filtering.cities.CityFilterData;

import java.util.List;
public interface VilleRepository {
    List<Ville> getAllCities(CityFilterData filterData);
    Ville getCityByPostalCode(String postalCode);
    Ville getCityByName(String name);
}

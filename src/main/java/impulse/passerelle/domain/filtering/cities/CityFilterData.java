package impulse.passerelle.domain.filtering.cities;

import impulse.passerelle.domain.estates.elements.Ville;

import java.util.List;

public record CityFilterData(String pays) {
    public List<Ville> filter(List<Ville> cities) {
        return cities.stream().filter(
                city -> (pays == null || city.getPaysIso().equals(pays))
        ).toList();
    }
}

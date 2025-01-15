package impulse.passerelle.domain.filtering.city;

import impulse.passerelle.domain.estates.elements.Pays;
import impulse.passerelle.domain.estates.elements.Ville;
import impulse.passerelle.domain.filtering.cities.CityFilterData;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CityFilterDataTest {

    @Test
    void filter_shouldReturnAllCitiesWhenCountryIsNull() {
        CityFilterData cityFilterData = new CityFilterData(null);
        Ville city1 = new Ville("75000", "Paris", new Pays("FR", "France"));
        Ville city2 = new Ville("10115", "Berlin", new Pays("DE", "Allemagne"));
        List<Ville> cities = List.of(city1, city2);
        List<Ville> filteredCities = cityFilterData.filter(cities);
        assertEquals(2, filteredCities.size());
        assertTrue(filteredCities.contains(city1));
        assertTrue(filteredCities.contains(city2));
    }

    @Test
    void filter_shouldReturnCitiesMatchingCountry() {
        CityFilterData cityFilterData = new CityFilterData("FR");
        Ville city1 = new Ville("75000", "Paris", new Pays("FR", "France"));
        Ville city2 = new Ville("10115", "Berlin", new Pays("DE", "Allemagne"));
        List<Ville> cities = List.of(city1, city2);
        List<Ville> filteredCities = cityFilterData.filter(cities);
        assertEquals(1, filteredCities.size());
        assertTrue(filteredCities.contains(city1));
        assertFalse(filteredCities.contains(city2));
    }

    @Test
    void filter_shouldReturnEmptyListWhenNoCitiesMatchCountry() {
        CityFilterData cityFilterData = new CityFilterData("ES");
        Ville city1 = new Ville("75000", "Paris", new Pays("FR", "France"));
        Ville city2 = new Ville("10115", "Berlin", new Pays("DE", "Allemagne"));
        List<Ville> cities = List.of(city1, city2);
        List<Ville> filteredCities = cityFilterData.filter(cities);
        assertTrue(filteredCities.isEmpty());
    }

    @Test
    void filter_shouldReturnEmptyListWhenCitiesListIsEmpty() {
        CityFilterData cityFilterData = new CityFilterData("FR");
        List<Ville> cities = List.of();
        List<Ville> filteredCities = cityFilterData.filter(cities);
        assertTrue(filteredCities.isEmpty());
    }
}
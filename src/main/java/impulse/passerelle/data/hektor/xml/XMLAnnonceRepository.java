package impulse.passerelle.data.hektor.xml;

import impulse.passerelle.domain.estates.Annonce;
import impulse.passerelle.domain.estates.Annonces;
import impulse.passerelle.domain.estates.elements.Pays;
import impulse.passerelle.domain.estates.elements.Ville;
import impulse.passerelle.domain.filtering.cities.CityFilterData;
import impulse.passerelle.domain.filtering.estates.EstateFilterData;
import impulse.passerelle.domain.sorting.estates.SortData;
import impulse.passerelle.repositories.annonces.AnnonceRepository;
import impulse.passerelle.repositories.annonces.PaysRepository;
import impulse.passerelle.repositories.annonces.VilleRepository;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Collections;
import java.util.List;

@Primary
@Repository
public class XMLAnnonceRepository implements AnnonceRepository, PaysRepository, VilleRepository {
    @Value("${xml.path}")
    private final String xmlPath = null;

    private final JAXBContext context = JAXBContext.newInstance(Annonces.class);
    private List<Annonce> annonces;

    public XMLAnnonceRepository() throws JAXBException {}

    private boolean tryToGetAnnoncesFromFile() {
        try {
            Annonces annoncesObject = (Annonces) context.createUnmarshaller().unmarshal(new FileReader(xmlPath));
            annonces = annoncesObject.getElements();
        }
        catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<Annonce> getAllAnnonces(SortData sortData, EstateFilterData filterData) {
        List<Annonce> data = tryToGetAnnoncesFromFile() ?
                annonces :
                Collections.emptyList();
        if (sortData != null) data = data.stream().sorted(sortData.getComparator()).toList();
        return filterData.filter(data);
    }

    public Annonce getAnnonceById(int id) {
        return tryToGetAnnoncesFromFile() ?
                annonces.stream().filter(annonce -> annonce.getId().getId() == id).findFirst().orElse(null) :
                null;
    }

    @Override
    public List<Pays> getAllCountries() {
        return tryToGetAnnoncesFromFile() ?
                annonces.stream().map(Annonce::getPays).distinct().toList() :
                Collections.emptyList();
    }

    @Override
    public Pays getCountryByName(String name) {
        return tryToGetAnnoncesFromFile() ?
                annonces.stream().map(Annonce::getPays).filter(pays -> pays.getNom().equals(name)).findFirst().orElse(null) :
                null;
    }

    @Override
    public Pays getCountryByIso(String iso) {
        return  tryToGetAnnoncesFromFile() ?
                annonces.stream().map(Annonce::getPays).filter(pays -> pays.getIso().equals(iso)).findFirst().orElse(null) :
                null;
    }

    @Override
    public List<Ville> getAllCities(CityFilterData filterData) {
        return tryToGetAnnoncesFromFile() ?
                filterData.filter(annonces.stream().map(Annonce::getVilleAsClass).distinct().toList()) :
                Collections.emptyList();
    }

    @Override
    public Ville getCityByPostalCode(String postalCode) {
        return tryToGetAnnoncesFromFile() ?
                annonces.stream().map(Annonce::getVilleAsClass).filter(ville -> ville.getCp().equals(postalCode)).findFirst().orElse(null) :
                null;
    }

    @Override
    public Ville getCityByName(String name) {
        return tryToGetAnnoncesFromFile() ?
                annonces.stream().map(Annonce::getVilleAsClass).filter(ville -> ville.getNom().equals(name)).findFirst().orElse(null) :
                null;
    }
}

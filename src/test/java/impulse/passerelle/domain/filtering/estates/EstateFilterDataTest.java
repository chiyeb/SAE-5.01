package impulse.passerelle.domain.filtering.estates;

import impulse.passerelle.domain.estates.Annonce;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EstateFilterDataTest {

    @Test
    void filter_shouldReturnAllAnnoncesWhenNoFilterIsApplied() {
        EstateFilterData filterData = new EstateFilterData(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        Annonce annonce1 = Annonce.getAnnonceForFilteringTest("FR", "75001", 1, 500000.0, 100.0, 3, 2, 80.0, 120.0, "A", "B", 1, true, "SUD", "vente");
        Annonce annonce2 = Annonce.getAnnonceForFilteringTest("DE", "10115", 2, 300000.0, 80.0, 2, 1, 60.0, 90.0, "B", "C", 1, false, "NORD", "location");
        List<Annonce> annonces = List.of(annonce1, annonce2);
        List<Annonce> filteredAnnonces = filterData.filter(annonces);
        assertEquals(2, filteredAnnonces.size());
        assertTrue(filteredAnnonces.contains(annonce1));
        assertTrue(filteredAnnonces.contains(annonce2));
    }

    @Test
    void filter_shouldReturnAnnoncesMatchingCountry() {
        EstateFilterData filterData = new EstateFilterData("FR", null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        Annonce annonce1 = Annonce.getAnnonceForFilteringTest("FR", "75001", 1, 500000.0, 100.0, 3, 2, 80.0, 120.0, "A", "B", 1, true, "SUD", "vente");
        Annonce annonce2 = Annonce.getAnnonceForFilteringTest("DE", "10115", 2, 300000.0, 80.0, 2, 1, 60.0, 90.0, "B", "C", 1, false, "NORD", "location");
        List<Annonce> annonces = List.of(annonce1, annonce2);
        List<Annonce> filteredAnnonces = filterData.filter(annonces);
        assertEquals(1, filteredAnnonces.size());
        assertTrue(filteredAnnonces.contains(annonce1));
        assertFalse(filteredAnnonces.contains(annonce2));
    }

    @Test
    void filter_shouldReturnAnnoncesMatchingMaxBudget() {
        EstateFilterData filterData = new EstateFilterData(null, null, null, 400000.0, null, null, null, null, null, null, null, null, null, null, null);
        Annonce annonce1 = Annonce.getAnnonceForFilteringTest("FR", "75001", 1, 500000.0, 100.0, 3, 2, 80.0, 120.0, "A", "B", 1, true, "SUD", "vente");
        Annonce annonce2 = Annonce.getAnnonceForFilteringTest("DE", "10115", 2, 300000.0, 80.0, 2, 1, 60.0, 90.0, "B", "C", 1, false, "NORD", "location");
        List<Annonce> annonces = List.of(annonce1, annonce2);
        List<Annonce> filteredAnnonces = filterData.filter(annonces);
        assertEquals(1, filteredAnnonces.size());
        assertFalse(filteredAnnonces.contains(annonce1));
        assertTrue(filteredAnnonces.contains(annonce2));
    }

    @Test
    void filter_shouldReturnEmptyListWhenNoAnnoncesMatch() {
        EstateFilterData filterData = new EstateFilterData("ES", null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        Annonce annonce1 = Annonce.getAnnonceForFilteringTest("FR", "75001", 1, 500000.0, 100.0, 3, 2, 80.0, 120.0, "A", "B", 1, true, "SUD", "vente");
        Annonce annonce2 = Annonce.getAnnonceForFilteringTest("DE", "10115", 2, 300000.0, 80.0, 2, 1, 60.0, 90.0, "B", "C", 1, false, "NORD", "location");
        List<Annonce> annonces = List.of(annonce1, annonce2);
        List<Annonce> filteredAnnonces = filterData.filter(annonces);
        assertTrue(filteredAnnonces.isEmpty());
    }

    @Test
    void filter_shouldReturnEmptyListWhenAnnoncesListIsEmpty() {
        EstateFilterData filterData = new EstateFilterData("FR", null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        List<Annonce> annonces = List.of();
        List<Annonce> filteredAnnonces = filterData.filter(annonces);
        assertTrue(filteredAnnonces.isEmpty());
    }
}
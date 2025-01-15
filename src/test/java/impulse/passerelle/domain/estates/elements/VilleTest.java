package impulse.passerelle.domain.estates.elements;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VilleTest {

    @Test
    void equalsShouldReturnTrueForSameCp() {
        Ville ville1 = new Ville("83400", "Hyères", new Pays("FR", "France"));
        Ville ville2 = new Ville("83400", "Toulon", new Pays("FR", "France"));
        assertTrue(ville1.equals(ville2));
    }

    @Test
    void equalsShouldReturnFalseForDifferentCp() {
        Ville ville1 = new Ville("83400", "Hyères", new Pays("FR", "France"));
        Ville ville2 = new Ville("75000", "Paris", new Pays("FR", "France"));
        assertFalse(ville1.equals(ville2));
    }

    @Test
    void hashCodeShouldBeSameForSameCp() {
        Ville ville1 = new Ville("83400", "Hyères", new Pays("FR", "France"));
        Ville ville2 = new Ville("83400", "Toulon", new Pays("FR", "France"));
        assertEquals(ville1.hashCode(), ville2.hashCode());
    }

    @Test
    void hashCodeShouldBeDifferentForDifferentCp() {
        Ville ville1 = new Ville("83400", "Hyères", new Pays("FR", "France"));
        Ville ville2 = new Ville("75000", "Paris", new Pays("FR", "France"));
        assertNotEquals(ville1.hashCode(), ville2.hashCode());
    }

    @Test
    void getPaysIsoShouldReturnIsoCode() {
        Ville ville = new Ville("83400", "Hyères", new Pays("FR", "France"));
        assertEquals("FR", ville.getPaysIso());
    }

    @Test
    void getPaysIsoShouldReturnNullIfPaysIsNull() {
        Ville ville = new Ville("83400", "Hyères", null);
        assertNull(ville.getPaysIso());
    }

    @Test
    void getCpShouldReturnCp() {
        Ville ville = new Ville("83400", "Hyères", new Pays("FR", "France"));
        assertEquals("83400", ville.getCp());
    }

    @Test
    void getNomShouldReturnNom() {
        Ville ville = new Ville("83400", "Hyères", new Pays("FR", "France"));
        assertEquals("Hyères", ville.getNom());
    }
}

package impulse.passerelle.domain.estates.elements;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TypeBienTest {

    @Test
    void codeShouldReturnCorrectCode() {
        TypeBien typeBien = new TypeBien(2, "Appartement");
        assertEquals(2, typeBien.code());
    }

    @Test
    void nameShouldReturnCorrectName() {
        TypeBien typeBien = new TypeBien(2, "Appartement");
        assertEquals("Appartement", typeBien.name());
    }

    @Test
    void codeShouldHandleNegativeCode() {
        TypeBien typeBien = new TypeBien(-1, "Unknown");
        assertEquals(-1, typeBien.code());
    }

    @Test
    void nameShouldHandleEmptyName() {
        TypeBien typeBien = new TypeBien(2, "");
        assertEquals("", typeBien.name());
    }

    @Test
    void nameShouldHandleNullName() {
        TypeBien typeBien = new TypeBien(2, null);
        assertNull(typeBien.name());
    }
}

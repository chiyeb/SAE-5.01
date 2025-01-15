package impulse.passerelle.domain.estates.elements;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.junit.jupiter.api.Test;

import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class TarifXmlTest {

    @Test
    void unmarshall_shouldConvertXmlToTarif() throws JAXBException {
        String xml = "<tarif id=\"1\"><label>Haute saison</label><duree>7</duree><prix currency=\"EUR\">500.0</prix><periode><start>01/01/2023</start><end>07/01/2023</end></periode></tarif>";

        JAXBContext context = JAXBContext.newInstance(Tarif.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Tarif tarif = (Tarif) unmarshaller.unmarshal(new StringReader(xml));

        assertEquals(1, tarif.getId());
        assertEquals("Haute saison", tarif.getLabel());
        assertEquals(7, tarif.getDuree());
        assertEquals(500.0, tarif.getPrix().getValeur());
        assertEquals("EUR", tarif.getPrix().getCurrency());
        assertEquals("01/01/2023", tarif.getPeriode().getStart());
        assertEquals("07/01/2023", tarif.getPeriode().getEnd());
    }
}
package impulse.passerelle.domain.estates.lists;

import impulse.passerelle.domain.estates.elements.Tarif;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TarifsXmlTest {

    @Test
    void unmarshall_shouldConvertXmlToTarifs() throws JAXBException {
        String xml = """
                <tarifs>
                    <tarif id="1">
                        <label>Haute saison</label>
                        <duree>7</duree>
                        <prix currency="EUR">500.0</prix>
                        <periode>
                            <start>01/01/2023</start>
                            <end>07/01/2023</end>
                        </periode>
                    </tarif>
                    <tarif id="2">
                        <label>Basse saison</label>
                        <duree>7</duree>
                        <prix currency="EUR">300.0</prix>
                        <periode>
                            <start>01/02/2023</start>
                            <end>07/02/2023</end>
                        </periode>
                    </tarif>
                </tarifs>
                """;

        JAXBContext context = JAXBContext.newInstance(Tarifs.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Tarifs tarifs = (Tarifs) unmarshaller.unmarshal(new StringReader(xml));

        List<Tarif> tarifList = tarifs.getElements();
        assertEquals(2, tarifList.size());
        assertEquals(1, tarifList.get(0).getId());
        assertEquals("Haute saison", tarifList.get(0).getLabel());
        assertEquals(7, tarifList.get(0).getDuree());
        assertEquals(500.0, tarifList.get(0).getPrix().getValeur());
        assertEquals("EUR", tarifList.get(0).getPrix().getCurrency());
        assertEquals("01/01/2023", tarifList.get(0).getPeriode().getStart());
        assertEquals("07/01/2023", tarifList.get(0).getPeriode().getEnd());
        assertEquals(2, tarifList.get(1).getId());
        assertEquals("Basse saison", tarifList.get(1).getLabel());
        assertEquals(7, tarifList.get(1).getDuree());
        assertEquals(300.0, tarifList.get(1).getPrix().getValeur());
        assertEquals("EUR", tarifList.get(1).getPrix().getCurrency());
        assertEquals("01/02/2023", tarifList.get(1).getPeriode().getStart());
        assertEquals("07/02/2023", tarifList.get(1).getPeriode().getEnd());
    }
}

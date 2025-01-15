package impulse.passerelle.domain.estates.elements;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.junit.jupiter.api.Test;

import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DetailPrixXmlTest {

    @Test
    void unmarshall_shouldConvertXmlToDetailPrix() throws JAXBException {
        String xml = "<detail_prix>" +
                "<prix_semaine_basse>500.0</prix_semaine_basse>" +
                "<prix_quinzaine_basse>900.0</prix_quinzaine_basse>" +
                "<prix_mois_basse>1600.0</prix_mois_basse>" +
                "<prix_semaine_haute>700.0</prix_semaine_haute>" +
                "<prix_quinzaine_haute>1300.0</prix_quinzaine_haute>" +
                "<prix_mois_haute>2400.0</prix_mois_haute>" +
                "</detail_prix>";

        JAXBContext context = JAXBContext.newInstance(DetailPrix.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        DetailPrix detailPrix = (DetailPrix) unmarshaller.unmarshal(new StringReader(xml));

        assertEquals(500.0, detailPrix.getPrixSemaineBasse());
        assertEquals(900.0, detailPrix.getPrixQuinzaineBasse());
        assertEquals(1600.0, detailPrix.getPrixMoisBasse());
        assertEquals(700.0, detailPrix.getPrixSemaineHaute());
        assertEquals(1300.0, detailPrix.getPrixQuinzaineHaute());
        assertEquals(2400.0, detailPrix.getPrixMoisHaute());
    }
}
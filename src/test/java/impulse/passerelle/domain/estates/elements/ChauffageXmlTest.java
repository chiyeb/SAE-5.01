package impulse.passerelle.domain.estates.elements;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.junit.jupiter.api.Test;

import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class ChauffageXmlTest {

    @Test
    void unmarshall_shouldConvertXmlToChauffage() throws JAXBException {
        String xml = "<chauffage><type_chauffage>RADIATEUR</type_chauffage><energie_chauffage>GAZ</energie_chauffage><format_chauffage>INDIVIDUEL</format_chauffage></chauffage>";

        JAXBContext context = JAXBContext.newInstance(Chauffage.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Chauffage chauffage = (Chauffage) unmarshaller.unmarshal(new StringReader(xml));

        assertEquals("RADIATEUR", chauffage.getTypeChauffage());
        assertEquals("GAZ", chauffage.getEnergieChauffage());
        assertEquals("INDIVIDUEL", chauffage.getFormatChauffage());
    }
}
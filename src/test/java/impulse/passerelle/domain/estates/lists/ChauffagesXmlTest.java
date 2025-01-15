package impulse.passerelle.domain.estates.lists;

import impulse.passerelle.domain.estates.elements.Chauffage;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChauffagesXmlTest {

    @Test
    void unmarshall_shouldConvertXmlToChauffages() throws JAXBException {
        String xml = """
                <chauffages>
                    <chauffage>
                        <type_chauffage>RADIATEUR</type_chauffage>
                        <energie_chauffage>GAZ</energie_chauffage>
                        <format_chauffage>INDIVIDUEL</format_chauffage>
                    </chauffage>
                    <chauffage>
                        <type_chauffage>CHAUDIERE</type_chauffage>
                        <energie_chauffage>FIOUL</energie_chauffage>
                        <format_chauffage>COLLECTIF</format_chauffage>
                    </chauffage>
                </chauffages>
                """;

        JAXBContext context = JAXBContext.newInstance(Chauffages.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Chauffages chauffages = (Chauffages) unmarshaller.unmarshal(new StringReader(xml));

        List<Chauffage> chauffageList = chauffages.getElements();
        assertEquals(2, chauffageList.size());
        assertEquals("RADIATEUR", chauffageList.get(0).getTypeChauffage());
        assertEquals("GAZ", chauffageList.get(0).getEnergieChauffage());
        assertEquals("INDIVIDUEL", chauffageList.get(0).getFormatChauffage());
        assertEquals("CHAUDIERE", chauffageList.get(1).getTypeChauffage());
        assertEquals("FIOUL", chauffageList.get(1).getEnergieChauffage());
        assertEquals("COLLECTIF", chauffageList.get(1).getFormatChauffage());
    }
}

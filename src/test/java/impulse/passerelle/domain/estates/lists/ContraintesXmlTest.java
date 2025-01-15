package impulse.passerelle.domain.estates.lists;

import impulse.passerelle.domain.estates.elements.Contrainte;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContraintesXmlTest {

    @Test
    void unmarshall_shouldConvertXmlToContraintes() throws JAXBException {
        String xml = """
                <contraintes>
                    <contrainte id="1"><![CDATA[Animaux non autorisés]]></contrainte>
                    <contrainte id="2"><![CDATA[Pas de bruit après 22h]]></contrainte>
                </contraintes>
                """;

        JAXBContext context = JAXBContext.newInstance(Contraintes.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Contraintes contraintes = (Contraintes) unmarshaller.unmarshal(new StringReader(xml));

        List<Contrainte> contrainteList = contraintes.getElements();
        assertEquals(2, contrainteList.size());
        assertEquals(1, contrainteList.get(0).getId());
        assertEquals("Animaux non autorisés", contrainteList.get(0).getDescription());
        assertEquals(2, contrainteList.get(1).getId());
        assertEquals("Pas de bruit après 22h", contrainteList.get(1).getDescription());
    }
}

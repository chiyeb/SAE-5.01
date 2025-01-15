package impulse.passerelle.domain.estates.elements;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.junit.jupiter.api.Test;

import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class ContrainteXmlTest {

    @Test
    void unmarshall_shouldConvertXmlToContrainte() throws JAXBException {
        String xml = "<contrainte id=\"4\"><![CDATA[Animaux non autorisés]]></contrainte>";

        JAXBContext context = JAXBContext.newInstance(Contrainte.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Contrainte contrainte = (Contrainte) unmarshaller.unmarshal(new StringReader(xml));

        assertEquals(4, contrainte.getId());
        assertEquals("Animaux non autorisés", contrainte.getDescription());
    }
}
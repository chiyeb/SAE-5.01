package impulse.passerelle.domain.estates.elements;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.junit.jupiter.api.Test;

import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class LoisirXmlTest {

    @Test
    void unmarshall_shouldConvertXmlToLoisir() throws JAXBException {
        String xml = "<loisir id=\"1\">Description du loisir</loisir>";

        JAXBContext context = JAXBContext.newInstance(Loisir.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Loisir loisir = (Loisir) unmarshaller.unmarshal(new StringReader(xml));

        assertEquals(1, loisir.getId());
        assertEquals("Description du loisir", loisir.getDescription());
    }
}
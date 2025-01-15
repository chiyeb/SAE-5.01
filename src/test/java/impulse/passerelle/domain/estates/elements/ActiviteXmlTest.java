package impulse.passerelle.domain.estates.elements;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;

class ActiviteXmlTest {
    @Test
    void unmarshall_shouldConvertXmlToActivite() throws JAXBException {
        String xml = "<activite><id>123</id><label>Restaurant</label></activite>";

        JAXBContext context = JAXBContext.newInstance(Activite.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Activite activite = (Activite) unmarshaller.unmarshal(new StringReader(xml));

        assertEquals("123", activite.getId());
        assertEquals("Restaurant", activite.getLabel());
    }
}

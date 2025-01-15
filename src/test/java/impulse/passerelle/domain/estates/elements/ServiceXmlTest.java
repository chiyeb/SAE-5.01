package impulse.passerelle.domain.estates.elements;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.junit.jupiter.api.Test;

import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class ServiceXmlTest {

    @Test
    void unmarshall_shouldConvertXmlToService() throws JAXBException {
        String xml = "<service id=\"1\">Draps fournis</service>";

        JAXBContext context = JAXBContext.newInstance(Service.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Service service = (Service) unmarshaller.unmarshal(new StringReader(xml));

        assertEquals(1, service.getId());
        assertEquals("Draps fournis", service.getDescription());
    }
}
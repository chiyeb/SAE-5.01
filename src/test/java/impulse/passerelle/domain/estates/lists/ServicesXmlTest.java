package impulse.passerelle.domain.estates.lists;

import impulse.passerelle.domain.estates.elements.Service;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ServicesXmlTest {

    @Test
    void unmarshall_shouldConvertXmlToServices() throws JAXBException {
        String xml = """
                <services>
                    <service id="1">Draps fournis</service>
                    <service id="2">Petit déjeuner inclus</service>
                </services>
                """;

        JAXBContext context = JAXBContext.newInstance(Services.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Services services = (Services) unmarshaller.unmarshal(new StringReader(xml));

        List<Service> serviceList = services.getElements();
        assertEquals(2, serviceList.size());
        assertEquals(1, serviceList.get(0).getId());
        assertEquals("Draps fournis", serviceList.get(0).getDescription());
        assertEquals(2, serviceList.get(1).getId());
        assertEquals("Petit déjeuner inclus", serviceList.get(1).getDescription());
    }
}
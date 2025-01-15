package impulse.passerelle.domain.estates.lists;

import impulse.passerelle.domain.estates.elements.Activite;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ActivitesXmlTest {

    @Test
    void unmarshall_shouldConvertXmlToActivites() throws JAXBException {
        String xml = """
                <activites>
                    <activite>
                        <id>123</id>
                        <label>Restaurant</label>
                    </activite>
                    <activite>
                        <id>456</id>
                        <label>Hotel</label>
                    </activite>
                </activites>
                """;

        JAXBContext context = JAXBContext.newInstance(Activites.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Activites activites = (Activites) unmarshaller.unmarshal(new StringReader(xml));

        List<Activite> activiteList = activites.getElements();
        assertEquals(2, activiteList.size());
        assertEquals("123", activiteList.get(0).getId());
        assertEquals("Restaurant", activiteList.get(0).getLabel());
        assertEquals("456", activiteList.get(1).getId());
        assertEquals("Hotel", activiteList.get(1).getLabel());
    }
}


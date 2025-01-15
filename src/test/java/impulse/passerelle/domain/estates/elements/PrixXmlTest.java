package impulse.passerelle.domain.estates.elements;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.junit.jupiter.api.Test;

import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class PrixXmlTest {

    @Test
    void unmarshall_shouldConvertXmlToPrix() throws JAXBException {
        String xml = "<prix currency=\"EUR\">1000.0</prix>";

        JAXBContext context = JAXBContext.newInstance(Prix.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Prix prix = (Prix) unmarshaller.unmarshal(new StringReader(xml));

        assertEquals(1000.0, prix.getValeur());
        assertEquals("EUR", prix.getCurrency());
    }
}
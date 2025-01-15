package impulse.passerelle.domain.estates.elements;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;

class ChargeElementXmlTest {
    @Test
    void unmarshall_shouldConvertXmlToChargeElement() throws JAXBException {
        String xml = "<chargeElement><chargeElementName>Eau</chargeElementName><chargeElementValeur>50</chargeElementValeur><chargeElementRecuperable>true</chargeElementRecuperable></chargeElement>";

        JAXBContext context = JAXBContext.newInstance(ChargeElement.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        ChargeElement chargeElement = (ChargeElement) unmarshaller.unmarshal(new StringReader(xml));

        assertEquals("Eau", chargeElement.getName());
        assertEquals("50", chargeElement.getValeur());
        assertTrue(chargeElement.isRecuperable());
    }
}
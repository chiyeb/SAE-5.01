package impulse.passerelle.domain.estates.elements;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.junit.jupiter.api.Test;

import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class OptionXmlTest {

    @Test
    void unmarshall_shouldConvertXmlToOption() throws JAXBException {
        String xml = "<opt id=\"1\">" +
                "<label>Nom de l'option</label>" +
                "<prix currency=\"EUR\">99.99</prix>" +
                "<descriptif>Details de l'option</descriptif>" +
                "</opt>";

        JAXBContext context = JAXBContext.newInstance(Option.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Option option = (Option) unmarshaller.unmarshal(new StringReader(xml));

        assertEquals(1, option.getId());
        assertEquals("Nom de l'option", option.getLabel());
        assertEquals("99.99", option.getPrix().getValeur().toString());
        assertEquals("EUR", option.getPrix().getCurrency());
        assertEquals("Details de l'option", option.getDescriptif());
    }
}
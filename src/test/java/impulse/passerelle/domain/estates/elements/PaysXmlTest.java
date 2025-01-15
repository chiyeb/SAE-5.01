package impulse.passerelle.domain.estates.elements;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.junit.jupiter.api.Test;

import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class PaysXmlTest {

    @Test
    void unmarshall_shouldConvertXmlToPays() throws JAXBException {
        String xml = "<pays ISO=\"FR\"><![CDATA[France]]></pays>";

        JAXBContext context = JAXBContext.newInstance(Pays.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Pays pays = (Pays) unmarshaller.unmarshal(new StringReader(xml));

        assertEquals("FR", pays.getIso());
        assertEquals("France", pays.getNom());
    }
}
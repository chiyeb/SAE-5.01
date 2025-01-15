package impulse.passerelle.domain.estates.elements;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.junit.jupiter.api.Test;

import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class LangueXmlTest {

    @Test
    void unmarshall_shouldConvertXmlToLangue() throws JAXBException {
        String xml = "<langue code=\"fr\">" +
                "<titre><![CDATA[Titre de l'annonce en français]]></titre>" +
                "<corps><![CDATA[Corps de l'annonce en français]]></corps>" +
                "<corps_impression><![CDATA[Corps réduit pour impression]]></corps_impression>" +
                "</langue>";

        JAXBContext context = JAXBContext.newInstance(Langue.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Langue langue = (Langue) unmarshaller.unmarshal(new StringReader(xml));

        assertEquals("fr", langue.getCode());
        assertEquals("Titre de l'annonce en français", langue.getTitre());
        assertEquals("Corps de l'annonce en français", langue.getCorps());
        assertEquals("Corps réduit pour impression", langue.getCorps_impression());
    }
}
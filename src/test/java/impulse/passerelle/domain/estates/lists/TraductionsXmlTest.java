package impulse.passerelle.domain.estates.lists;

import impulse.passerelle.domain.estates.elements.Langue;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TraductionsXmlTest {

    @Test
    void unmarshall_shouldConvertXmlToTraductions() throws JAXBException {
        String xml = """
                <traductions>
                    <langue code="fr">
                        <titre><![CDATA[Titre de l'annonce en français]]></titre>
                        <corps><![CDATA[Corps de l'annonce en français]]></corps>
                        <corps_impression><![CDATA[Corps réduit pour impression]]></corps_impression>
                    </langue>
                    <langue code="en">
                        <titre><![CDATA[Title of the ad in English]]></titre>
                        <corps><![CDATA[Body of the ad in English]]></corps>
                        <corps_impression><![CDATA[Reduced body for printing]]></corps_impression>
                    </langue>
                </traductions>
                """;

        JAXBContext context = JAXBContext.newInstance(Traductions.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Traductions traductions = (Traductions) unmarshaller.unmarshal(new StringReader(xml));

        List<Langue> langueList = traductions.getElements();
        assertEquals(2, langueList.size());
        assertEquals("fr", langueList.get(0).getCode());
        assertEquals("Titre de l'annonce en français", langueList.get(0).getTitre());
        assertEquals("Corps de l'annonce en français", langueList.get(0).getCorps());
        assertEquals("Corps réduit pour impression", langueList.get(0).getCorps_impression());
        assertEquals("en", langueList.get(1).getCode());
        assertEquals("Title of the ad in English", langueList.get(1).getTitre());
        assertEquals("Body of the ad in English", langueList.get(1).getCorps());
        assertEquals("Reduced body for printing", langueList.get(1).getCorps_impression());
    }
}
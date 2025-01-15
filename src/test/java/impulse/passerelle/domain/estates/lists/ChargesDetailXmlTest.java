package impulse.passerelle.domain.estates.lists;

import impulse.passerelle.domain.estates.elements.ChargeElement;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChargesDetailXmlTest {

    @Test
    void unmarshall_shouldConvertXmlToChargesDetail() throws JAXBException {
        String xml = """
                <ChargesDetail>
                    <chargeElement>
                        <chargeElementName>Electricité</chargeElementName>
                        <chargeElementValeur>50</chargeElementValeur>
                        <chargeElementRecuperable>true</chargeElementRecuperable>
                    </chargeElement>
                    <chargeElement>
                        <chargeElementName>Eau</chargeElementName>
                        <chargeElementValeur>30</chargeElementValeur>
                        <chargeElementRecuperable>false</chargeElementRecuperable>
                    </chargeElement>
                </ChargesDetail>
                """;

        JAXBContext context = JAXBContext.newInstance(ChargesDetail.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        ChargesDetail chargesDetail = (ChargesDetail) unmarshaller.unmarshal(new StringReader(xml));

        List<ChargeElement> chargeElementList = chargesDetail.getElements();
        assertEquals(2, chargeElementList.size());
        assertEquals("Electricité", chargeElementList.get(0).getName());
        assertEquals("50", chargeElementList.get(0).getValeur());
        assertTrue(chargeElementList.get(0).isRecuperable());
        assertEquals("Eau", chargeElementList.get(1).getName());
        assertEquals("30", chargeElementList.get(1).getValeur());
        assertFalse(chargeElementList.get(1).isRecuperable());
    }
}

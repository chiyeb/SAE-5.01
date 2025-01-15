package impulse.passerelle.domain.estates.elements;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.junit.jupiter.api.Test;

import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class PeriodeXmlTest {

    @Test
    void unmarshall_shouldConvertXmlToPeriode() throws JAXBException {
        String xml = "<periode><start><![CDATA[2025-01-01]]></start><end><![CDATA[2025-12-31]]></end></periode>";

        JAXBContext context = JAXBContext.newInstance(Periode.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Periode periode = (Periode) unmarshaller.unmarshal(new StringReader(xml));

        assertEquals("2025-01-01", periode.getStart());
        assertEquals("2025-12-31", periode.getEnd());
    }
}

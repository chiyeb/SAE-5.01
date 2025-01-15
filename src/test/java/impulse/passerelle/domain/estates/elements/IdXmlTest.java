package impulse.passerelle.domain.estates.elements;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class IdXmlTest {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    void unmarshall_shouldConvertXmlToId() throws JAXBException {
        String xml = "<id dateEnr=\"2023-01-01\" dateMaj=\"2023-01-15\" mandateKey=\"ABC123\">1001</id>";
        JAXBContext context = JAXBContext.newInstance(Id.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Id id = (Id) unmarshaller.unmarshal(new StringReader(xml));


        assertEquals(1001, id.getId());
        assertEquals("2023-01-01", dateFormat.format(id.getDateEnr()));
        assertEquals("2023-01-15", dateFormat.format(id.getDateMaj()));
        assertEquals("ABC123", id.getMandateKey());
    }
}
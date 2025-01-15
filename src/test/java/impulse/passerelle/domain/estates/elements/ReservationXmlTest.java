package impulse.passerelle.domain.estates.elements;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.junit.jupiter.api.Test;

import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class ReservationXmlTest {

    @Test
    void unmarshall_shouldConvertXmlToReservation() throws JAXBException {
        String xml = "<reservation id=\"1\"><start>01/01/2025</start><end>07/01/2025</end></reservation>";

        JAXBContext context = JAXBContext.newInstance(Reservation.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Reservation reservation = (Reservation) unmarshaller.unmarshal(new StringReader(xml));

        assertEquals(1, reservation.getId());
        assertEquals("01/01/2025", reservation.getStart());
        assertEquals("07/01/2025", reservation.getEnd());
    }
}
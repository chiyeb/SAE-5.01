package impulse.passerelle.domain.estates.lists;

import impulse.passerelle.domain.estates.elements.Reservation;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReservationsXmlTest {

    @Test
    void unmarshall_shouldConvertXmlToReservations() throws JAXBException {
        String xml = """
                <reservations>
                    <reservation id="1">
                        <start>01/01/2025</start>
                        <end>07/01/2025</end>
                    </reservation>
                    <reservation id="2">
                        <start>10/01/2025</start>
                        <end>15/01/2025</end>
                    </reservation>
                </reservations>
                """;

        JAXBContext context = JAXBContext.newInstance(Reservations.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Reservations reservations = (Reservations) unmarshaller.unmarshal(new StringReader(xml));

        List<Reservation> reservationList = reservations.getElements();
        assertEquals(2, reservationList.size());
        assertEquals(1, reservationList.get(0).getId());
        assertEquals("01/01/2025", reservationList.get(0).getStart());
        assertEquals("07/01/2025", reservationList.get(0).getEnd());
        assertEquals(2, reservationList.get(1).getId());
        assertEquals("10/01/2025", reservationList.get(1).getStart());
        assertEquals("15/01/2025", reservationList.get(1).getEnd());
    }
}
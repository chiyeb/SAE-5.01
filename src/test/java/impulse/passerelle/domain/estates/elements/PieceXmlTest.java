package impulse.passerelle.domain.estates.elements;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.junit.jupiter.api.Test;

import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class PieceXmlTest {
    @Test
    void unmarshall_shouldConvertXmlToPiece() throws JAXBException {
        String xml = "<piece><type><![CDATA[Chambre]]></type><surface><![CDATA[20]]></surface><label><![CDATA[Chambre principale]]></label><description><![CDATA[Pièce calme avec vue]]></description><etage>1</etage></piece>";

        JAXBContext context = JAXBContext.newInstance(Piece.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Piece piece = (Piece) unmarshaller.unmarshal(new StringReader(xml));

        assertEquals("Chambre", piece.getType());
        assertEquals("20", piece.getSurface());
        assertEquals("Chambre principale", piece.getLabel());
        assertEquals("Pièce calme avec vue", piece.getDescription());
        assertEquals(1, piece.getEtage());
    }
}
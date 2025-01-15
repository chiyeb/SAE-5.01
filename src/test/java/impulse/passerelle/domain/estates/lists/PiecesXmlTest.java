package impulse.passerelle.domain.estates.lists;

import impulse.passerelle.domain.estates.elements.Piece;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PiecesXmlTest {

    @Test
    void unmarshall_shouldConvertXmlToPieces() throws JAXBException {
        String xml = """
                <pieces>
                    <piece>
                        <type><![CDATA[Chambre]]></type>
                        <surface><![CDATA[20]]></surface>
                        <label><![CDATA[Chambre principale]]></label>
                        <description><![CDATA[Pièce calme avec vue]]></description>
                        <etage>1</etage>
                    </piece>
                    <piece>
                        <type><![CDATA[Salon]]></type>
                        <surface><![CDATA[30]]></surface>
                        <label><![CDATA[Salon spacieux]]></label>
                        <description><![CDATA[Pièce lumineuse]]></description>
                        <etage>1</etage>
                    </piece>
                </pieces>
                """;

        JAXBContext context = JAXBContext.newInstance(Pieces.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Pieces pieces = (Pieces) unmarshaller.unmarshal(new StringReader(xml));

        List<Piece> pieceList = pieces.getElements();
        assertEquals(2, pieceList.size());
        assertEquals("Chambre", pieceList.get(0).getType());
        assertEquals("20", pieceList.get(0).getSurface());
        assertEquals("Chambre principale", pieceList.get(0).getLabel());
        assertEquals("Pièce calme avec vue", pieceList.get(0).getDescription());
        assertEquals(1, pieceList.get(0).getEtage());
        assertEquals("Salon", pieceList.get(1).getType());
        assertEquals("30", pieceList.get(1).getSurface());
        assertEquals("Salon spacieux", pieceList.get(1).getLabel());
        assertEquals("Pièce lumineuse", pieceList.get(1).getDescription());
        assertEquals(1, pieceList.get(1).getEtage());
    }
}
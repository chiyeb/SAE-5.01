package impulse.passerelle.domain.estates.lists;

import impulse.passerelle.domain.estates.elements.Loisir;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LoisirsXmlTest {

    @Test
    void unmarshall_shouldConvertXmlToLoisirs() throws JAXBException {
        String xml = """
                <loisirs>
                    <loisir id="1">Description du loisir 1</loisir>
                    <loisir id="2">Description du loisir 2</loisir>
                </loisirs>
                """;

        JAXBContext context = JAXBContext.newInstance(Loisirs.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Loisirs loisirs = (Loisirs) unmarshaller.unmarshal(new StringReader(xml));

        List<Loisir> loisirList = loisirs.getElements();
        assertEquals(2, loisirList.size());
        assertEquals(1, loisirList.get(0).getId());
        assertEquals("Description du loisir 1", loisirList.get(0).getDescription());
        assertEquals(2, loisirList.get(1).getId());
        assertEquals("Description du loisir 2", loisirList.get(1).getDescription());
    }
}
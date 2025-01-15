package impulse.passerelle.domain.estates.lists;

import impulse.passerelle.domain.estates.elements.Option;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OptionsXmlTest {

    @Test
    void unmarshall_shouldConvertXmlToOptions() throws JAXBException {
        String xml = """
                <opts>
                    <opt id="1">
                        <label>Option 1</label>
                        <prix currency="EUR">99.99</prix>
                        <descriptif>Description de l'option 1</descriptif>
                    </opt>
                    <opt id="2">
                        <label>Option 2</label>
                        <prix currency="USD">49.99</prix>
                        <descriptif>Description de l'option 2</descriptif>
                    </opt>
                </opts>
                """;

        JAXBContext context = JAXBContext.newInstance(Options.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Options options = (Options) unmarshaller.unmarshal(new StringReader(xml));

        List<Option> optionList = options.getElements();
        assertEquals(2, optionList.size());
        assertEquals(1, optionList.get(0).getId());
        assertEquals("Option 1", optionList.get(0).getLabel());
        assertEquals("EUR", optionList.get(0).getPrix().getCurrency());
        assertEquals(99.99, optionList.get(0).getPrix().getValeur());
        assertEquals("Description de l'option 1", optionList.get(0).getDescriptif());
        assertEquals(2, optionList.get(1).getId());
        assertEquals("Option 2", optionList.get(1).getLabel());
        assertEquals("USD", optionList.get(1).getPrix().getCurrency());
        assertEquals(49.99, optionList.get(1).getPrix().getValeur());
        assertEquals("Description de l'option 2", optionList.get(1).getDescriptif());
    }
}
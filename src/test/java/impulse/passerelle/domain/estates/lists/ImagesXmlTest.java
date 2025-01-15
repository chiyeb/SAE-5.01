package impulse.passerelle.domain.estates.lists;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.net.URL;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ImagesXmlTest {

    @Test
    void unmarshall_shouldConvertXmlToImages() throws JAXBException {
        String xml = """
                <images>
                    <image>http://example.com/image1.jpg</image>
                    <image>http://example.com/image2.jpg</image>
                </images>
                """;

        JAXBContext context = JAXBContext.newInstance(Images.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Images images = (Images) unmarshaller.unmarshal(new StringReader(xml));

        List<URL> imageList = images.getElements();
        assertEquals(2, imageList.size());
        assertEquals("http://example.com/image1.jpg", imageList.get(0).toString());
        assertEquals("http://example.com/image2.jpg", imageList.get(1).toString());
    }
}
package impulse.passerelle.domain.estates.lists;

import com.google.gson.annotations.JsonAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.net.URL;
import java.util.List;

@XmlRootElement(name = "images")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonAdapter(JsonObjetsSerializer.class)
public class Images extends Objets<URL>{
    @XmlElement(name = "image")
    private List<URL> images;

    @Override
    public List<URL> getElements() {
        return images;
    }
}

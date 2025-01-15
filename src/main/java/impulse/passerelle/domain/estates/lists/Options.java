package impulse.passerelle.domain.estates.lists;

import impulse.passerelle.domain.estates.elements.Option;
import com.google.gson.annotations.JsonAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "opts")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonAdapter(JsonObjetsSerializer.class)
public class Options extends Objets<Option> {
    @XmlElement(name = "opt")
    private List<Option> options = new ArrayList<>();

    public List<Option> getElements() {
        return options;
    }

    @Override
    public String toString() {
        return options.toString();
    }
}

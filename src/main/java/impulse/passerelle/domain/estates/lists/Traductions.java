package impulse.passerelle.domain.estates.lists;

import impulse.passerelle.domain.estates.elements.Langue;
import com.google.gson.annotations.JsonAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "traductions")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonAdapter(JsonObjetsSerializer.class)
public class Traductions extends Objets<Langue> {
    @XmlElement(name = "langue")
    private List<Langue> traductions = new ArrayList<>();

    public List<Langue> getElements() {
        return traductions;
    }

    @Override
    public String toString() {
        return traductions.toString();
    }
}

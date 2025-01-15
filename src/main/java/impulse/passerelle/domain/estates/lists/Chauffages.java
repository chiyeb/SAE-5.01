package impulse.passerelle.domain.estates.lists;

import impulse.passerelle.domain.estates.elements.Chauffage;
import com.google.gson.annotations.JsonAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "chauffages")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonAdapter(JsonObjetsSerializer.class)
public class Chauffages extends Objets<Chauffage>{
    @XmlElement(name = "chauffage")
    private List<Chauffage> chauffages = new ArrayList<>();

    public List<Chauffage> getElements() {
        return chauffages;
    }

    @Override
    public String toString() {
        return chauffages.toString();
    }
}

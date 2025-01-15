package impulse.passerelle.domain.estates.lists;

import impulse.passerelle.domain.estates.elements.Activite;
import com.google.gson.annotations.JsonAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "activites")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonAdapter(JsonObjetsSerializer.class)
public class Activites extends Objets<Activite>{
    @XmlElement(name = "activite")
    private List<Activite> activites = new ArrayList<>();

    public List<Activite> getElements() {
        return activites;
    }
}

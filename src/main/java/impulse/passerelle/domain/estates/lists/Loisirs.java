package impulse.passerelle.domain.estates.lists;

import impulse.passerelle.domain.estates.elements.Loisir;
import com.google.gson.annotations.JsonAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "loisirs")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonAdapter(JsonObjetsSerializer.class)
public class Loisirs extends Objets<Loisir>{
    @XmlElement(name = "loisir")
    private List<Loisir> loisirs = new ArrayList<>();

    public List<Loisir> getElements() {
        return loisirs;
    }

    @Override
    public String toString() {
        return loisirs.toString();
    }
}

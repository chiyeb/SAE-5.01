package impulse.passerelle.domain.estates.lists;

import impulse.passerelle.domain.estates.elements.Contrainte;
import com.google.gson.annotations.JsonAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "contraintes")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonAdapter(JsonObjetsSerializer.class)
public class Contraintes extends Objets<Contrainte>{
    @XmlElement(name = "contrainte")
    private List<Contrainte> contraintes = new ArrayList<>();

    public List<Contrainte> getElements() {
        return contraintes;
    }

    @Override
    public String toString() {
        return contraintes.toString();
    }
}

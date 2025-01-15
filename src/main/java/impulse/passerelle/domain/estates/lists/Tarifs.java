package impulse.passerelle.domain.estates.lists;

import impulse.passerelle.domain.estates.elements.Tarif;
import com.google.gson.annotations.JsonAdapter;
import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "tarifs")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonAdapter(JsonObjetsSerializer.class)
public class Tarifs extends Objets<Tarif>{
    @XmlElement(name = "tarif")
    private List<Tarif> tarifs = new ArrayList<>();

    public List<Tarif> getElements() {
        return tarifs;
    }
}

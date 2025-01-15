package impulse.passerelle.domain.estates.lists;

import impulse.passerelle.domain.estates.elements.ChargeElement;
import com.google.gson.annotations.JsonAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "ChargesDetail")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonAdapter(JsonObjetsSerializer.class)
public class ChargesDetail extends Objets<ChargeElement>{
    @XmlElement(name = "chargeElement")
    private List<ChargeElement> charges = new ArrayList<>();

    public List<ChargeElement> getElements() {
        return charges;
    }

    @Override
    public String toString() {
        return charges.toString();
    }
}

package impulse.passerelle.domain.estates.lists;

import impulse.passerelle.domain.estates.elements.Reservation;
import com.google.gson.annotations.JsonAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "reservations")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonAdapter(JsonObjetsSerializer.class)
public class Reservations extends Objets<Reservation>{
    @XmlElement(name = "reservation")
    private List<Reservation> reservations = new ArrayList<>();

    public List<Reservation> getElements() {
        return reservations;
    }

    @Override
    public String toString() {
        return reservations.toString();
    }
}

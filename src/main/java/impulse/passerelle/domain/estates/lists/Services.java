package impulse.passerelle.domain.estates.lists;

import impulse.passerelle.domain.estates.elements.Service;
import com.google.gson.annotations.JsonAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "services")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonAdapter(JsonObjetsSerializer.class)
public class Services extends Objets<Service> {
    @XmlElement(name = "service")
    private List<Service> services;

    @Override
    public List<Service> getElements() {
        return services;
    }
}

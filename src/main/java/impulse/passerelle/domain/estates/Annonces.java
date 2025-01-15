package impulse.passerelle.domain.estates;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "hektor")
public class Annonces {
    @XmlElement(name = "ad")
    private List<Annonce> annonces = new ArrayList<>();

    public List<Annonce> getElements() {
        return annonces;
    }

    @Override
    public String toString() {
        return annonces.toString();
    }
}

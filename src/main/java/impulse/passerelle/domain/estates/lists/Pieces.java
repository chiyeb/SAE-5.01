package impulse.passerelle.domain.estates.lists;

import impulse.passerelle.domain.estates.elements.Piece;
import com.google.gson.annotations.JsonAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "pieces")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonAdapter(JsonObjetsSerializer.class)
public class Pieces extends Objets<Piece>{
    @XmlElement(name = "piece")
    private List<Piece> pieces = new ArrayList<>();

    public List<Piece> getElements() {
        return pieces;
    }

    @Override
    public String toString() {
        return pieces.toString();
    }
}

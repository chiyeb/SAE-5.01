package impulse.passerelle.repositories.annonces;

import impulse.passerelle.domain.estates.elements.TypeBien;

import java.util.List;

public interface TypeBienRepository {
    List<TypeBien> getAllTypes();
    TypeBien getTypeByCode(int code);
    TypeBien getTypeByName(String name);
}

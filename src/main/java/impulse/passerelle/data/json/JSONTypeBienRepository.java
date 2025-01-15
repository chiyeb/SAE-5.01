package impulse.passerelle.data.json;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import impulse.passerelle.domain.estates.elements.TypeBien;
import impulse.passerelle.repositories.annonces.TypeBienRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.Arrays;
import java.util.List;

@Primary
@Repository
public class JSONTypeBienRepository implements TypeBienRepository {
    @Value("${type-bien.json.path}")
    private String jsonPath;

    private final Gson gson = new Gson();

    private JsonReader createJsonReader() throws FileNotFoundException {
        return new JsonReader(new FileReader(jsonPath));
    }

    @Override
    public List<TypeBien> getAllTypes() {
        try {
            TypeBien[] data = gson.fromJson(createJsonReader(), TypeBien[].class);
            return Arrays.asList(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public TypeBien getTypeByCode(int code) {
        try {
            TypeBien[] data = gson.fromJson(createJsonReader(), TypeBien[].class);
            return Arrays.stream(data).filter(typeBien -> typeBien.code() == code).findFirst().orElse(null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public TypeBien getTypeByName(String name) {
        try {
            TypeBien[] data = gson.fromJson(createJsonReader(), TypeBien[].class);
            return Arrays.stream(data).filter(typeBien -> typeBien.name().equals(name)).findFirst().orElse(null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}

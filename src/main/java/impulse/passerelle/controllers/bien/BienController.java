package impulse.passerelle.controllers.bien;

import com.google.gson.Gson;
import impulse.passerelle.repositories.annonces.TypeBienRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bien")
public class BienController {
    private final TypeBienRepository typeBienRepository;
    private final Gson gson;

    public BienController(TypeBienRepository typeBienRepository) {
        this.typeBienRepository = typeBienRepository;
        this.gson = new Gson();
    }

    @GetMapping(value = "/type/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAllTypes() {
        return gson.toJson(typeBienRepository.getAllTypes());
    }

    @GetMapping(value = "/type/get/{codeOrName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getTypeByCodeOrName(@PathVariable String codeOrName){
        try {
            int code = Integer.parseInt(codeOrName);
            String typeJSON = gson.toJson(typeBienRepository.getTypeByCode(code));
            if (!typeJSON.equals("null")) return typeJSON;
        } catch (NumberFormatException e) {}
        return gson.toJson(typeBienRepository.getTypeByName(codeOrName));
    }
}

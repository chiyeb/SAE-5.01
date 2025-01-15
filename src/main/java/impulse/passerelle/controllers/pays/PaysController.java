package impulse.passerelle.controllers.pays;

import com.google.gson.Gson;
import impulse.passerelle.repositories.annonces.PaysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pays")
public class PaysController {
    private final PaysRepository paysRepository;
    private final Gson gson;

    @Autowired
    public PaysController(PaysRepository paysRepository) {
        this.paysRepository = paysRepository;
        this.gson = new Gson();
    }

    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAllCountries() {
        return gson.toJson(paysRepository.getAllCountries());
    }

    @GetMapping(value = "/get/{nameOrIso}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getCountryByNameOrIso(@PathVariable String nameOrIso) {
        String paysJSON = gson.toJson(paysRepository.getCountryByIso(nameOrIso));
        if (!paysJSON.equals("null")) return paysJSON;
        return gson.toJson(paysRepository.getCountryByName(nameOrIso));
    }

}

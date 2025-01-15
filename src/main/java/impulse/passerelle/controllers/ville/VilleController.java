package impulse.passerelle.controllers.ville;

import com.google.gson.Gson;
import impulse.passerelle.domain.filtering.cities.CityFilterData;
import impulse.passerelle.repositories.annonces.VilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ville")
public class VilleController {
    private final VilleRepository villeRepository;
    private final Gson gson;

    @Autowired
    public VilleController(VilleRepository villeRepository) {
        this.villeRepository = villeRepository;
        this.gson = new Gson();
    }

    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAllCities(@RequestParam(required = false) String pays){
        CityFilterData filterData = new CityFilterData(pays);
        return gson.toJson(villeRepository.getAllCities(filterData));
    }

    @GetMapping(value = "/get/{postalCodeOrName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getCityByPostalCodeOrName(@PathVariable String postalCodeOrName) {
        String villeJSON = gson.toJson(villeRepository.getCityByPostalCode(postalCodeOrName));
        if (!villeJSON.equals("null")) return villeJSON;
        return gson.toJson(villeRepository.getCityByName(postalCodeOrName));
    }
}

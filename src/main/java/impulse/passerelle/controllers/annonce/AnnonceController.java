package impulse.passerelle.controllers.annonce;

import com.google.gson.GsonBuilder;
import com.google.gson.Gson;
import impulse.passerelle.domain.estates.Annonce;
import impulse.passerelle.domain.filtering.estates.EstateFilterData;
import impulse.passerelle.domain.sorting.estates.SortData;
import impulse.passerelle.repositories.annonces.AnnonceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/annonce")
public class AnnonceController {
    private final AnnonceRepository annonceRepository;

    private final Gson gson;

    @Autowired
    public AnnonceController(AnnonceRepository annonceRepository) {
        this.annonceRepository = annonceRepository;
        this.gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
    }

    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllAnnonces(
            @RequestParam(required = false) String pays,
            @RequestParam(required = false) String ville,
            @RequestParam(required = false) Integer type_bien,
            @RequestParam(required = false) Double max_budget,
            @RequestParam(required = false) Double surface,
            @RequestParam(required = false) Integer nombre_pieces,
            @RequestParam(required = false) Integer nombres_chambres,
            @RequestParam(required = false) Double habitable_surface_min,
            @RequestParam(required = false) Double habitable_surface_max,
            @RequestParam(required = false) String class_energie,
            @RequestParam(required = false) String class_climat,
            @RequestParam(required = false) Integer nb_salle_bain,
            @RequestParam(required = false) Boolean piscine,
            @RequestParam(required = false) String orientation,
            @RequestParam(required = false) String tri_valeur,
            @RequestParam(required = false) Boolean tri_croissant,
            @RequestParam(required = false) String  type_offre,
            @RequestParam(required = false) Boolean meuble
    ) {
        boolean tri_croissant_default = tri_croissant != null ? tri_croissant : true;
        SortData sortData = (tri_valeur != null) ?
                new SortData(tri_valeur, tri_croissant_default) :
                null;
        EstateFilterData filterData = new EstateFilterData(pays, ville, type_bien, max_budget, surface, nombre_pieces, nombres_chambres, habitable_surface_min, habitable_surface_max, class_energie, class_climat, nb_salle_bain, piscine, orientation, type_offre, meuble);
        return ResponseEntity.ok(gson.toJson(annonceRepository.getAllAnnonces(sortData, filterData)));
    }

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAnnonceById(@PathVariable int id) {
        Annonce annonce = annonceRepository.getAnnonceById(id);
        if (annonce != null) return ResponseEntity.ok(gson.toJson(annonceRepository.getAnnonceById(id)));
        else return ResponseEntity.notFound().build();
    }
}

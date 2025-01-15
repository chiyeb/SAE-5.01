package impulse.passerelle.domain.filtering.estates;

import impulse.passerelle.domain.estates.Annonce;

import java.util.List;
import java.util.Objects;

public record EstateFilterData(String pays, String code_postal, Integer type_bien, Double max_budget, Double surface,
                               Integer nombre_pieces, Integer nombres_chambres, Double habitable_surface_min, Double habitable_surface_max,
                               String class_energie, String class_climat, Integer nb_salle_bain, Boolean piscine, String orientation, String type_offre) {

    public List<Annonce> filter(List<Annonce> annonces) {
        return annonces.stream().filter(
                annonce -> (pays == null || annonce.getPays().getIso().equals(pays)) &&
                        (code_postal == null || annonce.getCp().equals(code_postal)) &&
                        (type_bien == null || Objects.equals(annonce.getTypeBienCode(), type_bien)) &&
                        (max_budget == null || annonce.getPrix() <= max_budget) &&
                        (surface == null || Objects.equals(annonce.getSurfaceHabitable(), surface)) &&
                        (nombre_pieces == null || Objects.equals(annonce.getNbPieces(), nombre_pieces)) &&
                        (nombres_chambres == null || Objects.equals(annonce.getChambres(), nombres_chambres)) &&
                        (habitable_surface_min == null || annonce.getSurfaceHabitable() >= habitable_surface_min) &&
                        (habitable_surface_max == null || annonce.getSurfaceHabitable() <= habitable_surface_max) &&
                        (class_energie == null || annonce.getClassEnergie().equals(class_energie)) &&
                        (class_climat == null || annonce.getClassEmissionGaz().equals(class_climat)) &&
                        (nb_salle_bain == null || Objects.equals(annonce.getSdb(), nb_salle_bain)) &&
                        (piscine == null || annonce.hasPiscine() == piscine) &&
                        (orientation == null || annonce.getExposition().contains(orientation)) &&
                        (type_offre == null || annonce.getTypeOffre().equals(type_offre))
        ).toList();
    }
}

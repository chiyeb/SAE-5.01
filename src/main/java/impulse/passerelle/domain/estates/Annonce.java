package impulse.passerelle.domain.estates;

import impulse.passerelle.domain.estates.elements.*;
import com.google.gson.annotations.SerializedName;
import impulse.passerelle.domain.estates.lists.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.net.URL;
import java.util.Date;

/**
 * Classe représentant un noeud XML représentant une annonce immobilière.
 */
@XmlRootElement(name = "ad")
@XmlAccessorType(XmlAccessType.FIELD)
public class Annonce {
    /** Identifiant unique de l'annonce. */
    @XmlElement(name = "id")
    private Id id;

    /** L’identifiant d’export référence client */
    @XmlElement(name = "reference_client")
    @SerializedName("reference_client")
    private String referenceClient;

    /** Titre de l'annonce. */
    @XmlElement(name = "titre")
    private String titre;

    /** Numéro du dossier. */
    @XmlElement(name = "reference")
    private String reference;

    /** Numéro de mandat. */
    @XmlElement(name = "numero_mandat")
    @SerializedName("numero_mandat")
    private String numeroMandat;

    /** Type d'annonce. */
    @XmlElement(name = "type_offre")
    @SerializedName("type_offre")
    private String typeOffre;

    /** Code du type d'annonce. */
    @XmlElement(name = "type_offre_code")
    @SerializedName("type_offre_code")
    private Integer typeOffreCode;

    /** Nom du négociateur. */
    @XmlElement(name = "negociateur")
    private String negociateur;

    /** Téléphone du négociateur. */
    @XmlElement(name = "tel_negociateur")
    @SerializedName("tel_negociateur")
    private String telNegociateur;

    /** Photo du négociateur. */
    @XmlElement(name = "photo_negociateur")
    @SerializedName("photo_negociateur")
    private URL photoNegociateur;

    /** Corps de l'annonce. */
    @XmlElement(name = "corps")
    private String corps;

    /** Corps de l'annonce réduite */
    @XmlElement(name = "corps_impression")
    @SerializedName("corps_impression")
    private String corpsImpression;

    /** Prix de l'annonce charges comprises */
    @XmlElement(name = "prix")
    private Double prix;

    /** Détail du prix pour les locations saisonnières. */
    @XmlElement(name = "detail_prix")
    @SerializedName("detail_prix")
    private DetailPrix detailPrix;

    /** Montant de l’état des lieux pour les types locations . */
    @XmlElement(name = "etatDeslieux")
    @SerializedName("etat_des_lieux")
    private Double etatDesLieux;

    /** Charges. */
    @XmlElement(name = "charge")
    private Double charge;

    /** Commentaire libre sur les charges. */
    @XmlElement(name = "chargesCommentaires")
    @SerializedName("charges_commentaires")
    private String chargesCommentaires;

    /** Type de charges pour les types locations. */
    @XmlElement(name = "chargesTypes")
    @SerializedName("type_charge")
    private Integer typeCharge;

    /** Détail des charges pour les locations. */
    @XmlElement(name = "ChargesDetail")
    @SerializedName("charges_detail")
    private ChargesDetail chargesDetail;

    /** Honoraires totaux. */
    @XmlElement(name = "honoraires")
    private Double honoraires;

    /** URL du barème des honoraires. */
    @XmlElement(name = "urlBaremeHono")
    @SerializedName("url_bareme_hono")
    private URL baremeHonoraires;

    /** Honoraires attribuées à
     * 1 : l'acquéreur
     * 2 : le vendeur
     * 3 : partagés entre l'acquéreur et le vendeur
     */
    @XmlElement(name = "honorairesAChargeDe")
    @SerializedName("honoraires_a_charge_de")
    private Double honorairesAChargeDe;

    /** Honoraires à la charge de l'acquéreur. */
    @XmlElement(name = "honorairesAcquereur")
    @SerializedName("honoraires_acquereur")
    private Double honorairesAcquereur;

    /** Honoraires à la charge du propriétaire. */
    @XmlElement(name = "honorairesMandant")
    @SerializedName("honoraires_mandant")
    private Double honorairesMandant;

    /** Pourcentage des honoraires à la charge de l'acquéreur, Ventes uniquement. */
    @XmlElement(name = "honorairesPourcentageAcquereur")
    @SerializedName("honoraires_pourcentage_acquereur")
    private Double honorairesPourcentageAcquereur;

    /** Prix net vendeur. */
    @XmlElement(name = "prixnetvendeur")
    @SerializedName("prix_net_vendeur")
    private Double prixNetVendeur;

    /** Montant des taxes foncières. */
    @XmlElement(name = "foncier")
    private Double foncier;

    /** URL de l'annonce. */
    @XmlElement(name = "url")
    private URL url;

    /** Type de mandat. (SIMPLE/ACCORD/EXCLUSIF) */
    @XmlElement(name = "type_mandat")
    @SerializedName("type_mandat")
    private String typeMandat;

    /** Type de bien. */
    @XmlElement(name = "type_bien")
    @SerializedName("type_bien")
    private String typeBien;

    /** Code du type de bien. */
    @XmlElement(name = "type_bien_code")
    @SerializedName("type_bien_code")
    private Integer typeBienCode;

    /** Surface habitable. */
    @XmlElement(name = "surface_habitable")
    @SerializedName("surface_habitable")
    private Double surfaceHabitable;

    /** Surface du terrain. */
    @XmlElement(name = "surface_terrain")
    @SerializedName("surface_terrain")
    private Double surfaceTerrain;

    /** Surface du séjour. */
    @XmlElement(name = "surface_sejour")
    @SerializedName("surface_sejour")
    private Double surfaceSejour;

    /** Nombre de pièces. */
    @XmlElement(name = "nb_piece")
    @SerializedName("nb_pieces")
    private Integer nbPieces;

    /** Nombre de chambres. */
    @XmlElement(name = "chambres")
    private Integer chambres;

    /** Nombre de couchages. */
    @XmlElement(name = "couchages")
    private Integer couchages;

    /** Nombre d'étages. */
    @XmlElement(name = "nb_etages")
    @SerializedName("nb_etages")
    private Integer nbEtages;

    /** N° de l'étage pour les appartements. */
    @XmlElement(name = "etage")
    private Integer etage;

    /** Nombre de salles de bain. */
    @XmlElement(name = "sdb")
    private Integer sdb;

    /** Nombre de salles d'eau. */
    @XmlElement(name = "sde")
    private Integer sde;

    /** Nombre de WC. */
    @XmlElement(name = "wc")
    private Integer wc;

    /** Type de cuisine. */
    @XmlElement(name = "cuisine")
    private String cuisine;

    /** Type de chauffage. */
    @XmlElement(name = "chauffage")
    private String chauffage;

    /** Présence d'un parking. */
    @XmlElement(name = "parking")
    private Boolean parking;

    /** Présence d'une piscine. */
    @XmlElement(name = "piscine")
    private Boolean piscine;

    /** Présence d'un balcon. */
    @XmlElement(name = "balcon")
    private Boolean balcon;

    /** Présence d'une terrasse. */
    @XmlElement(name = "terrasse")
    private Boolean terrasse;

    /** Exposition du bien. (NORD, SUD, SUD-EST...) */
    @XmlElement(name = "exposition")
    private String exposition;

    /** Environnement du bien. */
    @XmlElement(name = "environnement")
    private String environnement;

    /** Vue du bien. */
    @XmlElement(name = "vue")
    private String vue;

    /** Annonce de prestige. */
    @XmlElement(name = "prestige")
    private Boolean prestige;

    /** Consommation énergétique. */
    @XmlElement(name = "cons_energie")
    @SerializedName("cons_energie")
    private Double consEnergie;

    /** Classe énergie. */
    @XmlElement(name = "class_energie")
    @SerializedName("class_energie")
    private String classEnergie;

    /** Emission de gaz à effet de serre. */
    @XmlElement(name = "emission_gaz")
    @SerializedName("emission_gaz")
    private Double emissionGaz;

    /** Classe d'émission de gaz à effet de serre. */
    @XmlElement(name = "class_emission_gaz")
    @SerializedName("class_emission_gaz")
    private String classEmissionGaz;

    /* Si le bien est non concerné par le DPE. */
    @XmlElement(name = "dpe_non_concerne")
    @SerializedName("dpe_non_concerne")
    private Boolean dpeNonConcerne;

    /* Si le DPE est vierge. */
    @XmlElement(name = "dpe_vierge")
    @SerializedName("dpe_vierge")
    private Boolean dpeVierge;

    /** Pays du bien. */
    @XmlElement(name = "pays")
    private Pays pays;

    /** Ville du bien. */
    @XmlElement(name = "ville")
    private String ville;

    /** Adresse du bien. */
    @XmlElement(name = "adresse")
    private String adresse;

    /** Latitude du bien. */
    @XmlElement(name = "latitude")
    private Double latitude;

    /** Longitude du bien. */
    @XmlElement(name = "longitude")
    private Double longitude;

    /** Code postal de la ville. */
    @XmlElement(name = "cp")
    private String cp;

    /** Département de la ville. */
    @XmlElement(name = "departement")
    private String departement;

    /** Secteur de la ville. */
    @XmlElement(name = "secteur")
    private String secteur;

    /** Si le bien est un coup de cœur. */
    @XmlElement(name = "coup_de_coeur")
    @SerializedName("coup_de_coeur")
    private Boolean coupDeCoeur;

    /** Images de l'annonce. */
    @XmlElement(name = "images")
    private Images images; //TODO(Télécharger les images)

    /** URL de la visite virtuelle. */
    @XmlElement(name = "visite_virtuelle")
    @SerializedName("visite_virtuelle")
    private URL visiteVirtuelle;

    /** Traductions de l'annonce. */
    @XmlElement(name = "traductions")
    private Traductions traductions;

    /** Inclus les détails de la copropriété. */
    @XmlElement(name = "copropriete")
    private Double copropriete;

    /** N° du lot dans la copropriété. */
    @XmlElement(name = "copropriete_lot")
    @SerializedName("copropriete_lot")
    private Integer coproprieteLot;

    /** Nombre de lots dans la copropriété. */
    @XmlElement(name = "copropriete_nb_lot")
    @SerializedName("copropriete_nb_lot")
    private Integer coproprieteNbLot;

    /** Quote-part annuelle des charges du bien. */
    @XmlElement(name = "copropriete_quote_part")
    @SerializedName("copropriete_quote_part")
    private Integer coproprieteQuotePart;

    /** Si la copropriété est sous plan de sauvegarde. */
    @XmlElement(name = "copropriete_plan_sauvegarde")
    @SerializedName("copropriete_plan_sauvegarde")
    private Boolean coproprietePlanSauvegarde;

    /** Statut du syndicat de copropriété. */
    @XmlElement(name = "copropriete_statut_syndicat")
    @SerializedName("copropriete_statut_syndicat")
    private String coproprieteStatutSyndicat;

    /** Si le bien est meublé. */
    @XmlElement(name = "meuble")
    private Boolean meuble;

    /** Si le terrain est constructible. */
    @XmlElement(name = "Constructible")
    private Boolean constructible;

    /** Statut du lot. (LIBRE / LOUE / OPTION / RESERVE / ACTE / LIVRE)*/
    @XmlElement(name = "statutLot")
    @SerializedName("statut_lot")
    private String statutLot;

    /** Statut du bien. */
    @XmlElement(name = "status")
    private Integer status;

    /** Montant du dépôt de garantie. */
    @XmlElement(name = "depotGarantie")
    @SerializedName("depot_garantie")
    private Double depotGarantie;

    /** Chauffages du bien. */
    @XmlElement(name = "chauffages")
    private Chauffages chauffages;

    /** Date de la réalisation du diagnostic de performance énergétique. */
    @XmlElement(name = "date_diag_energie")
    @SerializedName("date_diag_energie")
    private String dateDiagEnergie;

    /**  Estimation minimum des coûts annuels d’énergie du logement */
    @XmlElement(name = "dpe_couts_min_estim")
    @SerializedName("dpe_couts_min_estim")
    private Double dpeCoutsMinEstim;

    /** Estimation maximum des coûts annuels d’énergie du logement */
    @XmlElement(name = "dpe_couts_max_estim")
    @SerializedName("dpe_couts_max_estim")
    private Double dpeCoutsMaxEstim;

    /** Annéee de référence pour l’estimation des coûts annuels d’énergie du logement */
    @XmlElement(name = "dpe_couts_ref_estim")
    @SerializedName("dpe_couts_ref_estim")
    private Integer dpeCoutsRefEstim;

    /** Information relative au DPE afin
     de savoir si un bien est situé à
     plus de 800 mètre d’altitude.*/
    @XmlElement(name = "haute_altitude")
    @SerializedName("haute_altitude")
    private Boolean hauteAltitude;

    /** Valeur de l’énergie finale du bien. */
    @XmlElement(name = "valeurEnergieFinale")
    @SerializedName("valeur_energie_finale")
    private Double valeurEnergieFinale;

    /** Activités à proximité du bien. */
    @XmlElement(name = "activites")
    private Activites activites;

    /** Le bien est-il concerné par un Etat des
     risques et Pollutions (ERP) ? */
    @XmlElement(name = "risques_pollution")
    @SerializedName("risques_pollution")
    private Boolean risquesPollution;

    /** Date d’établissement Etat des
     risques et Pollutions */
    @XmlElement(name = "erp_date")
    @SerializedName("erp_date")
    private String erpDate;

    /** Commentaire sur l’Etat des risques et Pollutions */
    @XmlElement(name="erp_commentaire")
    @SerializedName("erp_commentaire")
    private String erpCommentaire;

    /** Pièces du bien. */
    @XmlElement(name = "pieces")
    private Pieces pieces;

    /** Informations sur la location saisonnière. */
    @XmlElement(name = "ls")
    @SerializedName("location_saisonniere")
    private LocationSaisonniere locationSaisonniere;

    public Annonce() {}

    private Annonce(Id id, Double prix) {
        this.id = id;
        this.prix = prix;
    }

    private Annonce(String pays, String code_postal, Integer type_bien, Double prix, Double surface,
                    Integer nombre_pieces, Integer nombres_chambres, Double habitable_surface_min, Double habitable_surface_max,
                    String class_energie, String class_climat, Integer nb_salle_bain, Boolean piscine, String orientation, String type_offre) {
        this.pays = new Pays(pays,null);
        this.cp = code_postal;
        this.typeBienCode = type_bien;
        this.prix = prix;
        this.surfaceHabitable = surface;
        this.nbPieces = nombre_pieces;
        this.chambres = nombres_chambres;
        this.classEnergie = class_energie;
        this.classEmissionGaz = class_climat;
        this.sdb = nb_salle_bain;
        this.piscine = piscine;
        this.exposition = orientation;
        this.typeOffre = type_offre;
    }

    /** MUST NOT BE USED IN PRODUCTION CODE, ONLY TESTS */
    public static Annonce getAnnonceForSortingTest(Id id, Double prix) {
        return new Annonce(id, prix);
    }

    /** MUST NOT BE USED IN PRODUCTION CODE, ONLY TESTS */
    public static Annonce getAnnonceForFilteringTest(String pays, String code_postal, Integer type_bien, Double prix, Double surface,
                                                     Integer nombre_pieces, Integer nombres_chambres, Double habitable_surface_min, Double habitable_surface_max,
                                                     String class_energie, String class_climat, Integer nb_salle_bain, Boolean piscine, String orientation, String type_offre) {
        return new Annonce(pays, code_postal, type_bien, prix, surface, nombre_pieces, nombres_chambres, habitable_surface_min, habitable_surface_max, class_energie, class_climat, nb_salle_bain, piscine, orientation, type_offre);
    }

    public Id getId() {
        return id;
    }

    public Date getDateEnr() {
        return id.getDateEnr();
    }

    public String getReferenceClient() {
        return referenceClient;
    }

    public String getTitre() {
        return titre;
    }

    public String getReference() {
        return reference;
    }

    public String getNumeroMandat() {
        return numeroMandat;
    }

    public String getTypeOffre() {
        return typeOffre;
    }

    public Integer getTypeOffreCode() {
        return typeOffreCode;
    }

    public String getNegociateur() {
        return negociateur;
    }

    public String getTelNegociateur() {
        return telNegociateur;
    }

    public URL getPhotoNegociateur() {
        return photoNegociateur;
    }

    public String getCorps() {
        return corps;
    }

    public String getCorpsImpression() {
        return corpsImpression;
    }

    public Double getPrix() {
        return prix;
    }

    public DetailPrix getDetailPrix() {
        return detailPrix;
    }

    public Double getEtatDesLieux() {
        return etatDesLieux;
    }

    public Double getCharge() {
        return charge;
    }

    public String getChargesCommentaires() {
        return chargesCommentaires;
    }

    public Integer getTypeCharge() {
        return typeCharge;
    }

    public Double getHonoraires() {
        return honoraires;
    }

    public URL getBaremeHonoraires() {
        return baremeHonoraires;
    }

    public Double getHonorairesAChargeDe() {
        return honorairesAChargeDe;
    }

    public Double getHonorairesAcquereur() {
        return honorairesAcquereur;
    }

    public Double getHonorairesMandant() {
        return honorairesMandant;
    }

    public Double getHonorairesPourcentageAcquereur() {
        return honorairesPourcentageAcquereur;
    }

    public Double getPrixNetVendeur() {
        return prixNetVendeur;
    }

    public Double getFoncier() {
        return foncier;
    }

    public URL getUrl() {
        return url;
    }

    public String getTypeMandat() {
        return typeMandat;
    }

    public String getTypeBien() {
        return typeBien;
    }

    public Integer getTypeBienCode() {
        return typeBienCode;
    }

    public Double getSurfaceHabitable() {
        return surfaceHabitable;
    }

    public Double getSurfaceTerrain() {
        return surfaceTerrain;
    }

    public Double getSurfaceSejour() {
        return surfaceSejour;
    }

    public Integer getNbPieces() {
        return nbPieces;
    }

    public Integer getChambres() {
        return chambres;
    }

    public Integer getCouchages() {
        return couchages;
    }

    public Integer getNbEtages() {
        return nbEtages;
    }

    public Integer getEtage() {
        return etage;
    }

    public Integer getSdb() {
        return sdb;
    }

    public Integer getSde() {
        return sde;
    }

    public Integer getWc() {
        return wc;
    }

    public String getCuisine() {
        return cuisine;
    }

    public String getChauffage() {
        return chauffage;
    }

    public Boolean isParking() {
        return parking;
    }

    public Boolean hasPiscine() {
        return piscine;
    }

    public Boolean isBalcon() {
        return balcon;
    }

    public Boolean isTerrasse() {
        return terrasse;
    }

    public String getExposition() {
        return exposition;
    }

    public String getEnvironnement() {
        return environnement;
    }

    public String getVue() {
        return vue;
    }

    public Boolean isPrestige() {
        return prestige;
    }

    public Double getConsEnergie() {
        return consEnergie;
    }

    public String getClassEnergie() {
        return classEnergie;
    }

    public Double getEmissionGaz() {
        return emissionGaz;
    }

    public String getClassEmissionGaz() {
        return classEmissionGaz;
    }

    public Boolean isDpeNonConcerne() {
        return dpeNonConcerne;
    }

    public Boolean isDpeVierge() {
        return dpeVierge;
    }

    public Pays getPays() {
        return pays;
    }

    public String getVille() {
        return ville;
    }

    public Ville getVilleAsClass() {
        return new Ville(cp,ville,pays);
    }

    public String getAdresse() {
        return adresse;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public String getCp() {
        return cp;
    }

    public String getDepartement() {
        return departement;
    }

    public String getSecteur() {
        return secteur;
    }

    public Boolean isCoupDeCoeur() {
        return coupDeCoeur;
    }

    public Images getImages() {
        return images;
    }

    public URL getVisiteVirtuelle() {
        return visiteVirtuelle;
    }

    public Traductions getTraductions() { return traductions; }

    public Double getCopropriete() {
        return copropriete;
    }

    public Integer getCoproprieteLot() {
        return coproprieteLot;
    }

    public Integer getCoproprieteNbLot() {
        return coproprieteNbLot;
    }

    public Integer getCoproprieteQuotePart() {
        return coproprieteQuotePart;
    }

    public Boolean isCoproprietePlanSauvegarde() {
        return coproprietePlanSauvegarde;
    }

    public String getCoproprieteStatutSyndicat() {
        return coproprieteStatutSyndicat;
    }

    public Boolean isMeuble() {
        return meuble;
    }

    public Boolean isConstructible() {
        return constructible;
    }

    public String getStatutLot() {
        return statutLot;
    }

    public Integer getStatus() {
        return status;
    }

    public Double getDepotGarantie() {
        return depotGarantie;
    }

    public Chauffages getChauffages() {
        return chauffages;
    }

    public ChargesDetail getChargesDetail() {
        return chargesDetail;
    }

    public Boolean getParking() {
        return parking;
    }

    public Boolean getPiscine() {
        return piscine;
    }

    public Boolean getBalcon() {
        return balcon;
    }

    public Boolean getTerrasse() {
        return terrasse;
    }

    public Boolean getPrestige() {
        return prestige;
    }

    public Boolean getDpeNonConcerne() {
        return dpeNonConcerne;
    }

    public Boolean getDpeVierge() {
        return dpeVierge;
    }

    public Boolean getCoupDeCoeur() {
        return coupDeCoeur;
    }

    public Boolean getCoproprietePlanSauvegarde() {
        return coproprietePlanSauvegarde;
    }

    public Boolean getMeuble() {
        return meuble;
    }

    public Boolean getConstructible() {
        return constructible;
    }

    public String getDateDiagEnergie() {
        return dateDiagEnergie;
    }

    public Double getDpeCoutsMinEstim() {
        return dpeCoutsMinEstim;
    }

    public Double getDpeCoutsMaxEstim() {
        return dpeCoutsMaxEstim;
    }

    public Integer getDpeCoutsRefEstim() {
        return dpeCoutsRefEstim;
    }

    public Boolean getHauteAltitude() {
        return hauteAltitude;
    }

    public Double getValeurEnergieFinale() {
        return valeurEnergieFinale;
    }

    public Activites getActivites() {
        return activites;
    }

    public Boolean getRisquesPollution() {
        return risquesPollution;
    }

    public String getErpDate() {
        return erpDate;
    }

    public String getErpCommentaire() {
        return erpCommentaire;
    }

    public Pieces getPieces() {
        return pieces;
    }

    public LocationSaisonniere getLocationSaisonniere() {
        return locationSaisonniere;
    }

    @Override
    public String toString() {
        return "Annonce{" +
                "id=" + id +
                ", referenceClient='" + referenceClient + '\'' +
                ", titre='" + titre + '\'' +
                ", reference='" + reference + '\'' +
                ", numeroMandat='" + numeroMandat + '\'' +
                ", typeOffre='" + typeOffre + '\'' +
                ", typeOffreCode=" + typeOffreCode +
                ", negociateur='" + negociateur + '\'' +
                ", telNegociateur='" + telNegociateur + '\'' +
                ", photoNegociateur=" + photoNegociateur +
                ", corps='" + corps + '\'' +
                ", corpsImpression='" + corpsImpression + '\'' +
                ", prix=" + prix +
                ", detailPrix=" + detailPrix +
                ", etatDesLieux=" + etatDesLieux +
                ", charge=" + charge +
                ", chargesCommentaires='" + chargesCommentaires + '\'' +
                ", typeCharge=" + typeCharge +
                ", chargesDetail=" + chargesDetail +
                ", honoraires=" + honoraires +
                ", baremeHonoraires=" + baremeHonoraires +
                ", honorairesAChargeDe=" + honorairesAChargeDe +
                ", honorairesAcquereur=" + honorairesAcquereur +
                ", honorairesMandant=" + honorairesMandant +
                ", honorairesPourcentageAcquereur=" + honorairesPourcentageAcquereur +
                ", prixNetVendeur=" + prixNetVendeur +
                ", foncier=" + foncier +
                ", url=" + url +
                ", typeMandat='" + typeMandat + '\'' +
                ", typeBien='" + typeBien + '\'' +
                ", typeBienCode=" + typeBienCode +
                ", surfaceHabitable=" + surfaceHabitable +
                ", surfaceTerrain=" + surfaceTerrain +
                ", surfaceSejour=" + surfaceSejour +
                ", nbPieces=" + nbPieces +
                ", chambres=" + chambres +
                ", couchages=" + couchages +
                ", nbEtages=" + nbEtages +
                ", etage=" + etage +
                ", sdb=" + sdb +
                ", sde=" + sde +
                ", wc=" + wc +
                ", cuisine='" + cuisine + '\'' +
                ", chauffage='" + chauffage + '\'' +
                ", parking=" + parking +
                ", piscine=" + piscine +
                ", balcon=" + balcon +
                ", terrasse=" + terrasse +
                ", exposition='" + exposition + '\'' +
                ", environnement='" + environnement + '\'' +
                ", vue='" + vue + '\'' +
                ", prestige=" + prestige +
                ", consEnergie=" + consEnergie +
                ", classEnergie='" + classEnergie + '\'' +
                ", emissionGaz=" + emissionGaz +
                ", classEmissionGaz='" + classEmissionGaz + '\'' +
                ", dpeNonConcerne=" + dpeNonConcerne +
                ", dpeVierge=" + dpeVierge +
                ", pays=" + pays +
                ", ville='" + ville + '\'' +
                ", adresse='" + adresse + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", cp='" + cp + '\'' +
                ", departement='" + departement + '\'' +
                ", secteur='" + secteur + '\'' +
                ", coupDeCoeur=" + coupDeCoeur +
                ", images=" + images +
                ", visiteVirtuelle=" + visiteVirtuelle +
                ", traductions=" + traductions +
                ", copropriete=" + copropriete +
                ", coproprieteLot=" + coproprieteLot +
                ", coproprieteNbLot=" + coproprieteNbLot +
                ", coproprieteQuotePart=" + coproprieteQuotePart +
                ", coproprietePlanSauvegarde=" + coproprietePlanSauvegarde +
                ", coproprieteStatutSyndicat='" + coproprieteStatutSyndicat + '\'' +
                ", meuble=" + meuble +
                ", constructible=" + constructible +
                ", statutLot='" + statutLot + '\'' +
                ", status=" + status +
                ", depotGarantie=" + depotGarantie +
                ", chauffages=" + chauffages +
                ", dateDiagEnergie='" + dateDiagEnergie + '\'' +
                ", dpeCoutsMinEstim=" + dpeCoutsMinEstim +
                ", dpeCoutsMaxEstim=" + dpeCoutsMaxEstim +
                ", dpeCoutsRefEstim=" + dpeCoutsRefEstim +
                ", hauteAltitude=" + hauteAltitude +
                ", valeurEnergieFinale=" + valeurEnergieFinale +
                ", activites=" + activites +
                ", risquesPollution='" + risquesPollution + '\'' +
                ", erpDate='" + erpDate + '\'' +
                ", erpCommentaire='" + erpCommentaire + '\'' +
                ", pieces=" + pieces +
                ", locationSaisonniere=" + locationSaisonniere +
                '}';
    }
}

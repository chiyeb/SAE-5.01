<?php
/**
 * Plugin Name: Plugin Property API
 * Description: Plugin permettant de récupérer (ou de simuler) des propriétés depuis une API, avec un carrousel d’images et différentes fonctionnalités de recherche et de filtrage.
 * Version: 1.12
 * Author: Chiheb
 */

/**
 *
 * /!\ Informations /!\
 * Certains éléments de la documentation / des commentaires ont été générés par une intelligence artificiel,
 * les éléments principaux / importants ont été commentés par nos soins.
 */


// Sécurité : empêche l'accès direct au plugin en dehors de WordPress
if ( ! defined( 'ABSPATH' ) ) {
    exit;
}

// Inclusion de fichiers externes qui contiennent les classes nécessaires pour récupérer les données
require_once 'get_estate.php';
require_once 'get_estate_type.php';
require_once 'get_city.php';
require_once plugin_dir_path(__FILE__) . 'favorites_handler.php';
require_once plugin_dir_path(__FILE__) . 'head_handler.php';


/**
 * Classe principale du plugin qui gère l’affichage des biens immobiliers sous forme de shortcodes,
 * ainsi que l’enqueue des scripts et styles nécessaires.
 */
class DisplayEstate {
    private $getEstate;

    /**
     * Constructeur de la classe
     * @param string $url url de l'api utilisé pour afficher les biens
     */
    public function __construct($url) {
        // Initialisation de la variable privée
        $this->getEstate = new GetEstate($url);
    }


    /**
     * Shortcode : [list_properties country="FR"]
     *
     * Affiche sur la page d’accueil (ou n’importe quelle page utilisant ce shortcode)
     * une liste de biens (3 biens maximum).
     *
     * @param array $atts Attributs du shortcode : 'country' (par défaut = 'FR').
     * @return string Retourne le code HTML généré.
     */
    public function shortcode_home_estate($atts) {
        // Fusionne les attributs passés au shortcode avec les valeurs par défaut
        $atts = shortcode_atts(
            array(
                'country' => 'FR',
            ),
            $atts,
            'list_properties'
        );

        // Prépare l’URL pour récupérer les biens
        $_GET['pays'] = $atts['country'];
        $url = "https://impulsepasserelle.alwaysdata.net/annonce/get";

        // Instancie la classe GetEstate et récupère la liste des biens (3 biens max)

        $allListings = array_slice($this->getEstate->get_estate_by_country($atts['country']), 0, 3);

        $country = $atts['country'];

        // Si pas de biens trouvés
        if ( ! is_array($allListings) || empty($allListings) ) {
            return "<p> Aucun biens en vente en " . esc_html($country) . ".</p>";
        }

        // Démarre la mise en tampon de sortie pour construire le HTML
        ob_start();
        ?>
        <div class="property-list">
            <?php foreach ( $allListings as $property ) : ?>
                <div class="property-item">

                    <!-- Carrousel d’images pour chaque bien -->
                    <?php if ( ! empty( $property['images'] ) && is_array( $property['images'] ) ) : ?>
                        <div class="property-carousel swiper">
                            <div class="swiper-wrapper">
                                <?php foreach ( $property['images'] as $image ) : ?>
                                    <div class="swiper-slide">
                                        <img src="<?php echo esc_url($image); ?>" alt="<?php echo esc_attr($property['titre']); ?>">
                                    </div>
                                <?php endforeach; ?>
                            </div>
                            <div class="swiper-button-next"></div>
                            <div class="swiper-button-prev"></div>
                            <div class="swiper-pagination"></div>
                        </div>
                    <?php endif; ?>

                    <!-- Informations principales du bien -->
                    <div class="property-content">
                        <h3 class="property-title">
                            <?php echo esc_html($property['titre']); ?>
                        </h3>

                        <p class="property-description">
                            <?php
                            // Nettoyage du texte pour éviter les tags HTML indésirables
                            echo esc_html(
                                ltrim(
                                    rtrim(
                                        trim(
                                            strip_tags(
                                                html_entity_decode(
                                                    $property['corps_impression'] ?? substr($property['corps'], 0, 80) . '...'
                                                )
                                            )
                                        ),
                                        '></'
                                    ),
                                    '></'
                                )
                            );
                            ?>
                        </p>

                        <p class="property-price">
                            <?php echo esc_html($property['prix'] . "€"); ?>
                        </p>

                        <p class="property-location">
                            <?php echo esc_html($property['ville'] . ', ' . $property['pays']['nom']); ?>
                        </p>

                        <p class="property-type">
                            <?php echo esc_html($property['type_bien']); ?>
                        </p>

                        <!-- Lien vers la page du bien -->
                        <a href="<?php echo '/index.php/bien?id=' . htmlspecialchars($property['id']['id'], ENT_QUOTES, 'UTF-8'); ?>">
                            <i class="fa-solid fa-eye"></i>
                        </a>
                        <a class="fav-btns" data-info="<?php echo htmlspecialchars($property['id']['id'],ENT_QUOTES, 'UTF-8'); ?>">

                        </a>
                    </div>
                </div>
            <?php endforeach; ?>
        </div>
        <?php
        // Récupère le contenu du tampon et le retourne
        return ob_get_clean();
    }

    /**
     * Shortcode : [estate_page id="1234"]
     *
     * Affiche la page d’un seul bien immobilier en fonction de l’ID fourni.
     *
     * @param array $atts Attributs du shortcode : 'id' (par défaut = 000).
     * @return string Retourne le code HTML généré.
     */
    public function shortcode_estate_page($atts) {
        // Fusionne les attributs passés au shortcode avec les valeurs par défaut
        $atts = shortcode_atts(
            array(
                'id' => 000,
            ),
            $atts,
            'estate_page'
        );

        // Prépare l’URL pour récupérer les détails du bien
        $url = "https://impulsepasserelle.alwaysdata.net/annonce/get";
        $property = $this->getEstate->get_estate_by_id($atts['id']);

        // Vérification du format des données renvoyées
        if ( ! is_array($property) || empty($property) ) {
            return "<p>Error with the listing</p>";
        }

        // Démarrage du buffer pour construire le HTML
        ob_start();
        ?>
        <h1 id="property-page-title">
            <?php echo esc_html($property['titre']); ?>
        </h1>
        <div class="single-property">

            <!-- Carrousel d’images du bien -->
            <?php if ( ! empty($property['images']) && is_array($property['images']) ) : ?>
                <div class="single-property-carousel swiper">
                    <div class="swiper-wrapper">
                        <?php foreach ( $property['images'] as $image ) : ?>
                            <div class="swiper-slide">
                                <img src="<?php echo esc_url($image); ?>" alt="<?php echo esc_attr($property['titre']); ?>">
                            </div>
                        <?php endforeach; ?>
                    </div>
                    <div class="swiper-button-next"></div>
                    <div class="swiper-button-prev"></div>
                    <div class="swiper-pagination"></div>
                </div>
            <?php endif; ?>

            <!-- Informations principales -->
            <div class="single-property-content">
                <h3 class="single-property-title">
                    <?php echo esc_html($property['titre']); ?>
                </h3>

                <p class="single-property-body">
                    <?php
                    // Nettoyage du texte pour éviter les tags HTML indésirables
                    echo esc_html(
                        ltrim(
                            rtrim(
                                trim(
                                    strip_tags(
                                        html_entity_decode($property['corps'] ?? '')
                                    )
                                )
                            ),
                            '></'
                        ),
                        '></'
                    );
                    ?>
                </p>

                <p class="single-property-price">
                    <?php echo esc_html($property['prix'] ?? '') . "€"; ?>
                </p>

                <p class="single-property-location">
                    <?php echo esc_html(($property['ville'] ?? '') . ', ' . ($property['pays']['nom'] ?? '')); ?>
                </p>

                <p class="single-property-type">
                    <?php echo esc_html($property['type_bien'] ?? ''); ?>
                </p>
            </div>
        </div>

        <!-- Section Caractéristiques supplémentaires du bien -->
        <div class="property-info">
            <h2 class="property-info-title">Caractéristiques :</h2>

            <div class="property-info-grid">
                <?php if (!empty($property['surface_habitable'])): ?>
                    <div class="property-info-item">
                        <strong>Surface habitable :</strong>
                        <?php echo esc_html($property['surface_habitable']); ?>
                    </div>
                <?php endif; ?>

                <?php if (!empty($property['chambres'])): ?>
                    <div class="property-info-item">
                        <strong>Chambres :</strong>
                        <?php echo esc_html($property['chambres']); ?>
                    </div>
                <?php endif; ?>

                <?php if (!empty($property['piscine'])): ?>
                    <div class="property-info-item">
                        <strong>Piscine :</strong>
                        <?php echo esc_html($property['piscine']); ?>
                    </div>
                <?php endif; ?>

                <?php if (!empty($property['terrain_surface'])): ?>
                    <div class="property-info-item">
                        <strong>Surface terrain :</strong>
                        <?php echo esc_html($property['terrain_surface']); ?>
                    </div>
                <?php endif; ?>

                <?php if (!empty($property['exposition'])): ?>
                    <div class="property-info-item">
                        <strong>Orientation :</strong>
                        <?php echo esc_html($property['exposition']); ?>
                    </div>
                <?php endif; ?>

                <?php if (!empty($property['vue'])): ?>
                    <div class="property-info-item">
                        <strong>Vue :</strong>
                        <?php echo esc_html($property['vue']); ?>
                    </div>
                <?php endif; ?>
            </div>

            <!-- Diagnostic de performance énergétique -->
            <h2 class="property-info-title">Diagnostic de performance énergétique</h2>
            <div class="property-classes-grid">
                <?php if (!empty($property['class_energie'])): ?>
                    <div class="class-container">
                        <div class="class-card">
                            <h3 class="property-h3-title">Classe énergie</h3>
                            <div class="property-energy-class property-energy-<?php echo esc_attr($property['class_energie']); ?>">
                                <p class="property-energy-class-text"><?php echo esc_html($property['class_energie']); ?></p>
                            </div>
                        </div>
                    </div>
                <?php endif; ?>

                <?php if (!empty($property['class_emission_gaz'])): ?>
                    <div class="class-container">
                        <div class="class-card">
                            <h3 class="property-h3-title">Classe climat</h3>
                            <div class="property-climate-class property-climate-<?php echo esc_attr($property['class_emission_gaz']); ?>">
                                <p class="property-climate-class-text"><?php echo esc_html($property['class_emission_gaz']); ?></p>
                            </div>
                        </div>
                    </div>
                <?php endif; ?>
            </div>

            <!-- Carte Leaflet si latitude et longitude sont disponibles -->
            <h2 class="property-info-title">Location</h2>
            <?php if (!empty($property['latitude']) && !empty($property['longitude'])): ?>
                <div id="property-map" style="height: 400px;"></div>

                <script>
                    document.addEventListener('DOMContentLoaded', function() {
                        var lat = <?php echo esc_js($property['latitude']); ?>;
                        var lng = <?php echo esc_js($property['longitude']); ?>;

                        // Initialisation de la carte Leaflet
                        var map = L.map('property-map').setView([lat, lng], 13);

                        L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
                            attribution: '&copy; OpenStreetMap contributors'
                        }).addTo(map);

                        // Ajout d'un marqueur à la position du bien
                        L.marker([lat, lng]).addTo(map)
                            .bindPopup('<?php echo esc_js($property['titre']); ?>')
                            .openPopup();
                    });
                </script>
            <?php endif; ?>

            <!-- Bouton de contact -->
            <h2 class="property-info-title">Intéréssé par le bien ?</h2>
            <a id="fav-btn-single-property" class="fav-btns" data-info="<?php echo htmlspecialchars($property['id']['id'],ENT_QUOTES, 'UTF-8'); ?>">
                Mettre le bien en favoris !
            </a>
            <a id="contact-btn-single-property" href="https://impulsewordpresssae.alwaysdata.net/index.php/contact-3/">
                Nous contacter !
            </a>
        </div>
        <?php
        // Retourne le HTML
        return ob_get_clean();
    }

    /**
     * Shortcode : [properties_pagination country="FR" offer_type="Vente"]
     *
     * Gère l'affichage paginé des biens immobiliers pour un pays et un type d'offre donnés.
     * Possibilité de filtrer les biens en fonction d’autres critères (via GET).
     *
     * @param array $atts Attributs du shortcode : 'country' => 'FR', 'offer_type' => 'Vente'.
     * @return string Retourne le code HTML généré, incluant la pagination.
     */
    public function estate_paging_shortcode($atts) {
        // Fusionne les attributs passés au shortcode avec les valeurs par défaut
        $atts = shortcode_atts(array(
            'country' => 'FR',
            'offer_type' => 'Vente',
        ), $atts, 'properties_pagination');

        // Attributs utilisés dans $_GET pour filtrer
        $_GET['type_offre'] = $atts['offer_type'];
        if (isset($atts['meuble'])) $_GET['meuble'] = $atts['meuble'];
        $url = "https://impulsepasserelle.alwaysdata.net/annonce/get";

        // Récupère la liste des biens filtrés par pays
        $allListings = $this->getEstate->get_estate_by_country($atts['country'], $_GET);

        $country = $atts['country'];
        $items_per_page = 10; // Nombre de biens par page
        $total_pages    = ceil(count($allListings) / $items_per_page);

        // Commence la mise en tampon
        ob_start();

        // Affiche le formulaire de recherche / filtrage
        $this->search_tab($atts['country']);

        // Si aucun bien
        if (empty($allListings)) {
            return '<p>Aucun bien en vente en ' . esc_html($country) . '.</p>';
        }
        ?>
        <div class="paginated-properties-list">
            <!-- Boucle sur toutes les pages -->
            <?php for ($i = 0; $i < $total_pages; $i++) : ?>
                <div class="property-page"
                     id="property-page-<?php echo $i + 1; ?>"
                     style="display: <?php echo $i === 0 ? 'block' : 'none'; ?>;">
                    <div class="properties-list">
                        <?php
                        // Gestion de l’offset pour récupérer les biens de la page en cours
                        $offset = $i * $items_per_page;
                        $pagedProperties = array_slice($allListings, $offset, $items_per_page);

                        // Affiche chaque bien
                        foreach ($pagedProperties as $property) : ?>
                            <div class="property-card">
                                <!-- Carrousel d’images du bien -->
                                <?php if (!empty($property['images']) && is_array($property['images'])) : ?>
                                    <div class="property-carousel swiper">
                                        <div class="swiper-wrapper">
                                            <?php foreach ($property['images'] as $image) : ?>
                                                <div class="swiper-slide">
                                                    <img src="<?php echo esc_url($image); ?>" alt="<?php echo esc_attr($property['titre']); ?>">
                                                </div>
                                            <?php endforeach; ?>
                                        </div>
                                        <div class="swiper-button-next"></div>
                                        <div class="swiper-button-prev"></div>
                                        <div class="swiper-pagination"></div>
                                    </div>
                                <?php endif; ?>

                                <!-- Informations principales du bien -->
                                <div class="property-details">
                                    <h3 class="property-title">
                                        <?php echo esc_html($property['titre']); ?>
                                    </h3>
                                    <p class="property-description">
                                        <?php
                                        // Nettoyage du texte pour une présentation propre
                                        echo esc_html(
                                            ltrim(
                                                rtrim(
                                                    trim(
                                                        strip_tags(
                                                            html_entity_decode(
                                                                $property['corps_impression'] ?? substr($property['corps'], 0, 80) . '...'
                                                            )
                                                        )
                                                    ),
                                                    '></'
                                                ),
                                                '></'
                                            )
                                        );
                                        ?>
                                    </p>
                                    <p class="property-price">
                                        <?php echo esc_html($property['prix'] . "€"); ?>
                                    </p>
                                    <p class="property-location">
                                        <?php echo esc_html($property['ville'] . ', ' . $property['pays']['nom']); ?>
                                    </p>
                                    <p class="property-type">
                                        <?php echo esc_html($property['type_bien']); ?>
                                    </p>

                                    <!-- Lien vers la page du bien -->
                                    <a href="<?php echo '/index.php/bien?id=' . htmlspecialchars($property['id']['id'], ENT_QUOTES, 'UTF-8'); ?>">
                                        <i class="fa-solid fa-eye"></i>
                                    </a>
                                    <a class="fav-btns" data-info="<?php echo htmlspecialchars($property['id']['id'],ENT_QUOTES, 'UTF-8'); ?>">
                                        Ajouté le bien en favoris
                                    </a>
                                </div>
                            </div>
                        <?php endforeach; ?>
                    </div>
                </div>
            <?php endfor; ?>
        </div>

        <!-- Pagination -->
        <div class="pagination-container">
            <button class="pagination-prev" disabled>&laquo; Previous</button>
            <div class="pagination-numbers">
                <?php for ($i = 0; $i < $total_pages; $i++) : ?>
                    <button class="pagination-number" data-page="<?php echo $i + 1; ?>">
                        <?php echo $i + 1; ?>
                    </button>
                <?php endfor; ?>
            </div>
            <button class="pagination-next">&raquo; Next</button>
        </div>
        <?php
        // Retourne le contenu généré
        return ob_get_clean();
    }

    /**
     * Affiche l’onglet de recherche/filtrage (formulaire) au-dessus de la liste des biens.
     * Méthode interne appelée dans estate_paging_shortcode().
     *
     * @param string $country Le pays (ex. "FR").
     * @return void
     */
    public function search_tab($country)
    {
        // URL des différentes API pour récupérer les listes de villes et de types de biens
        $cityUrl = "https://impulsepasserelle.alwaysdata.net/ville/get";
        $estatTypeUrl = "https://impulsepasserelle.alwaysdata.net/bien/type/get";

        // Instanciation des objets pour récupérer les données
        $getCity = new GetCity($cityUrl);
        $getEstateType = new GetEstateType($estatTypeUrl);

        // Récupération des listes de villes et de types de biens
        $cities = $getCity->get_all_cities();
        $estateTypes = $getEstateType->get_all_estate_types();
        ?>
        <!-- Formulaire de filtrage des biens -->
        <form class="biens-filtre-form" action="#" method="get">
            <input type="hidden" name="pays" value="<?php echo esc_attr($country); ?>">
            <div class="ligne">
                <div>
                    <label for="type_bien">Type de bien :</label>
                    <select id="type_bien" name="type_bien">
                        <option value=""></option>
                        <?php
                        foreach ($estateTypes as $estateType) {
                            echo '<option ' .
                                ($_GET['type_bien'] == $estateType->getCode() ? 'selected="selected" ' : '') .
                                'value="' . esc_attr($estateType->getCode()) . '">' . esc_attr($estateType->getName()) . '</option>';
                        }
                        ?>
                    </select>
                </div>
                <div>
                    <label for="max_budget">Budget Maximum (€) :</label>
                    <input type="number" id="max_budget"
                           name="max_budget" <?php if (isset($_GET['max_budget'])) echo 'value="' . esc_attr($_GET['max_budget']) . '"' ?> >
                </div>
                <div>
                    <label for="ville">Ville :</label>
                    <select id="ville" name="ville">
                        <option value=""></option>
                        <?php
                        foreach ($cities as $city) {
                            echo '<option ' .
                                ($_GET['ville'] == $city->getPostalCode() ? 'selected="selected" ' : '') .
                                'value="' . esc_attr($city->getPostalCode()) . '">' .
                                $city->getName() . ' (' . $city->getPostalCode() . ')</option>';
                        }
                        ?>
                    </select>
                </div>
                <div>
                    <label for="surface">Surface Habitable (m²) :</label>
                    <input type="number" id="surface"
                           name="surface" <?php if (isset($_GET['surface'])) echo 'value="' . esc_attr($_GET['surface']) . '"' ?> >
                </div>
                <div>
                    <label for="nombres_chambres">Nombre de chambres :</label>
                    <input type="number" min="0" id="nombres_chambres"
                           name="nombres_chambres" <?php if (isset($_GET['nombres_chambres'])) echo 'value="' . esc_attr($_GET['nombres_chambres']) . '"'; ?>>
                </div>
                <div>
                    <label for="nombre_pieces">Nbre de pièces :</label>
                    <input type="number" min="0" id="nombre_pieces"
                           name="nombre_pieces" <?php if (isset($_GET['nombre_pieces'])) echo 'value="' . esc_attr($_GET['nombre_pieces']) . '"' ?>>
                </div>
                <div>
                    <label for="tri">Trier par :</label>
                    <select id="tri" name="tri">
                        <option value="date_enr&1">Nouveautés</option>
                        <option value="prix&1">Prix croissant</option>
                        <option value="prix&0">Prix décroissant</option>
                    </select>
                </div>
            </div>
            <div id="filtres-supplementaires" class="hidden">
                <div class="ligne">
                    <div>
                        <label for="habitable_surface_min">Surface habitable min (m²) :</label>
                        <input type="number" id="habitable_surface_min" name="habitable_surface_min" min="0">
                    </div>
                    <div>
                        <label for="habitable_surface_max">Surface habitable max (m²) :</label>
                        <input type="number" id="habitable_surface_max" name="habitable_surface_max">
                    </div>
                    <div>
                        <label for="class_energie">Classe énergie :</label>
                        <select id="class_energie" name="class_energie">
                            <option value="">Toutes</option>
                            <option value="A">A</option>
                            <option value="B">B</option>
                            <option value="C">C</option>
                            <option value="D">D</option>
                            <option value="E">E</option>
                            <option value="F">F</option>
                            <option value="G">G</option>
                        </select>
                    </div>
                    <div>
                        <label for="class_climat">Classe climat :</label>
                        <select id="class_climat" name="class_climat">
                            <option value="">Toutes</option>
                            <option value="A">A</option>
                            <option value="B">B</option>
                            <option value="C">C</option>
                            <option value="D">D</option>
                            <option value="E">E</option>
                            <option value="F">F</option>
                            <option value="G">G</option>
                        </select>
                    </div>
                    <div>
                        <label for="nb_salle_bain">Nombre de salles de bain :</label>
                        <input type="number" id="nb_salle_bain" name="nb_salle_bain" min="0">
                    </div>
                </div>
                <div class="ligne">
                    <div>
                        <label for="piscine">Avec piscine</label>
                        <select id="piscine" name="piscine">
                            <option value="0">Non</option>
                            <option value="1">Oui</option>
                        </select>
                    </div>
                    <div>
                        <label for="vue">Vue :</label>
                        <select id="vue" name="vue">
                            <option value="">Toutes</option>
                            <option value="ville">Ville</option>
                            <option value="mer">Mer</option>
                            <option value="montagne">Montagne</option>
                        </select>
                    </div>
                    <div>
                        <label for="orientation">Orientation :</label>
                        <select id="orientation" name="orientation">
                            <option value="">Toutes</option>
                            <option value="NORD">Nord</option>
                            <option value="SUD">Sud</option>
                            <option value="EST">Est</option>
                            <option value="OUEST">Ouest</option>
                        </select>
                    </div>
                </div>
            </div>
            <button type="button" id="voir-plus">Voir plus</button>
            <button type="submit">Filtrer</button>
        </form>
        <?php
    }

    public function shortcode_fav_estates() {
        ob_start();

        // recup les données envoyées en JSON
        $data = json_decode(file_get_contents('php://input'), true);

        // verif si le JSON contient la clé 'favorites'
        if (empty($data['favorites'])) {
            echo "Aucune donnée reçue ou la liste de favoris est vide.";
            return ob_get_clean();
        }

        // recup la liste d’IDs de biens
        $estate_ids = $data['favorites'];

        // recup les biens correspondants
        $allListings = $this->getEstate->get_estates_by_ids($estate_ids);

        // vérif si la fonction get_estates_by_ids(...) a renvoyé quelque chose
        if (empty($allListings)) {
            echo "Aucun bien trouvé pour les favoris spécifiés.";
            return ob_get_clean();
        }
        ?>

        <div class="property-list">
            <?php foreach ($allListings as $property) : ?>
                <div class="property-item">

                    <!-- Carrousel d’images pour chaque bien -->
                    <?php if (!empty($property['images']) && is_array($property['images'])) : ?>
                        <div class="property-carousel swiper">
                            <div class="swiper-wrapper">
                                <?php foreach ($property['images'] as $image) : ?>
                                    <div class="swiper-slide">
                                        <img src="<?php echo esc_url($image); ?>" alt="<?php echo esc_attr($property['titre']); ?>">
                                    </div>
                                <?php endforeach; ?>
                            </div>
                            <div class="swiper-button-next"></div>
                            <div class="swiper-button-prev"></div>
                            <div class="swiper-pagination"></div>
                        </div>
                    <?php endif; ?>

                    <!-- Informations principales du bien -->
                    <div class="property-content">
                        <h3 class="property-title">
                            <?php echo esc_html($property['titre']); ?>
                        </h3>

                        <p class="property-description">
                            <?php
                            // Nettoyage du texte pour éviter les tags HTML indésirables
                            echo esc_html(
                                ltrim(
                                    rtrim(
                                        trim(
                                            strip_tags(
                                                html_entity_decode(
                                                    $property['corps_impression']
                                                    ?? substr($property['corps'], 0, 80) . '...'
                                                )
                                            )
                                        ),
                                        '></'
                                    ),
                                    '></'
                                )
                            );
                            ?>
                        </p>

                        <p class="property-price">
                            <?php echo esc_html($property['prix'] . "€"); ?>
                        </p>

                        <p class="property-location">
                            <?php
                            // Gestion de la ville/pays (vérification si 'pays' est un tableau ou objet)
                            $paysNom = is_array($property['pays']) && isset($property['pays']['nom'])
                                ? $property['pays']['nom']
                                : '';
                            echo esc_html($property['ville'] . ', ' . $paysNom);
                            ?>
                        </p>

                        <p class="property-type">
                            <?php echo esc_html($property['type_bien']); ?>
                        </p>

                        <!-- Lien vers la page du bien -->
                        <a href="<?php echo '/index.php/bien?id=' . urlencode($property['id']['id'] ?? ''); ?>">
                            <i class="fa-solid fa-eye"></i>
                        </a>
                        <a class="fav-btns" data-info="<?php echo htmlspecialchars($property['id']['id'] ?? '', ENT_QUOTES, 'UTF-8'); ?>">
                            <i class="fa-solid fa-heart-circle-xmark"></i>
                        </a>
                    </div>
                </div>
            <?php endforeach; ?>
        </div>

        <?php
        // Retourne le contenu du tampon
        return ob_get_clean();
    }

    /**
     * Shortcode : [list_favorites]
     * Affiche la liste des biens favoris (stockés dans localStorage > "favorites")
     */
    function shortcode_list_favorites() {
        ob_start();
        ?>
        <!-- Conteneur où on va injecter la liste des favoris -->
        <div id="favorite-properties" class="property-list"></div>

        <script>
            document.addEventListener('DOMContentLoaded', function() {
                // recup les IDs dans localStorage
                let favIds = JSON.parse(localStorage.getItem('favorites')) || [];

                const container = document.getElementById('favorite-properties');

                // si ya pas d'IDs => affiche un message
                if (!favIds.length) {
                    container.innerHTML = "<p>Aucun bien en favoris.</p>";
                    return;
                }

                let route = "<?php echo rest_url('impulse/v1/get_estates_by_ids'); ?>";

                let url = route + '?ids=' + favIds.join(',');

                fetch(url)
                    .then(response => response.json())
                    .then(json => {
                        if (json.error) {
                            container.innerHTML = "<p>Erreur : " + json.error + "</p>";
                            return;
                        }

                        let success = json.success || {};

                        // S’il n’y a pas de biens récupérés
                        if (!Object.keys(success).length) {
                            container.innerHTML = "<p>Aucun bien trouvé.</p>";
                            return;
                        }

                        // Construire le HTML
                        let html = "";
                        Object.values(success).forEach(property => {
                            html += buildPropertyHTML(property);
                        });

                        //  Injecter le HTML
                        container.innerHTML = html;

                        if (typeof initAllSwipers === 'function') {
                            initAllSwipers('#favorite-properties');
                        }

                    })
                    .catch(err => {
                        console.error(err);
                        container.innerHTML = "<p>Impossible de charger les favoris pour le moment.</p>";
                    });

                function buildPropertyHTML(property) {
                    return `
            <div class="property-item">
                ${generateCarouselHTML(property)}
                <div class="property-content">
                    <h3 class="property-title">
                        ${escapeHTML(property.titre || '')}
                    </h3>
                    <p class="property-description">
                        ${escapeHTML(formatDescription(property))}
                    </p>
                    <p class="property-price">
                        ${escapeHTML((property.prix || '') + '€')}
                    </p>
                    <p class="property-location">
                        ${escapeHTML(property.ville || '')}
                        ${property.pays && property.pays.nom ? ', ' + escapeHTML(property.pays.nom) : ''}
                    </p>
                    <p class="property-type">
                        ${escapeHTML(property.type_bien || '')}
                    </p>
                    <a href="/index.php/bien?id=${encodeHTML(property.id.id)}">
                        <i class="fa-regular fa-eye"></i>
                    </a>
                    <!-- Bouton Favoris -->
                    <a class="fav-btns" data-info="${encodeHTML(property.id.id)}">
                        <i class="fa-solid fa-heart-circle-plus"></i>
                    </a>
                </div>
            </div>
            `;
                }

                function generateCarouselHTML(property) {
                    if (!property.images || !Array.isArray(property.images) || !property.images.length) {
                        return '';
                    }
                    let slides = property.images.map(image => {
                        return `<div class="swiper-slide">
                            <img src="${encodeHTML(image)}" alt="${encodeHTML(property.titre || '')}">
                        </div>`;
                    }).join('');
                    return `
                <div class="property-carousel swiper">
                    <div class="swiper-wrapper">
                        ${slides}
                    </div>
                    <div class="swiper-button-next"></div>
                    <div class="swiper-button-prev"></div>
                    <div class="swiper-pagination"></div>
                </div>
            `;
                }

                function formatDescription(property) {
                    let desc = property.corps_impression
                        ? property.corps_impression
                        : (property.corps ? property.corps.substring(0, 80) + '...' : '');

                    let tmp = document.createElement('DIV');
                    tmp.innerHTML = desc;
                    return tmp.textContent || tmp.innerText || "";
                }

                function escapeHTML(value) {
                    // si c'est un nombre, on le convertit
                    if (typeof value === 'number') {
                        value = String(value);
                    }
                    // sinon on retourne vide
                    if (typeof value !== 'string') {
                        return '';
                    }
                    return value
                        .replace(/&/g, "&amp;")
                        .replace(/</g, "&lt;")
                        .replace(/>/g, "&gt;")
                        .replace(/"/g, "&quot;")
                        .replace(/'/g, "&#039;");
                }


                function encodeHTML(str) {
                    return escapeHTML(str);
                }
            });
        </script>
        <?php
        return ob_get_clean();
    }
    /**
     * Pour l’AJOUT / SUPPRESSION des favoris : listener global
     */
    public function euqueue_fav_script() {
        // On charge un JS (même vide) pour avoir un handle
        wp_enqueue_script('fav_script', get_template_directory_uri() . '/js/fav-script.js', array(), null, true);

        // On injecte du code inline
        wp_add_inline_script(
            'fav_script',
            "
            // On définit une fonction globale qui met à jour TOUTES les icônes:
            window.updateAllFavButtons = function() {
                const favorites = getFavorites();
                const favBtns   = document.querySelectorAll('.fav-btns');
                favBtns.forEach(btn => {
                    const propId = btn.dataset.info;
                    if (favorites.includes(propId)) {
                        btn.innerHTML = '<i class=\"fa-solid fa-heart-circle-xmark\"></i>';
                        btn.title     = 'Retirer ce bien des favoris';
                    } else {
                        btn.innerHTML = '<i class=\"fa-solid fa-heart-circle-plus\"></i>';
                        btn.title     = 'Ajouter ce bien en favoris';
                    }
                });
            }

            // Idem pour get/save
            window.getFavorites = function() {
                const favs = localStorage.getItem('favorites');
                return favs ? JSON.parse(favs) : [];
            }
            window.saveFavorites = function(favs) {
                localStorage.setItem('favorites', JSON.stringify(favs));
            }

            document.addEventListener('DOMContentLoaded', function() {
                // 1) On met à jour d'abord les icônes au chargement
                if (typeof updateAllFavButtons === 'function') {
                    updateAllFavButtons();
                }

                // 2) Event delegation sur body
                document.body.addEventListener('click', function(e) {
                    const button = e.target.closest('.fav-btns');
                    if (!button) return;

                    let favorites = getFavorites();
                    const propId = button.dataset.info;

                    // Ajout / retrait
                    if (favorites.includes(propId)) {
                        favorites = favorites.filter(id => id !== propId);
                        alert('Bien retiré des favoris !');
                    } else {
                        favorites.push(propId);
                        alert('Bien ajouté aux favoris !');
                    }
                    saveFavorites(favorites);

                    // Met à jour toutes les icônes (ou juste le bouton cliqué)
                    updateAllFavButtons();
                });
            });
            ",
            'after'
        );
    }


    /**
     * Enqueue les scripts et styles nécessaires au carrousel (Swiper) et à la carte (Leaflet).
     * Appelée dans le hook 'wp_enqueue_scripts'.
     *
     * @return void
     */
    public function enqueue_carousel_scripts() {
        // Swiper CSS & JS
        wp_enqueue_style( 'swiper-style', 'https://unpkg.com/swiper/swiper-bundle.min.css', array(), '8.4.5' );
        wp_enqueue_script( 'swiper-script', 'https://unpkg.com/swiper/swiper-bundle.min.js', array(), '8.4.5', true );

        // Fichier CSS principal
        wp_enqueue_style(
            'plugin-style',
            plugin_dir_url(__FILE__) . 'styles.css',
            array(),
            '1.0.0'
        );

        // Initialise le carrousel après le chargement du DOM
        wp_add_inline_script(
            'swiper-script',
            "
    // déclare une fonction globale 
    function initAllSwipers(containerSelector) {
        
        const container = containerSelector
            ? document.querySelector(containerSelector)
            : document;

        if (!container) return; // Sécurité

        // Sélectionne tous les .property-carousel, .single-property-carousel dans ce container
        const carousels = container.querySelectorAll('.property-carousel, .single-property-carousel');
        carousels.forEach(carousel => {
            new Swiper(carousel, {
                loop: true,
                navigation: {
                    nextEl: '.swiper-button-next',
                    prevEl: '.swiper-button-prev',
                },
                pagination: {
                    el: '.swiper-pagination',
                    clickable: true,
                },
            });
        });
    }

    document.addEventListener('DOMContentLoaded', function () {
        initAllSwipers();
    });
    ",
            'after'
        );


        // Leaflet CSS & JS
        wp_enqueue_style( 'leaflet-css', 'https://unpkg.com/leaflet@1.9.3/dist/leaflet.css', array(), '1.9.3' );
        wp_enqueue_script( 'leaflet-js', 'https://unpkg.com/leaflet@1.9.3/dist/leaflet.js', array(), '1.9.3', true );
    }

    /**
     * Enqueue un script externe pour la pagination en JS.
     * Appelé dans le hook 'wp_enqueue_scripts'.
     *
     * @return void
     */
    public function enqueue_pagination_scripts() {
        wp_enqueue_script(
            'pagination-script',
            plugin_dir_url(__FILE__) . 'Pagination.js',
            array(),
            '1.0',
            true
        );
    }
}

$url = "https://impulsepasserelle.alwaysdata.net/annonce/get";
// Instancie la classe principale
$display_estate = new DisplayEstate($url);

// Hooks & Shortcodes
// Chargement des scripts et styles pour le carrousel et Leaflet
add_action( 'wp_enqueue_scripts', array( $display_estate, 'enqueue_carousel_scripts' ) );
add_action('wp_enqueue_scripts', array( $display_estate, 'euqueue_fav_script'));
// Shortcode avec pagination
add_shortcode('properties_pagination', array( $display_estate, 'estate_paging_shortcode'));

// [list_properties] => affiche 3 biens maximum (homepage)
add_shortcode('list_properties', array( $display_estate, 'shortcode_home_estate' ));

// [estate_page] => page d’un seul bien
add_shortcode('estate_page', array( $display_estate, 'shortcode_estate_page' ));

add_shortcode('list_favorites', array($display_estate, 'shortcode_list_favorites'));

// Chargement du script de pagination
add_action('wp_enqueue_scripts', array( $display_estate, 'enqueue_pagination_scripts'));

<?php
require_once 'get_estate.php';

/**
 * Plugin Name: Plugin Property API
 * Description: Plugin to fetch (or simulate) properties from an API with an image carousel.
 * Version: 1.12
 * Author: Chiheb
 */

if ( ! defined( 'ABSPATH' ) ) {
    exit;
}

// Energy class colors
$energyClassColors = array(
    'A' => '#18a16c',
    'B' => '#4eb253',
    'C' => '#a5cd75',
    'D' => '#f2e814',
    'E' => '#f2a705',
    'F' => '#f77707',
    'G' => '#ed0000',
);

// Climate class colors
$climateClassColors = array(
    'A' => '#a3daf8',
    'B' => '#8eb5d4',
    'C' => '#7793b3',
    'D' => '#5f6e8f',
    'E' => '#4c5071',
    'F' => '#363351',
    'G' => '#221332',
);

/**
 * Shortcode [list_properties]
 */
class DisplayEstate {

    /**
     * Shortcode for displaying properties on homepage
     * Usage: [list_properties country="FR"]
     */
    public function shortcode_home_estate($atts) {
        $atts = shortcode_atts(
            array(
                'country' => 'FR',
            ),
            $atts,
            'list_properties'
        );
        $url = "https://impulsepasserelle.alwaysdata.net/annonce/get";
        $getEstate = new GetEstate($url);
        $allListings = array_slice($getEstate->get_estate_by_country($atts['country']), 0, 3);

        $country = $atts['country'];

        if ( ! is_array($allListings) || empty($allListings) ) {
            return "<p> Aucun biens en vente en " . esc_html($country) . ".</p>";
        }

        ob_start();
        ?>
        <div class="property-list">
            <?php foreach ( $allListings as $property ) : ?>
                <div class="property-item">
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

                    <div class="property-content">
                        <h3 class="property-title">
                            <?php echo esc_html($property['titre']); ?>
                        </h3>

                        <p class="property-description">
                            <?php
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

                        <a href="<?php echo '/index.php/bien?id=' . htmlspecialchars($property['id']['id'], ENT_QUOTES, 'UTF-8'); ?>">
                            Voir le bien
                        </a>
                    </div>
                </div>
            <?php endforeach; ?>
        </div>
        <?php
        return ob_get_clean();
    }

    /**
     * Shortcode for displaying a single property page (one estate page)
     * Usage: [estate_page id="1234"]
     */
    public function shortcode_estate_page($atts) {
        $atts = shortcode_atts(
            array(
                'id' => 000,
            ),
            $atts,
            'estate_page'
        );
        $url = "https://impulsepasserelle.alwaysdata.net/annonce/get";
        $getEstate = new GetEstate($url);
        $property = $getEstate->get_estate_by_id($atts['id']);

        if ( ! is_array($property) || empty($property) ) {
            return "<p>Error with the listing</p>";
        }

        ob_start();
        ?>
        <h1 id="property-page-title">
            <?php echo esc_html($property['titre']); ?>
        </h1>
        <div class="single-property">
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

            <div class="single-property-content">
                <h3 class="single-property-title">
                    <?php echo esc_html($property['titre']); ?>
                </h3>

                <p class="single-property-body">
                    <?php
                    echo esc_html(
                        ltrim(
                            rtrim(
                                trim(
                                    strip_tags(
                                        html_entity_decode(
                                            $property['corps'] ?? '')
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
            <h2 class="property-info-title">Location</h2>
            <?php if (!empty($property['latitude']) && !empty($property['longitude'])): ?>
                <div id="property-map" style="height: 400px;"></div>

                <script>
                    document.addEventListener('DOMContentLoaded', function() {
                        var lat = <?php echo esc_js($property['latitude']); ?>;
                        var lng = <?php echo esc_js($property['longitude']); ?>;

                        var map = L.map('property-map').setView([lat, lng], 13);

                        L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
                            attribution: '&copy; OpenStreetMap contributors'
                        }).addTo(map);

                        L.marker([lat, lng]).addTo(map)
                            .bindPopup('<?php echo esc_js($property['titre']); ?>')
                            .openPopup();
                    });
                </script>
            <?php endif; ?>
            <h2 class="property-info-title">Intéréssé par le bien ?</h2>
            <a id="contact-btn-single-property" href="https://impulsewordpresssae.alwaysdata.net/index.php/contact-3/">
                Nous contacter !
            </a>
        </div>
        <?php
        return ob_get_clean();
    }

    /**
     * Shortcode for listing multiple properties on a page (for "tout les biens en" + country)
     * Usage: [list_property_page country="FR"]
     */
    public function shortcode_multiple_estate_page( $atts ) {
        $atts = shortcode_atts(
            array(
                'country' => 'FR',
            ),
            $atts,
            'list_property_page'
        );
        $getEstate = new GetEstate();

        $allListings = $getEstate->fetch_fake_data();

        $country = $atts['country'];

        $filteredListings = array_filter($allListings, function($property) use ($country) {
            return isset($property['pays']) && strtolower($property['pays']) === strtolower($country);
        });

        if ( empty($filteredListings) ) {
            return '<p>Aucun bien en vente en ' . esc_html($country) . '.</p>';
        }

        ob_start();
        ?>


        <div class="property-list-page">
            <?php foreach ( $filteredListings as $property ) : ?>
                <div class="property-card">
                    <?php if ( ! empty($property['images']) && is_array($property['images']) ) : ?>
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

                    <div class="property-details">
                        <h3 class="property-title"><?php echo esc_html($property['titre']); ?></h3>
                        <p class="property-description">
                            <?php
                            echo esc_html(
                                $property['corps_impression']
                                ?? substr($property['corps'], 0, 80) . '...'
                            );
                            ?>
                        </p>
                        <p class="property-price"><?php echo esc_html($property['prix'] . "€"); ?></p>
                        <p class="property-location">
                            <?php echo esc_html($property['ville'] . ', ' . $property['pays']); ?>
                        </p>
                        <p class="property-type">
                            Property type: <?php echo esc_html($property['type_bien']); ?>
                        </p>
                        <a href="<?php echo '/index.php/bien?id=' . htmlspecialchars($property['ref'], ENT_QUOTES, 'UTF-8'); ?>">
                            Voir le bien
                        </a>
                    </div>
                </div>
            <?php endforeach; ?>
        </div>
        <?php
        return ob_get_clean();
    }

    /**
     * Shortcode with pagination for properties (for all estate page)
     * Usage: [properties_pagination country="FR"]
     */
    public function estate_paging_shortcode($atts) {
        $atts = shortcode_atts(array(
            'country' => 'FR',
        ), $atts, 'properties_pagination');
        $url = "https://impulsepasserelle.alwaysdata.net/annonce/get";
        $getEstate = new GetEstate($url);
        $allListings = $getEstate->get_estate_by_country($atts['country']);

        $country = $atts['country'];
        $items_per_page = 10;
        $total_pages    = ceil(count($allListings) / $items_per_page);

        if ( empty($allListings) ) {
            return '<p>Aucun bien en vente en ' . esc_html($country) . '.</p>';
        }

        ob_start();
        ?>
        <form class="biens-filtre-form">
            <label for="type_bien">Type de bien :</label>
            <select id="type_bien" name="type_bien">
                <option value="">Tous</option>
                <option value="Appartement">Appartement</option>
                <option value="Maison">Maison</option>
            </select>

            <label for="prix_min">Prix min :</label>
            <input type="number" id="prix_min" name="prix_min" placeholder="Min (€)">
            <label for="prix_max">Prix max :</label>
            <input type="number" id="prix_max" name="prix_max" placeholder="Max (€)">

            <label for="ville">Ville :</label>
            <input type="text" id="ville" name="ville" placeholder="Nom de la ville">

            <label for="habitable_surface_min">Surface habitable min (m²) :</label>
            <input type="number" id="habitable_surface_min" name="habitable_surface_min">
            <label for="habitable_surface_max">Surface habitable max (m²) :</label>
            <input type="number" id="habitable_surface_max" name="habitable_surface_max">

            <label for="class_energie">Classe énergie :</label>
            <select id="class_energie" name="class_energie">
                <option value="">Toutes</option>
                <option value="A">A</option>
                <option value="B">B</option>
                <option value="C">C</option>
                <option value="D">D</option>
            </select>
            <label for="class_climat">Classe climat :</label>
            <select id="class_climat" name="class_climat">
                <option value="">Toutes</option>
                <option value="A">A</option>
                <option value="B">B</option>
                <option value="C">C</option>
                <option value="D">D</option>
            </select>

            <label for="nb_chambres">Nombre de chambres :</label>
            <input type="number" id="nb_chambres" name="nb_chambres" min="0">
            <label for="nb_salle_bain">Nombre de salles de bain :</label>
            <input type="number" id="nb_salle_bain" name="nb_salle_bain" min="0">

            <label for="piscine">
                <input type="checkbox" id="piscine" name="piscine"> Avec piscine
            </label>

            <label for="vue">Vue :</label>
            <select id="vue" name="vue">
                <option value="">Toutes</option>
                <option value="ville">Ville</option>
                <option value="mer">Mer</option>
                <option value="montagne">Montagne</option>
            </select>

            <label for="orientation">Orientation :</label>
            <select id="orientation" name="orientation">
                <option value="">Toutes</option>
                <option value="nord">Nord</option>
                <option value="sud">Sud</option>
                <option value="est">Est</option>
                <option value="ouest">Ouest</option>
                <option value="sud-est">Sud-Est</option>
            </select>

            <button type="submit">Filtrer</button>
        </form>
        <div class="paginated-properties-list">
            <?php for ($i = 0; $i < $total_pages; $i++) : ?>
                <div class="property-page"
                     id="property-page-<?php echo $i + 1; ?>"
                     style="display: <?php echo $i === 0 ? 'block' : 'none'; ?>;">
                    <div class="properties-list">
                        <?php
                        $offset = $i * $items_per_page;
                        $pagedProperties = array_slice($allListings, $offset, $items_per_page);

                        foreach ($pagedProperties as $property) : ?>
                            <div class="property-card">
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

                                <div class="property-details">
                                    <h3 class="property-title">
                                        <?php echo esc_html($property['titre']); ?>
                                    </h3>
                                    <p class="property-description">
                                        <?php
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
                                    <a href="<?php echo '/index.php/bien?id=' . htmlspecialchars($property['id']['id'], ENT_QUOTES, 'UTF-8'); ?>">
                                        Voir le bien
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
        return ob_get_clean();
    }

    /**
     * Enqueue scripts and styles
     */
    public function enqueue_carousel_scripts() {
        wp_enqueue_style( 'swiper-style', 'https://unpkg.com/swiper/swiper-bundle.min.css', array(), '8.4.5' );
        wp_enqueue_script( 'swiper-script', 'https://unpkg.com/swiper/swiper-bundle.min.js', array(), '8.4.5', true );

        wp_enqueue_style(
            'plugin-style',
            plugin_dir_url(__FILE__) . 'styles.css',
            array(),
            '1.0.0'
        );

        wp_add_inline_script( 'swiper-script', "
            document.addEventListener('DOMContentLoaded', function () {
                const carousels = document.querySelectorAll('.property-carousel, .single-property-carousel');
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
            });
        ");

        // Leaflet
        wp_enqueue_style( 'leaflet-css', 'https://unpkg.com/leaflet@1.9.3/dist/leaflet.css', array(), '1.9.3' );
        wp_enqueue_script( 'leaflet-js', 'https://unpkg.com/leaflet@1.9.3/dist/leaflet.js', array(), '1.9.3', true );
    }

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

// Instantiate our class
$display_estate = new DisplayEstate();

// Hooks & Shortcodes
add_action( 'wp_enqueue_scripts', array( $display_estate, 'enqueue_carousel_scripts' ) );
add_shortcode('properties_pagination', array( $display_estate, 'estate_paging_shortcode'));

// [list_properties] => liste homepage
add_shortcode('list_properties', array( $display_estate, 'shortcode_home_estate' ));

// [estate_page] => single page
add_shortcode('estate_page', array( $display_estate, 'shortcode_estate_page' ));

// [list_property_page] => multiple listing page
add_shortcode('list_property_page', array( $display_estate, 'shortcode_multiple_estate_page' ));

// Pagination script
add_action('wp_enqueue_scripts', array( $display_estate, 'enqueue_pagination_scripts'));
